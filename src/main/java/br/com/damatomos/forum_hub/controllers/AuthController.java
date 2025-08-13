package br.com.damatomos.forum_hub.controllers;

import br.com.damatomos.forum_hub.domain.users.UserMapper;
import br.com.damatomos.forum_hub.domain.users.dto.CreateUserDTO;
import br.com.damatomos.forum_hub.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid CreateUserDTO dto, ServletUriComponentsBuilder urlBuilder)
    {
        var user = this.userService.create(UserMapper.toModel(dto));
        var uri = urlBuilder.path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
