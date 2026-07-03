package br.com.wagner.issuetracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wagner.issuetracker.domain.Ticket;
import br.com.wagner.issuetracker.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket criarTicket(@RequestBody Ticket ticket) {
        return ticketService.criarTicket(ticket);
    }

    @GetMapping
    public Iterable<Ticket> listar() {
        return ticketService.listarTodos();
    }

}
