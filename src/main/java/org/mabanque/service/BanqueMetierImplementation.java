package org.mabanque.service;

import java.util.Date;

import org.mabanque.dao.ClientRepository;
import org.mabanque.dao.CompteRepository;
import org.mabanque.dao.OperationRepository;
import org.mabanque.metier.Client;
import org.mabanque.metier.Compte;
import org.mabanque.metier.CompteCourant;
import org.mabanque.metier.Operation;
import org.mabanque.metier.Retrait;
import org.mabanque.metier.Versement;
import org.mabanque.metier.Virement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BanqueMetierImplementation implements IBanqueMetier{
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Override
	public Compte ConsulterCompte(String codeCompte) {
		Compte cp = compteRepository.getOne(codeCompte);
		if(cp == null) {
			throw new RuntimeException("Compte introuvable !");
		}
		return cp;
	}

	@Override
	public void Verser(String codeCompte, double montant) {
		Compte cp = ConsulterCompte(codeCompte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
		
		
	}

	@Override
	public void Retirer(String codeCompte, double montant) {
		Compte cp = ConsulterCompte(codeCompte);
		double decouvert = 0;
		if(cp instanceof CompteCourant)
			decouvert = ((CompteCourant) cp).getDecouvert();
		if((cp.getSolde()+decouvert)<montant)
			throw new RuntimeException("Solde insuffisant !!!");
		
		Retrait r = new Retrait(new Date(), -1*montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void Virement(String codeCompte1, String codeCompte2, double montant) {
		Compte cp1 = ConsulterCompte(codeCompte1);
		Compte cp2 = ConsulterCompte(codeCompte2);
		double decouvert = 0;
		if(cp1 instanceof CompteCourant)
			decouvert = ((CompteCourant) cp1).getDecouvert();
		if((cp1.getSolde()+decouvert)<montant)
			throw new RuntimeException("Solde insuffisant !!!");
		Virement v = new Virement(new Date(), montant, cp1, cp2);
		operationRepository.save(v);
		cp1.setSolde(cp1.getSolde()-montant);
		cp2.setSolde(cp2.getSolde()+montant);
		compteRepository.save(cp1);
		compteRepository.save(cp2);
	}


	@Override
	public Page<Operation> ConsulterOperation(String codeCompte, String codeCompte2, int page, int size) {
		return operationRepository.ConsulterOperation(codeCompte, codeCompte2, PageRequest.of(page, size));
	}

	@Override
	public Page<Client> ConsulterClient(String name, int page, int size) {
		return clientRepository.ConsulterClient("%"+name+"%", PageRequest.of(page, size));
		
	}

	@Override
	public Client ConsulterClient(Long code) {
		return clientRepository.getOne(code);
	}

	@Override
	public Client saveClient(Client c) {
		Client p = clientRepository.save(c);
		return p;
	}



}
