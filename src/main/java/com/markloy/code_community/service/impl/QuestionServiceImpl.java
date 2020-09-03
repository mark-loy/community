package com.markloy.code_community.service.impl;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.mapper.QuestionMapper;
import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.Question;
import com.markloy.code_community.pojo.QuestionExample;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper qm;

    @Autowired
    private UserMapper um;

    @Override
    public PageDTO findAll(Integer currentPage, Integer count, Integer size) {
        //查询所有问题
        List<Question> question = qm.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(count, size));
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
        PageDTO pageDTO = new PageDTO();
        //分页控制计算
        pageDTO.computer(currentPage, (int) qm.countByExample(new QuestionExample()), size);
        pageDTO.setQuestionDTO(questionDTO);
        return pageDTO;
    }

    @Override
    public PageDTO findByUserId(Integer userId, Integer currentPage, Integer count, Integer size) {
        List<QuestionDTO> questionDTO = new ArrayList<>();
        //查询所有问题
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> question = qm.selectByExampleWithRowbounds(questionExample, new RowBounds(count, size));
        for (Question que : question) {
            QuestionDTO dto = new QuestionDTO();
            //通过关联的creator查询user的用户信息
            User user = um.selectByPrimaryKey(que.getCreator());
            //通过工具类将问题信息赋值到dto中
            BeanUtils.copyProperties(que, dto);
            dto.setUser(user);
            questionDTO.add(dto);
        }
        PageDTO pageDTO = new PageDTO();
        //分页控制计算
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria()
                .andCreatorEqualTo(userId);
        pageDTO.computer(currentPage, (int) qm.countByExample(questionExample1), size);
        pageDTO.setQuestionDTO(questionDTO);
        return pageDTO;
    }

    @Override
    public QuestionDTO findById(Integer id) {
        Question question = qm.selectByPrimaryKey(id);
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
            qm.insert(question);
        } else {
            //修改
            Question que = new Question();
            que.setGmtModify(System.currentTimeMillis());
            que.setTitle(question.getTitle());
            que.setDescription(question.getDescription());
            que.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            qm.updateByPrimaryKeySelective(que, questionExample);
        }
    }
}
