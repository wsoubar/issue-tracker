package br.com.wagner.issuetracker.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.wagner.issuetracker.domain.User;

@Service
public class TokenService {
    // A chave secreta não deve ficar hardcoded no código fonte real.
    // Estamos injetando do application.yml
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            // Aqui você pode usar uma biblioteca como java-jwt para gerar o token JWT
            // Exemplo:
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("issue-tracker-api")
                    .withSubject(user.username())
                    .withClaim("role", user.role()) // Injetando o papel (role) no payload do token
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("issue-tracker-api")
                    .build()
                    .verify(token)
                    .getSubject(); // Retorna o username se o token for válido e não estiver expirado
        } catch (JWTVerificationException exception) {
            return ""; // Retorna string vazia se o token for inválido (o Spring Security barrará
                       // depois)
        }
    }

    private Instant genExpirationDate() {
        // Token expira em 2 horas. No Brasil usamos o offset de -03:00.
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
