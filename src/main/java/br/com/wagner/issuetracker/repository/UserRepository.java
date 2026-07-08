package br.com.wagner.issuetracker.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.wagner.issuetracker.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // O Spring Data JDBC gerará automaticamente o SQL: SELECT * FROM APP_USER WHERE
    // username = ?
    Optional<User> findByUsername(String username);

}
