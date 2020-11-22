package com.cenfotec.crud.controller;

import com.cenfotec.crud.domain.Categoria;
import com.cenfotec.crud.domain.Tarea;
import com.cenfotec.crud.domain.Workshop;
import com.cenfotec.crud.service.categorias.CategoriaService;
import com.cenfotec.crud.service.tareas.TareaService;
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
	TareaService tareaService;

	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}

	@RequestMapping(value = "/workshopsPorCat", method = RequestMethod.GET)
	public String buscarPorCat(Model model){
		model.addAttribute("categorias", categoriaService.getAll());
		model.addAttribute("categoria", new Categoria());
		return "buscarPorCat";
	}

	@RequestMapping(value = "/workshops/insertar", method = RequestMethod.GET)
	public String insertarWorkshopPage(Model model) {
		List<Categoria> categorias = categoriaService.getAll();
		if (categorias.size()==0)
			return "no-categorias";
		model.addAttribute(new Workshop());
		model.addAttribute("categorias",categorias);
		return "crear-workshop";
	}

	@RequestMapping(value = "/workshops/insertar", method = RequestMethod.POST)
	public String insertarWorkshopAction(Workshop workshop, BindingResult result, Model model) {
		workshopService.save(workshop);
		return listarWorkshops(model);
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

	@RequestMapping(value = "/categorias/{id}", method = RequestMethod.GET)
	public String editarCategoriaPage(@PathVariable("id") long id,Model model){
		model.addAttribute("categoria",categoriaService.get(id).get());
		return "editar-categoria";
	}

	@RequestMapping(value = "/categorias/{id}", method = RequestMethod.POST)
	public String editarCategoriaAction(@PathVariable("id") long id, Model model, Categoria categoria){
		categoriaService.save(categoria);
		return "categorias";
	}

	@RequestMapping(value = "/categorias/delete/{id}", method = RequestMethod.POST)
	public String deleteCategoria(@PathVariable("id") long id,Model model){
		categoriaService.delete(categoriaService.get(id).get());
//		model.addAttribute("categorias",categoriaService.getAll());
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
		return listarCategorias(model);
	}

	@RequestMapping(value = "/workshops/{id}", method = RequestMethod.GET)
	public String editarWorkshopPage(@PathVariable("id") Long id, Model model) {
		Workshop workshop = workshopService.get(id).get();
		model.addAttribute("workshop",workshop);
		model.addAttribute("categorias",categoriaService.getAll());
		List<Tarea> tareas = tareaService.getAll();
		tareas.removeIf(tarea -> tarea.getWorkshop().getId() != workshop.getId());
		model.addAttribute("tareas",tareas);
		return "editar-workshop";
	}

	@RequestMapping(value = "/workshops/{id}", method = RequestMethod.POST)
	public String editarWorkshopAction(@PathVariable("id") Long id, Model model, Workshop workshop) {
		workshopService.save(workshop);
		model.addAttribute("categorias",categoriaService.getAll());
		List<Tarea> tareas = tareaService.getAll();
		tareas.removeIf(tarea -> tarea.getWorkshop().getId() != workshop.getId());
		model.addAttribute("tareas",tareas);
		return "workshops";
	}
}
