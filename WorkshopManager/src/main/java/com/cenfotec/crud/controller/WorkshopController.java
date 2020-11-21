package com.cenfotec.crud.controller;

import com.cenfotec.crud.domain.Autor;
import com.cenfotec.crud.domain.Categoria;
import com.cenfotec.crud.domain.Workshop;
import com.cenfotec.crud.service.autores.AutorService;
import com.cenfotec.crud.service.categorias.CategoriaService;
import com.cenfotec.crud.service.workshops.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WorkshopController {

	@Autowired
	WorkshopService workshopService;

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	AutorService autorService;

	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}

	@RequestMapping("/autores")
	public String listarAutores(Model model){
		return "autores";
	}

	@RequestMapping(value = "/autores/insertar", method = RequestMethod.GET)
	public String insertarAutorPage(Model model) {
		return "crear-autor";
	}

	@RequestMapping(value = "/autores/insertar", method = RequestMethod.POST)
	public String insertarAutorAction(Autor autor, BindingResult result, Model model){
		autorService.save(autor);
		return "/autores";
	}

	@RequestMapping(value = "/workshops/insertar", method = RequestMethod.GET)
	public String insertarWorkshopPage(Model model) {
		List<Categoria> categorias = categoriaService.getAll();
		if (categorias.size()==0)
			return "no-categorias";
		List<Autor> autores = autorService.getAll();
		if (autores.size()==0)
			return "no-autores";
		model.addAttribute(new Workshop());
		return "crear-workshop";
	}

	@RequestMapping(value = "/workshops/insertar", method = RequestMethod.POST)
	public String insertarWorkshopAction(Workshop workshop, BindingResult result, Model model) {
		workshopService.save(workshop);
		return "workshops";
	}

	@RequestMapping("/workshops")
	public String listarWorkshops(Model model) {
		model.addAttribute("workshops", workshopService.getAll());
		return "workshops";
	}

	@RequestMapping("/categorias")
	public String listarCategorias(Model model) {
		model.addAttribute("categorias",categoriaService.getAll());
		return "categorias";
	}

	@RequestMapping(value = "/categorias/insertar", method = RequestMethod.GET)
	public String insertarCategoriaPage(Model model){
		model.addAttribute(new Categoria());
		return "crear-categoria";
	}

	@RequestMapping(value = "/categorias/insertar", method = RequestMethod.POST)
	public String insertarCategoriaAction(Categoria categoria, BindingResult result,Model model) {
		categoriaService.save(categoria);
		return "/categorias";
	}

	@RequestMapping("/listar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		model.addAttribute(workshopService.get(id));
		return "listar";
	}
}
