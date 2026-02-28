package com.david.desafiolibro;

import com.david.desafiolibro.Principal.Principal;
import com.david.desafiolibro.repository.IAutorRepository;
import com.david.desafiolibro.repository.ILIbroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiolibroApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(DesafiolibroApplication.class, args);
	}
	@Autowired
	private ILIbroRepository repository;
	@Autowired
	private IAutorRepository autorRepository;

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,autorRepository);
		principal.muestraElMenu();
	}
}
