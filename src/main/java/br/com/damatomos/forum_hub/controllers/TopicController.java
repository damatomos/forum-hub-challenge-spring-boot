package br.com.damatomos.forum_hub.controllers;

import br.com.damatomos.forum_hub.domain.topics.TopicMapper;
import br.com.damatomos.forum_hub.domain.topics.TopicRepository;
import br.com.damatomos.forum_hub.domain.topics.dto.CreateTopicDTO;
import br.com.damatomos.forum_hub.domain.topics.dto.ResponseTopicDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<ResponseTopicDetails> create(@RequestBody @Valid CreateTopicDTO dto)
    {
        var topic = topicRepository.save(TopicMapper.toModel(dto));

        return ResponseEntity.ok(TopicMapper.fromModel(topic));
    }

}
