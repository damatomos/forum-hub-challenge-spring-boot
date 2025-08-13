package br.com.damatomos.forum_hub.domain.users;

import br.com.damatomos.forum_hub.domain.users.dto.CreateUserDTO;
import br.com.damatomos.forum_hub.domain.users.dto.ResponseUserDTO;

import java.util.List;

public class UserMapper {

    public static UserModel toModel(CreateUserDTO dto)
    {
        return new UserModel(null, dto.name(), dto.email(), dto.password(), List.of());
    }

    public static ResponseUserDTO fromModel(UserModel user) {
        return new ResponseUserDTO(user.getName());
    }
}
