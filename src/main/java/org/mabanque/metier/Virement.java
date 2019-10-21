package org.mabanque.metier;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("VI")
public class Virement extends Operation{
	@ManyToOne
	@JoinColumn(name = "CODE_COMPTE2")
	private Compte compte2;

	public Virement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Virement(Date dateOperation, double montant, Compte compte, Compte compte2) {
		super(dateOperation, montant, compte);
		this.compte2 = compte2;
	}

	public Compte getCompte2() {
		return compte2;
	}

	public void setCompte2(Compte compte2) {
		this.compte2 = compte2;
	}
	
	

}
