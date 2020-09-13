package com.markloy.code_community.service.impl;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.dto.QuestionSearchDTO;
import com.markloy.code_community.exception.CustomizeException;
import com.markloy.code_community.enums.CustomizeErrorCode;
import com.markloy.code_community.mapper.QuestionExtMapper;
import com.markloy.code_community.mapper.QuestionMapper;
import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.Question;
import com.markloy.code_community.pojo.QuestionExample;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper qm;

    @Autowired
    private QuestionExtMapper qem;

    @Autowired
    private UserMapper um;

    @Value("${hotQuestion.month}")
    private Long hotQuestionMonth;

    @Value("${hotQuestion.week}")
    private Long hotQuestionWeek;

    @Value("${hotQuestion.day}")
    private Long getHotQuestionDay;

    public List<QuestionDTO> forEachQuestion(List<Question> question) {
        List<QuestionDTO> questionDTO = new ArrayList<>();
        for (Question que : question) {
            QuestionDTO dto = new QuestionDTO();
            //通过关联的creator查询user的用户信息
            User user = um.selectByPrimaryKey(que.getCreator());
            //通过工具类将问题信息赋值到dto中
            BeanUtils.copyProperties(que, dto);
            dto.setUser(user);
            questionDTO.add(dto);
        }
        return questionDTO;
    }

    @Override
    public PageDTO<QuestionDTO> findAll(Integer currentPage, Integer count, Integer size, String search, String tag) {
        //查询所有问题
        QuestionSearchDTO searchDTO = new QuestionSearchDTO();
        if (!StringUtils.isEmpty(search)) {
            //处理search(将字符串中的空格和逗号，替换为|)
            searchDTO.setSearch(search.replaceAll(",| |，", "|"));
        }
        if (!StringUtils.isEmpty(tag)) {
            searchDTO.setTag(tag);
        }
        searchDTO.setCount(count);
        searchDTO.setSize(size);
        List<Question> question = qem.selectSearch(searchDTO);
        List<QuestionDTO> questionDTO = forEachQuestion(question);
        //分页控制计算
        PageDTO<QuestionDTO> pageDTO = new PageDTO<>();
        pageDTO.computer(currentPage, qem.selectSearchCount(searchDTO), size);
        pageDTO.setGeneraDTO(questionDTO);
        return pageDTO;
    }

    @Override
    public PageDTO<QuestionDTO> findByUserId(Long userId, Integer currentPage, Integer count, Integer size) {
        //查询所有问题
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("GMT_CREATE DESC");
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> question = qm.selectByExampleWithBLOBsWithRowbounds(questionExample, new RowBounds(count, size));
        List<QuestionDTO> questionDTO = forEachQuestion(question);
        PageDTO<QuestionDTO> pageDTO = new PageDTO<>();
        //分页控制计算
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria().andCreatorEqualTo(userId);
        pageDTO.computer(currentPage, (int) qm.countByExample(questionExample1), size);
        pageDTO.setGeneraDTO(questionDTO);
        return pageDTO;
    }

    @Override
    public QuestionDTO findById(Long id) {
        Question question = qm.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = um.selectByPrimaryKey(question.getCreator());
        QuestionDTO dto = new QuestionDTO();
        BeanUtils.copyProperties(question, dto);
        dto.setUser(user);
        return dto;
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //新增
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            qm.insertSelective(question);
        } else {
            //修改
            Question que = new Question();
            que.setGmtModify(System.currentTimeMillis());
            que.setTitle(question.getTitle());
            que.setDescription(question.getDescription());
            que.setTag(question.getTag());
            que.setId(question.getId());
            qm.updateByPrimaryKeySelective(que);
        }
    }

    @Override
    public int incViewCount(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        return qem.incViewCount(question);
    }

    @Override
    public List<Question> selectRelated(QuestionDTO questionDTO) {
        String replaceTag = questionDTO.getTag().replace(',', '|');
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);
        question.setTag(replaceTag);
        return qem.selectRelated(question);
    }

    @Override
    public PageDTO<QuestionDTO> findByCommentCount(Integer currentPage, int count, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCommentCountEqualTo(0);
        List<Question> questions = qm.selectByExampleWithRowbounds(questionExample, new RowBounds(count, size));
        List<QuestionDTO> questionDTO = forEachQuestion(questions);
        //分页控制计算
        PageDTO<QuestionDTO> pageDTO = new PageDTO<>();
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria().andCommentCountEqualTo(0);
        pageDTO.computer(currentPage, (int) qm.countByExample(questionExample1), size);
        pageDTO.setGeneraDTO(questionDTO);
        return pageDTO;
    }

    @Override
    public PageDTO<QuestionDTO> findHotQuestion(Integer currentPage, int count, Integer size, int hotId) {
        Long time = null;
        if (hotId == 1) {
            //7天以内的热门问题
            time = System.currentTimeMillis() - hotQuestionWeek;
        } else if (hotId == 2) {
            //30天以内的热门问题
            time = System.currentTimeMillis() - hotQuestionMonth;
        } else {
            //当天的热门问题
            time = System.currentTimeMillis() - getHotQuestionDay;
        }
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andGmtCreateGreaterThanOrEqualTo(time);
        List<Question> questions = qm.selectByExampleWithRowbounds(questionExample, new RowBounds(count, size));
        //排序
        questions.sort(new Comparator<Question>() {
            @Override
            public int compare(Question o1, Question o2) {
                return  (o2.getCommentCount() - o1.getCommentCount()) + (o2.getViewCount() - o1.getViewCount());
            }
        });
        List<QuestionDTO> questionDTO = forEachQuestion(questions);
        //分页控制计算
        PageDTO<QuestionDTO> pageDTO = new PageDTO<>();
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria().andGmtCreateGreaterThanOrEqualTo(time);
        pageDTO.computer(currentPage, (int) qm.countByExample(questionExample1), size);
        pageDTO.setGeneraDTO(questionDTO);
        return pageDTO;
    }
}
