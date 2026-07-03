package br.com.wagner.issuetracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.wagner.issuetracker.domain.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    // O Spring Data JDBC vai gerar o SQL de CRUD básico automaticamente para nós
    // aqui.
}