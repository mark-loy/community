package com.markloy.code_community.dto;

import lombok.Data;

@Data
public class NotificationDTO {

    private Long Id;
    private String typeMsg;
    private String title;
    private String notifier;
    private String statusMsg;
    private Long gmtCreate;

}
