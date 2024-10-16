package com.carblre.repository.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageModel {
    private String groupId;
    private String messageId;
    private String to;
    private String from;
    private String type;
    private String statusCode;
    private String statusMessage;
    private Object customFields;

    // Getters and Setters
}