package br.com.damatomos.forum_hub.controllers;

import br.com.damatomos.forum_hub.domain.users.UserMapper;
import br.com.damatomos.forum_hub.domain.users.UserRepository;
import br.com.damatomos.forum_hub.domain.users.dto.CreateUserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUserDTO dto)
    {
        // TODO: find by email

        userRepository.save(UserMapper.toModel(dto));

        return ResponseEntity.ok().build();
    }

}
