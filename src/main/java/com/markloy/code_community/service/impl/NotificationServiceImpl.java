package com.markloy.code_community.service.impl;

import com.markloy.code_community.dto.NotificationDTO;
import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.enums.NotificationStatusEnum;
import com.markloy.code_community.enums.NotificationTypeEnum;
import com.markloy.code_community.mapper.CommentMapper;
import com.markloy.code_community.mapper.NotificationMapper;
import com.markloy.code_community.mapper.QuestionMapper;
import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.*;
import com.markloy.code_community.service.NotificationService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper nm;

    @Autowired
    private QuestionMapper qm;

    @Autowired
    private UserMapper um;

    @Autowired
    private CommentMapper cm;

    @Override
    public void addNotification(Question question, NotificationTypeEnum typeEnum, NotificationStatusEnum statusEnum, Comment comment) {
        Notification notification = new Notification();
        //设置当前毫秒数
        notification.setGmtCreate(System.currentTimeMillis());
        //设置通知类型
        notification.setType(typeEnum.getType());
        //设置未读
        notification.setStatus(statusEnum.getStatus());
        //设置通知的评论
        notification.setParentId(question.getId());
        //设置评论者（消息发送者）
        notification.setNotifierId(comment.getCreator());
        //设置通知者（消息接收者）
        notification.setReceiverId(question.getCreator());
        nm.insertSelective(notification);
    }

    @Override
    public PageDTO<NotificationDTO> findByUserId(Long userId, Integer currentPage, int count, Integer size) {
        //1.查询当前用户的所有通知
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.setOrderByClause("gmt_create desc");
        notificationExample.createCriteria().andReceiverIdEqualTo(userId);
        List<Notification> notifications = nm.selectByExampleWithRowbounds(notificationExample, new RowBounds(count, size));
        //遍历集合，替换外键，结果保存dto
        for (Notification notification : notifications) {
            NotificationDTO dto = new NotificationDTO();
            //设置通知id
            dto.setId(notification.getId());
            Question question = qm.selectByPrimaryKey(notification.getParentId());
            //设置通知时间
            dto.setGmtCreate(notification.getGmtCreate());
            //获取通知主题
            dto.setTitle(question.getTitle());
            User user = um.selectByPrimaryKey(notification.getNotifierId());
            //获取通知人
            String loginName = user.getLoginName();
            dto.setNotifier(loginName);
            if (NotificationTypeEnum.COMMENT_TYPE.getType().equals(notification.getType())) {
                //评论类型回复
                dto.setTypeMsg(NotificationTypeEnum.COMMENT_TYPE.getMsg());
            } else {
                //问题回复
                dto.setTypeMsg(NotificationTypeEnum.QUESTION_TYPE.getMsg());
            }

            if (NotificationStatusEnum.UNREAD.getStatus().equals(notification.getStatus())) {
                //未读
                dto.setStatusMsg(NotificationStatusEnum.UNREAD.getMsg());
            } else {
                //已读
                dto.setStatusMsg(NotificationStatusEnum.READ.getMsg());
            }
            notificationDTOS.add(dto);
        }
        //分页控制计算
        //总记录数查询
        NotificationExample countExample = new NotificationExample();
        countExample.createCriteria().andReceiverIdEqualTo(userId);
        PageDTO<NotificationDTO> pageDTO = new PageDTO<>();
        pageDTO.setGeneraDTO(notificationDTOS);
        pageDTO.computer(currentPage, (int) nm.countByExample(countExample), size);
        return pageDTO;
    }
}
