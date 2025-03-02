package com.ecomwise.feedback.model.dto;

import lombok.Data;

@Data
public class FeedBackDTO {

    private Long id;
    private String content;
    private String sentiment;
    private String category;
    private String suggestion;
    private String timestamp;
}
