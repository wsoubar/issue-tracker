package br.com.wagner.issuetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IssueTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerApplication.class, args);
	}

	// @Bean
	// CommandLineRunner initDatabase(TicketRepository repository) {
	// return args -> {
	// // Criando nosso primeiro registro usando o construtor do Record
	// Ticket novoTicket = new Ticket(
	// null, // ID nulo para o banco gerar via AUTO_INCREMENT
	// "Bug na tela de login",
	// "O botão de submit não está funcionando no navegador Firefox.",
	// TicketStatus.OPEN,
	// Instant.now(),
	// null);

	// Ticket ticketSalvo = repository.save(novoTicket);
	// System.out.println("=========================================");
	// System.out.println("Ticket criado com sucesso! ID nativo: " +
	// ticketSalvo.id());
	// System.out.println("=========================================");
	// };
	// }
}
