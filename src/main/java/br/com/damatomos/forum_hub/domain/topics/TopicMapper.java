package br.com.damatomos.forum_hub.domain.topics;

import br.com.damatomos.forum_hub.domain.topics.dto.CreateTopicDTO;
import br.com.damatomos.forum_hub.domain.topics.dto.ResponseTopicDetails;
import br.com.damatomos.forum_hub.domain.topics.dto.UpdateTopicDTO;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

public class TopicMapper {

    public static TopicModel toModel(CreateTopicDTO dto)
    {
        return new TopicModel(null, dto.title(), dto.message(), true, dto.course(), LocalDateTime.now());
    }

    public static ResponseTopicDetails fromModel(TopicModel model)
    {
        return new ResponseTopicDetails(model.getId(), model.getTitle(), model.getMessage(), model.getCourse(), model.getCreatedAt());
    }

    public static TopicModel toModel(Long id, UpdateTopicDTO dto) {
        return new TopicModel(id, dto.title(), dto.message(), true, dto.course(), LocalDateTime.now());
    }
}
