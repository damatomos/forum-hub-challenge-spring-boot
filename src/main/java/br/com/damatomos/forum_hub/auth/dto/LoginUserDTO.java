package br.com.damatomos.forum_hub.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginUserDTO(

        @NotBlank(message = "O email precisa ser informado")
        @Email(message = "A formatação do email é inválida")
        String email,

        @NotBlank(message = "A senha precisa ser informada")
        @Pattern(regexp = "\\d{8,}", message = "A senha precisa ter no mínimo 8 caracteres")
        String password
) {
}
