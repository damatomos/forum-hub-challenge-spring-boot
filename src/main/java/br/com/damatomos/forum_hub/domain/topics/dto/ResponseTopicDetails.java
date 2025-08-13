package br.com.damatomos.forum_hub.domain.topics.dto;

import br.com.damatomos.forum_hub.domain.topics.TopicModel;
import br.com.damatomos.forum_hub.domain.users.dto.ResponseUserDTO;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public record ResponseTopicDetails(
        Long id,
        String title,
        String message,
        String course,
        Boolean status,
        ResponseUserDTO user,

        @JsonAlias("created_at")
        LocalDateTime createdAt
) {
}
