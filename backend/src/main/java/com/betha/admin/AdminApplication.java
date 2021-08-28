package com.betha.admin;

import com.betha.admin.model.Saida;
import com.betha.admin.model.TipoTransacao;
import com.betha.admin.repository.SaidaRepository;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.tools.jar.CommandLine;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class AdminApplication {

	private static final Logger log = LoggerFactory.getLogger(AdminApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(SaidaRepository repository) {
		LocalDate date = LocalDate.now();
		return (args -> {
			repository.save(new Saida(date, 100.00, TipoTransacao.PESSOAL, "teste"));
			log.info("FIND ALL ->");
			for (Saida saida : repository.findAll()) {
				log.info(saida.toString());
			}
			log.info("FIND BY ID (1L)");
			Saida saida = repository.findById(1L);
			log.info(saida.toString());
		});
	}

}
