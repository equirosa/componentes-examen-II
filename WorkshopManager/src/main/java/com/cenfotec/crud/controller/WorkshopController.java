package com.cenfotec.crud.controller;

import com.cenfotec.crud.domain.Categoria;
import com.cenfotec.crud.domain.Tarea;
import com.cenfotec.crud.domain.Workshop;
import com.cenfotec.crud.service.categorias.CategoriaService;
import com.cenfotec.crud.service.tareas.TareaService;
import com.cenfotec.crud.service.workshops.WorkshopService;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

	@RequestMapping(value = "/export/{id}")
	public String exportarWord(Model model, @PathVariable long id) throws IOException {
		Optional<Workshop> workshop = workshopService.get(id);
		if (workshop.isPresent()) {
			int duration=0;
			String tareas;
			for (Tarea tarea: workshop.get().getTareas()) {
				duration+=tarea.getDuration();
			}
			XWPFDocument document = new XWPFDocument();
			String output = "Workshop_" + workshop.get().getName() + ".docx";
			XWPFParagraph title = document.createParagraph();
			title.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun titleRun = title.createRun();
			titleRun.setText(workshop.get().getName());
			titleRun.setColor("000000");
			titleRun.setBold(true);
			titleRun.setFontFamily("Arial");
			titleRun.setFontSize(20);

			XWPFParagraph title2=document.createParagraph();
			title2.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun titleRun2 = title2.createRun();
			titleRun2.setText(new StringBuilder().append("Duracion del taller= ").append(duration).append(" minutos").toString());
			titleRun2.setColor("000000");
			titleRun2.setBold(true);
			titleRun2.setFontFamily("Arial");
			titleRun2.setFontSize(20);

			XWPFParagraph subTitle = document.createParagraph();
			subTitle.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun subTitleRun = subTitle.createRun();
			subTitleRun.setText("Autor del taller= "+workshop.get().getAutor());
			titleRun2.setColor("000000");
			titleRun2.setBold(true);
			titleRun2.setFontFamily("Arial");
			titleRun2.setFontSize(20);

			XWPFParagraph sectionTitle = document.createParagraph();
			XWPFRun sectionTRun = sectionTitle.createRun();
			sectionTRun.setText("Categoria "+workshop.get().getCategoria().getName());
			sectionTRun.setColor("009933");
			sectionTRun.setBold(true);
			sectionTRun.setFontFamily("Courier");

			for (Tarea tarea: workshop.get().getTareas()) {
				XWPFParagraph sub = document.createParagraph();
				sub.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun subTitleRu = sub.createRun();
				subTitleRu.setColor("111111");
				subTitleRu.setFontFamily("Courier");
				subTitleRu.setFontSize(16);
				subTitleRu.setTextPosition(20);
				subTitleRu.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
				tareas= tarea.getName()+"\n"+
						"Descripción= "+tarea.getDescription()+"\n"+
						"Notas de la actividad= "+tarea.getTexto() +"\n";
				subTitleRun.setText(tareas);

			}

			FileOutputStream out = new FileOutputStream(output);
			document.write(out);
			out.close();
			document.close();
			return "workshops";
		} else {
			return "error";
		}
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
