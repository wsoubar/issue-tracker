package br.com.wagner.issuetracker.domain;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TICKET")
public record Ticket(
        @Id Long id,
        String title,
        String description,
        TicketStatus status,
        Instant createdAt,
        Instant updatedAt) {
    // Exemplo de um "Wither" - um padrão moderno do Java para criar uma cópia
    // alterando apenas um campo
    // Excelente para imutabilidade (essencial em sistemas Cloud/Concorrentes)
    public Ticket withStatus(TicketStatus newStatus) {
        return new Ticket(this.id, this.title, this.description, newStatus, this.createdAt, Instant.now());
    }
}
