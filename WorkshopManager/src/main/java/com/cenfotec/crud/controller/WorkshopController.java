package com.cenfotec.crud.controller;

import com.cenfotec.crud.domain.Workshop;
import com.cenfotec.crud.service.workshops.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WorkshopController {

	@Autowired
	WorkshopService workshopService;

//	@Autowired
//	CategoriaService categoriaService;

	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}

	@RequestMapping(value = "/workshops/insertar", method = RequestMethod.GET)
	public String insertarPage(Model model) {
//		List<Categoria> categorias = categoriaService.getAll();
//		if (categorias.size()==0)
//			return "no-categorias";
		model.addAttribute(new Workshop());
		return "crear-workshop";
	}

	@RequestMapping(value = "/workshops/insertar", method = RequestMethod.POST)
	public String insertarAction(Workshop workshop, BindingResult result, Model model) {
		workshopService.save(workshop);
		return "workshops";
	}

	@RequestMapping("/workshops")
	public String listar(Model model) {
		model.addAttribute("workshops", workshopService.getAll());
		return "workshops";
	}

	@RequestMapping("/listar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		model.addAttribute(workshopService.get(id));
		return "listar";
	}
}
