package com.carblre.repository.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String to;
    private String from;
    private String text;
    private String subject;

    public Message(String to, String from, String text, String subject) {
        this.to = to;
        this.from = from;
        this.text = text;
        this.subject = subject;
    }
}