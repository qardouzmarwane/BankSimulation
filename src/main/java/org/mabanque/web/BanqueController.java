package org.mabanque.web;

import org.mabanque.metier.Compte;
import org.mabanque.metier.Operation;
import org.mabanque.service.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping("/comptes")
	public String index()
	{
		return "login";
	}
	
	@RequestMapping("/consulterCompte")
	public String consulter(Model model, String codeCompte, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue="6") int size)
	{

			Compte cp = banqueMetier.ConsulterCompte(codeCompte);
			Page <Operation>listOperations = banqueMetier.ConsulterOperation(codeCompte,codeCompte, page, size);
			int pageCount = listOperations.getTotalPages();
			int [] pages = new int[pageCount];
			for(int i=0; i<pageCount; i++) pages[i]=i+1;
			
			model.addAttribute("pages",pages);
			model.addAttribute("compte",cp);
			model.addAttribute("listOperations",listOperations);
			model.addAttribute("pageCourante", page);
			model.addAttribute("codeCompte", cp.getCodeCompte());

		return "comptes";
	}
	
	@RequestMapping(value = "/saveOperation",method = RequestMethod.POST)
	public String saveOperation(Model model, String codeCompte, double montant, String typeOperation, String codeCompte2)
	{
		try {
		if(typeOperation.equals("versement"))
			banqueMetier.Verser(codeCompte, montant);
		if(typeOperation.equals("retrait"))
			banqueMetier.Retirer(codeCompte, montant);
		if(typeOperation.equals("virement"))
			banqueMetier.Virement(codeCompte, codeCompte2, montant);

		}catch(Exception e) {
			return "error";
		}
		
			return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
	

}
