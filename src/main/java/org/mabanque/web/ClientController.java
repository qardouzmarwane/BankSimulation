package org.mabanque.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mabanque.metier.Client;
import org.mabanque.service.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
	@Autowired
	IBanqueMetier banqueMetier;

	
	@RequestMapping("/consulterClient")
	public String ConsulterClients(Model model, 
			@RequestParam(defaultValue = "") String clientName,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size",defaultValue = "10") int size)
	{
		Page<Client> listClients = banqueMetier.ConsulterClient(clientName, page, size);

		int pageCount = listClients.getTotalPages();
		int [] pages = new int[pageCount];
		for(int i=0; i<pageCount; i++) pages[i]=i+1;
		
		
		model.addAttribute("listClients", listClients);
		model.addAttribute("pageCourante",page);
		model.addAttribute("pages", pages);
		
		
		return "clients";
		
	}

	@RequestMapping("/clients")
	public String pageClients()
	{
		return "clients";
	}
	
	@RequestMapping("/DetailsClient")
	public String DetailsClient(Model model, Long codeClient)
	{
		Client c = banqueMetier.ConsulterClient(codeClient);
		model.addAttribute("ClientInfos", c);
		return "detailsclient";
	}
	
	@RequestMapping("/creationClient")
	public String creationClient()
	{
		return "creationclient";
	}
	
	@RequestMapping(value = "/saveClient", method = RequestMethod.POST)
	public String saveClient(Model model, String lastname, String firstname, String email, String username, String password, String dateNaissance) throws ParseException
	{

		
		System.out.println(dateNaissance);
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		Date dateN = d.parse(dateNaissance);
		try {
			Client c = new Client(firstname, lastname, dateN, username, password, "USER", email);
			Client p = banqueMetier.saveClient(c);
			model.addAttribute("ClientInfos", p);
			return "detailsclient";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "error";
	}
	
	
	
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("/")
	public String index()
	{
		return "redirect:/login";	
	}
	
		
	
}
