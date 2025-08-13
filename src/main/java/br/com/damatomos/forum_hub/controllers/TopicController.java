package br.com.damatomos.forum_hub.controllers;

import br.com.damatomos.forum_hub.domain.topics.TopicMapper;
import br.com.damatomos.forum_hub.domain.topics.dto.CreateTopicDTO;
import br.com.damatomos.forum_hub.domain.topics.dto.ResponseTopicDetails;
import br.com.damatomos.forum_hub.domain.topics.dto.UpdateTopicDTO;
import br.com.damatomos.forum_hub.domain.users.UserModel;
import br.com.damatomos.forum_hub.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<ResponseTopicDetails> create(@RequestBody @Valid CreateTopicDTO dto, ServletUriComponentsBuilder uriBuilder)
    {
        var user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var topic = topicService.create(TopicMapper.toModel(user, dto));

        var uri = uriBuilder.path("{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(TopicMapper.fromModel(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTopicDetails> findOne(@PathVariable("id") Long id)
    {
        var topic = topicService.findById(id);
        return ResponseEntity.ok(TopicMapper.fromModel(topic));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseTopicDetails>> findAll(@PageableDefault(size = 10, page = 0, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable)
    {
        var page = topicService.findAll(pageable).map(TopicMapper::fromModel);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTopicDetails> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateTopicDTO dto)
    {
        var user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var topic = this.topicService.update(TopicMapper.toModel(id, user, dto));

        return ResponseEntity.ok(TopicMapper.fromModel(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    {
        var user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        this.topicService.delete(id, user);

        return ResponseEntity.noContent().build();
    }

}
