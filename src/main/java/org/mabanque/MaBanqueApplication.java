package org.mabanque;

import org.mabanque.dao.ClientRepository;
import org.mabanque.dao.CompteRepository;
import org.mabanque.dao.OperationRepository;
import org.mabanque.service.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MaBanqueApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		/*
		 * banqueMetier.Verser("c1", 1111111111);
		 * 
		 * 
		 * Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000,
		 * c1, 500)); Compte cp2 = compteRepository.save(new CompteEpargne("c2", new
		 * Date(), 15422000, c2, 5.5)); Compte cp3 = compteRepository.save(new
		 * CompteCourant("c3", new Date(), 520000, c2, 600));
		 * 
		 * operationRepository.save(new Versement(new Date(), 1200, cp1));
		 * operationRepository.save(new Versement(new Date(), 2500, cp1));
		 * operationRepository.save(new Versement(new Date(), 500, cp1));
		 * operationRepository.save(new Versement(new Date(), 150, cp1));
		 * operationRepository.save(new Retrait(new Date(), 1500, cp1));
		 * 
		 * 
		 * operationRepository.save(new Versement(new Date(), 1200, cp2));
		 * operationRepository.save(new Versement(new Date(), 2500, cp2));
		 * operationRepository.save(new Retrait(new Date(), 5600, cp2));
		 * operationRepository.save(new Retrait(new Date(), 5600, cp2));
		 * operationRepository.save(new Versement(new Date(), 200, cp2));
		 * operationRepository.save(new Retrait(new Date(), 582, cp2));
		 * 
		 * 
		 * operationRepository.save(new Virement(new Date(),52000, cp1, cp2));
		 */

	}

}
