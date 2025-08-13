package br.com.damatomos.forum_hub.services;

import br.com.damatomos.forum_hub.domain.exceptions.BadRequestException;
import br.com.damatomos.forum_hub.domain.exceptions.DuplicateEntityException;
import br.com.damatomos.forum_hub.domain.users.UserModel;
import br.com.damatomos.forum_hub.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel create(UserModel model)
    {
        var user = this.userRepository.findByEmail(model.getEmail());

        if (user.isPresent())
        {
            throw new DuplicateEntityException("Já existe um usuário cadastrado com esse email");
        }

        var hash = passwordEncoder.encode(model.getPassword());
        model.setPassword(hash);

        userRepository.save(model);

        return model;
    }

    public UserDetails findByLogin(String username) {
        var user = this.userRepository.findByEmail(username);

        if (user.isEmpty())
        {
            throw new BadRequestException("Não existe usuário com esse email");
        }

        return user.get();
    }
}
