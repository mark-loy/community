package com.markloy.code_community.controller;

import com.markloy.code_community.enums.CustomizeErrorCode;
import com.markloy.code_community.enums.NotificationStatusEnum;
import com.markloy.code_community.exception.CustomizeException;
import com.markloy.code_community.mapper.NotificationMapper;
import com.markloy.code_community.mapper.QuestionMapper;
import com.markloy.code_community.pojo.Notification;
import com.markloy.code_community.pojo.NotificationExample;
import com.markloy.code_community.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotificationController {

    @Autowired
    private NotificationMapper nm;

    @Autowired
    private QuestionMapper qm;

    @GetMapping("/notification/{id}")
    public String updateNotificationStatus(@PathVariable("id") Long id) {
        Notification notification = nm.selectByPrimaryKey(id);
        Notification record = new Notification();
        record.setId(id);
        record.setStatus(NotificationStatusEnum.READ.getStatus());
        nm.updateByPrimaryKeySelective(record);
        //查询问题是否存在
        Question question = qm.selectByPrimaryKey(notification.getParentId());
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        return "redirect:/question/"+notification.getParentId();
    }

}
