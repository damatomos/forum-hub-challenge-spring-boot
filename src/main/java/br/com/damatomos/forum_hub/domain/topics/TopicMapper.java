package br.com.damatomos.forum_hub.domain.topics;

import br.com.damatomos.forum_hub.domain.topics.dto.CreateTopicDTO;
import br.com.damatomos.forum_hub.domain.topics.dto.ResponseTopicDetails;
import br.com.damatomos.forum_hub.domain.topics.dto.UpdateTopicDTO;
import br.com.damatomos.forum_hub.domain.users.UserMapper;
import br.com.damatomos.forum_hub.domain.users.UserModel;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

public class TopicMapper {

    public static TopicModel toModel(UserModel user, CreateTopicDTO dto)
    {
        return new TopicModel(null, dto.title(), dto.message(), true, dto.course(), user, LocalDateTime.now());
    }

    public static ResponseTopicDetails fromModel(TopicModel model)
    {
        return new ResponseTopicDetails(model.getId(), model.getTitle(), model.getMessage(), model.getCourse(), model.getStatus(), UserMapper.fromModel(model.getUser()), model.getCreatedAt());
    }

    public static TopicModel toModel(Long id, UserModel user, UpdateTopicDTO dto) {
        return new TopicModel(id, dto.title(), dto.message(), true, dto.course(), user, LocalDateTime.now());
    }
}
