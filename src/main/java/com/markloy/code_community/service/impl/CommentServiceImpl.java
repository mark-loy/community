package com.markloy.code_community.service.impl;

import com.markloy.code_community.dto.CommentListDTO;
import com.markloy.code_community.enums.CommentType;
import com.markloy.code_community.enums.CustomizeErrorCode;
import com.markloy.code_community.enums.NotificationStatusEnum;
import com.markloy.code_community.enums.NotificationTypeEnum;
import com.markloy.code_community.exception.CustomizeException;
import com.markloy.code_community.mapper.*;
import com.markloy.code_community.pojo.*;
import com.markloy.code_community.service.CommentService;
import com.markloy.code_community.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper cm;

    @Autowired
    private QuestionMapper qm;

    @Autowired
    private QuestionExtMapper qem;

    @Autowired
    private UserMapper um;

    @Autowired
    private NotificationService ns;

    @Autowired
    private CountCommentRecordMapper ccrm;

    @Autowired
    private CommentExtMapper cem;

    @Override
    @Transactional
    public void insertComment(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() < 1) {
            throw new CustomizeException(CustomizeErrorCode.PARAM_ERROR);
        }
        if (comment.getType() == null || !CommentType.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.PARAM_ERROR);
        }
        if (CommentType.COMMENT_TYPE.getType().equals(comment.getType())) {
            //评论回复
            Comment checkComment = cm.selectByPrimaryKey(comment.getParentId());
            if (checkComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            cm.insertSelective(comment);
            //查询该通知所属的问题
            Question question = qm.selectByPrimaryKey(checkComment.getParentId());
            //新增评论回复通知
            ns.addNotification(question, NotificationTypeEnum.COMMENT_TYPE, NotificationStatusEnum.UNREAD, comment);
        } else {
            //问题回复(问题评论数也要加一)
            Question question = qm.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            cm.insertSelective(comment);
            //增加回复数
            question.setCommentCount(1);
            qem.incCommentCount(question);
            //新增问题回复通知
            ns.addNotification(question, NotificationTypeEnum.QUESTION_TYPE, NotificationStatusEnum.UNREAD, comment);
        }
    }

    @Override
    public List<CommentListDTO> findById(Long id, CommentType questionType) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(questionType.getType());
        List<Comment> commentList = cm.selectByExample(commentExample);
        List<CommentListDTO> listDTOS = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentListDTO commentListDTO = new CommentListDTO();
            User user = um.selectByPrimaryKey(comment.getCreator());
            BeanUtils.copyProperties(comment, commentListDTO);
            commentListDTO.setUser(user);
            //查询当前评论的子回复数
            CommentExample chileExample = new CommentExample();
            chileExample.createCriteria()
                    .andParentIdEqualTo(comment.getId())
                    .andTypeEqualTo(CommentType.COMMENT_TYPE.getType());
            long childCommentCount = cm.countByExample(chileExample);
            commentListDTO.setChildCommentCount(childCommentCount);
            listDTOS.add(commentListDTO);
        }
        return listDTOS;
    }

    @Override
    public int incCommentLikeCount(Long id, User user) {
        Comment comment = cm.selectByPrimaryKey(id);
        if (comment.getCreator().equals(user.getId())) {
            //当前登录用户不能点赞自己评论,返回questionId
            return 0;
        }
        CountCommentRecordExample commentRecordExample = new CountCommentRecordExample();
        commentRecordExample.createCriteria()
                .andUserIdEqualTo(user.getId())
                .andQuestionIdEqualTo(comment.getParentId())
                .andCommentIdEqualTo(id);
        List<CountCommentRecord> countCommentRecords = ccrm.selectByExample(commentRecordExample);
        if (countCommentRecords.size() == 0) {
            //当前登录用户，对当前问题，当前点击的评论未点赞
            CountCommentRecord record = new CountCommentRecord();
            record.setUserId(user.getId());
            record.setQuestionId(comment.getParentId());
            record.setCommentId(id);
            record.setGmtCreate(System.currentTimeMillis());
            record.setCommentLikeCheck(1);
            ccrm.insertSelective(record);
            //增加点赞数
            Comment recordCommentLike = new Comment();
            recordCommentLike.setId(id);
            recordCommentLike.setLikeCount(1);
            int resultId = cem.incCommentLikeCount(recordCommentLike);
            if (resultId > 0) {
                //添加成功
                return resultId;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
