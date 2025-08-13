package br.com.damatomos.forum_hub.auth;

import br.com.damatomos.forum_hub.domain.users.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserModel user)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Forum Hub")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationTime())
                    .sign(algorithm);
        } catch (Exception e)
        {
            throw new RuntimeException("Error ao gerar token jwt", e);
        }
    }

    public String getSubject(String token)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Forum Hub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e)
        {
            throw new RuntimeException("Token JWT inválido");
        }
    }

    private Instant expirationTime() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }

}
