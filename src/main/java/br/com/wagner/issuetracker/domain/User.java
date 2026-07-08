package br.com.wagner.issuetracker.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("APP_USER")
public record User(
        @Id Long id,
        String username,
        String password, // Aqui ficará a senha criptografada com BCrypt
        String role // Ex: "ROLE_ADMIN", "ROLE_DEVELOPER"
) {
}
