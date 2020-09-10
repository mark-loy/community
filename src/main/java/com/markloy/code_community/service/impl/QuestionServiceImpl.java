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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper qm;

    @Autowired
    private QuestionExtMapper qem;

    @Autowired
    private UserMapper um;

    @Override
    public PageDTO<QuestionDTO> findAll(Integer currentPage, Integer count, Integer size, String search) {
        //查询所有问题
        String sear = null;
        if (!StringUtils.isEmpty(search)) {
            //处理search(将字符串中的空格和逗号，替换为|)
            sear = search.replaceAll(",| |，", "|");
        }

        QuestionSearchDTO searchDTO = new QuestionSearchDTO();
        searchDTO.setCount(count);
        searchDTO.setSearch(sear);
        searchDTO.setSize(size);
        List<Question> question = qem.selectSearch(searchDTO);
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
        PageDTO<QuestionDTO> pageDTO = new PageDTO<>();
        //分页控制计算
        pageDTO.computer(currentPage, qem.selectSearchCount(sear), size);
        pageDTO.setGeneraDTO(questionDTO);
        return pageDTO;
    }

    @Override
    public PageDTO<QuestionDTO> findByUserId(Long userId, Integer currentPage, Integer count, Integer size) {
        List<QuestionDTO> questionDTO = new ArrayList<>();
        //查询所有问题
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("GMT_CREATE DESC");
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> question = qm.selectByExampleWithBLOBsWithRowbounds(questionExample, new RowBounds(count, size));
        for (Question que : question) {
            QuestionDTO dto = new QuestionDTO();
            //通过关联的creator查询user的用户信息
            User user = um.selectByPrimaryKey(que.getCreator());
            //通过工具类将问题信息赋值到dto中
            BeanUtils.copyProperties(que, dto);
            dto.setUser(user);
            questionDTO.add(dto);
        }
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
        List<Question> related = qem.selectRelated(question);
        return related;
    }
}
