package com.markloy.code_community.service.impl;

import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.dto.QuestionDTO;
import com.markloy.code_community.mapper.QuestionMapper;
import com.markloy.code_community.mapper.UserMapper;
import com.markloy.code_community.pojo.Question;
import com.markloy.code_community.pojo.User;
import com.markloy.code_community.service.QuestionService;
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
        List<Question> question = qm.findAll(count, size);
        List<QuestionDTO> questionDTO = new ArrayList<>();
        for (Question que:question) {
            QuestionDTO dto = new QuestionDTO();
            //通过关联的creator查询user的用户信息
            User user = um.findById(que.getCreator());
            //通过工具类将问题信息赋值到dto中
            BeanUtils.copyProperties(que, dto);
            dto.setUser(user);
            questionDTO.add(dto);
        }

        PageDTO pageDTO = new PageDTO();
        //分页控制计算
        pageDTO.computer(currentPage, qm.findTotalCount(), size);
        pageDTO.setQuestionDTO(questionDTO);
        return pageDTO;
    }
}
