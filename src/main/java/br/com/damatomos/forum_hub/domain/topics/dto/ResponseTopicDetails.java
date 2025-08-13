package br.com.damatomos.forum_hub.domain.topics.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public record ResponseTopicDetails(
        Long id,
        String title,
        String message,
        String course,
        Boolean status,
        String author,

        @JsonAlias("created_at")
        LocalDateTime createdAt
) {
}
