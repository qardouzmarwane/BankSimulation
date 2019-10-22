package org.mabanque.service;

import org.mabanque.metier.Client;
import org.mabanque.metier.Compte;
import org.mabanque.metier.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
	
	public Compte ConsulterCompte(String codeCompte);
	public void Verser(String codeCompte, double montant);
	public void Retirer(String codeCompte, double montant);
	public void Virement(String codeCompte1, String codeCompte2, double montant);
	public Page<Operation> ConsulterOperation(String codeCompte, String codeCompte2, int page, int size);
	public Page<Client> ConsulterClient(String name, int page, int size);
	public Client ConsulterClient(Long code);
	
	

}
