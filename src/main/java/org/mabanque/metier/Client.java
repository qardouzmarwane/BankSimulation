package org.mabanque.metier;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Client implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	private String firstname;
	private String lastname;
	private Date dateNaissance;
	private String username="";
	private String password="";
	private String rolls;
	private String email;
	private Date dateCreation;
	private boolean active;
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Collection<Compte> comptes;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String firstname, String lastname, String username, String password, String rolls, String email,
			Collection<Compte> comptes) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.rolls = rolls;
		this.email = email;
		this.comptes = comptes;
		this.active = true;
	}
	public Client(String firstname, String lastname, String username, String password, String rolls, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.rolls = rolls;
		this.email = email;
		this.active = true;
	}
	
	
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Client(String firstname, String lastname, Date dateNaissance, String username, String password, String rolls,
			String email, Collection<Compte> comptes) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.password = password;
		this.rolls = rolls;
		this.email = email;
		this.comptes = comptes;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRolls() {
		return rolls;
	}
	public void setRolls(String rolls) {
		this.rolls = rolls;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	
	
	

}
