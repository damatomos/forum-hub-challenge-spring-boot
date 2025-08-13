package br.com.damatomos.forum_hub.controllers;

import br.com.damatomos.forum_hub.domain.topics.TopicMapper;
import br.com.damatomos.forum_hub.domain.topics.TopicRepository;
import br.com.damatomos.forum_hub.domain.topics.dto.CreateTopicDTO;
import br.com.damatomos.forum_hub.domain.topics.dto.ResponseTopicDetails;
import br.com.damatomos.forum_hub.domain.topics.dto.UpdateTopicDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<Page<ResponseTopicDetails>> findAll(@PageableDefault(size = 10, page = 0, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        var page = topicRepository.findAll(pageable).map(TopicMapper::fromModel);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateTopicDTO dto)
    {
        if (!topicRepository.existsById(id))
        {
            throw new RuntimeException("Não existe tópico com o id informado");
        }

        var model = TopicMapper.toModel(id, dto);

        topicRepository.save(model);

        return ResponseEntity.ok().build();
    }

}
