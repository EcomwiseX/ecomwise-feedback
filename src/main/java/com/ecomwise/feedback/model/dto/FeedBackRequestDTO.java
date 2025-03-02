package com.ecomwise.feedback.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FeedBackRequestDTO {

    @NotEmpty(message = "O conteúdo do feedback não pode estar vazio.")
    @Size(min = 5, max = 500, message = "O conteúdo deve ter entre 5 e 500 caracteres.")
    private String content;
}
