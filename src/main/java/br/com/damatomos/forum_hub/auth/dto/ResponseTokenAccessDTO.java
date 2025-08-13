package br.com.damatomos.forum_hub.auth.dto;

public record ResponseTokenAccessDTO(
        String token,
        String type
) {
}
