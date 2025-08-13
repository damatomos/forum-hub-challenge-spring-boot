package br.com.damatomos.forum_hub.controllers;

import br.com.damatomos.forum_hub.auth.TokenService;
import br.com.damatomos.forum_hub.auth.dto.LoginUserDTO;
import br.com.damatomos.forum_hub.auth.dto.ResponseTokenAccessDTO;
import br.com.damatomos.forum_hub.domain.users.UserMapper;
import br.com.damatomos.forum_hub.domain.users.UserModel;
import br.com.damatomos.forum_hub.domain.users.dto.CreateUserDTO;
import br.com.damatomos.forum_hub.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid CreateUserDTO dto, ServletUriComponentsBuilder urlBuilder)
    {
        var user = this.userService.create(UserMapper.toModel(dto));
        var uri = urlBuilder.path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseTokenAccessDTO> login(@RequestBody @Valid LoginUserDTO dto)
    {
        var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(token);

        var tokenJwt = tokenService.generateToken((UserModel) authentication.getPrincipal());

        return ResponseEntity.ok(new ResponseTokenAccessDTO(tokenJwt, "Bearer"));
    }

}
