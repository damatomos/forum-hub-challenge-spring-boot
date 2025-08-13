package br.com.damatomos.forum_hub.domain.topics.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTopicDTO(
        @NotBlank(message = "O título precisa ser informado")
        String title,

        @NotBlank(message = "Uma mensagem precisa ser informada")
        String message,

        @NotBlank(message = "O curso deve ser informado")
        String course
) {
}
