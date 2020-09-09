package com.markloy.code_community.service;

import com.markloy.code_community.dto.NotificationDTO;
import com.markloy.code_community.dto.PageDTO;
import com.markloy.code_community.enums.NotificationStatusEnum;
import com.markloy.code_community.enums.NotificationTypeEnum;
import com.markloy.code_community.pojo.Comment;
import com.markloy.code_community.pojo.Question;

import java.util.List;

public interface NotificationService {

    void addNotification(Question question, NotificationTypeEnum typeEnum,
                         NotificationStatusEnum statusEnum, Comment comment );

    PageDTO<NotificationDTO> findByUserId(Long id, Integer currentPage, int count, Integer size);
}
