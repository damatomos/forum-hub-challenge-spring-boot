package br.com.damatomos.forum_hub.domain.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserDTO(
        @NotBlank(message = "O nome deve ser informado")
        String name,

        @NotBlank(message = "O email deve ser informado")
        @Email(message = "Formatação do email inválida")
        String email,

        @NotBlank(message = "A senha precisa ser informada")
        @Pattern(regexp = "\\d{8,}")
        String password
) {
}
