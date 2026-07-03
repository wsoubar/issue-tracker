package br.com.wagner.issuetracker.service;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wagner.issuetracker.domain.Ticket;
import br.com.wagner.issuetracker.domain.TicketStatus;
import br.com.wagner.issuetracker.repository.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository repository;

    // Injeção de dependência via construtor (Best Practice do Spring atual).
    // Esqueça o @Autowired em atributos, isso dificulta testes unitários.
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Ticket criarTicket(Ticket ticketRequest) {
        // Garantimos as regras de negócio iniciais antes de salvar
        Ticket novoTicket = new Ticket(
                null, // ID gerado pelo banco
                ticketRequest.title(),
                ticketRequest.description(),
                TicketStatus.OPEN, // Todo ticket nasce ABERTO
                Instant.now(),
                null);

        // 1. O Spring Data JDBC dispara o INSERT no banco de dados aqui.
        Ticket ticketSalvo = repository.save(novoTicket);

        // 2. Simulamos uma regra de negócio que quebra APÓS a inserção
        if (ticketRequest.description() != null && ticketRequest.description().contains("FORBIDDEN")) {
            // Lançar uma RuntimeException (ou herdeiras dela) marca a transação para
            // Rollback automático
            throw new RuntimeException("Regra de negócio violada! Desfazendo a transação (Rollback).");
        }

        return ticketSalvo;
    }

    @Transactional(readOnly = true) // Otimiza a query, dizendo ao banco que não haverá lock de escrita
    public Iterable<Ticket> listarTodos() {
        return repository.findAll();
    }
}