package br.com.damatomos.forum_hub.services;

import br.com.damatomos.forum_hub.domain.users.UserModel;
import br.com.damatomos.forum_hub.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel create(UserModel model)
    {
        var user = this.userRepository.findByEmail(model.getEmail());

        if (user.isPresent())
        {
            throw new RuntimeException("Já existe um usuário cadastrado com esse email");
        }

        userRepository.save(model);

        return model;
    }

}
