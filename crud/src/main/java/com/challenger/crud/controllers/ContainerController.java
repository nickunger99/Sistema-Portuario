package com.challenger.crud.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.challenger.crud.models.Container;
import com.challenger.crud.models.Movimento;
import com.challenger.crud.repository.ContainerRepository;
import com.challenger.crud.repository.MovimentoRepository;

@Controller
public class ContainerController {

	@Autowired
	private ContainerRepository cr;
	
	@Autowired
	private MovimentoRepository mr;
	
	// GET que chama o form para cadastrar container
	@RequestMapping("/cadastrarContainer")
	public String form() {
		return "container/form-container";
	}

	// POST que cadastra containers
	@RequestMapping(value = "/cadastrarContainer", method = RequestMethod.POST)
	public String form(@Valid Container container, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarContainer";
		}
		
		if(cr.findByNumero(container.getNumero()) != null) {
			attributes.addFlashAttribute("mensagem_erro", "Container duplicado");
			return "redirect:/cadastrarContainer";
		}
		

		cr.save(container);
		attributes.addFlashAttribute("mensagem", "Container cadastrado com sucesso!");
		return "redirect:/cadastrarContainer";
	}

	// GET que lista containers
	@RequestMapping("/containers")
	public ModelAndView listaContainers() {
		ModelAndView mv = new ModelAndView("container/lista-container");
		Iterable<Container> containers = cr.findAll();
		mv.addObject("containers", containers );
		return mv;
	}
	
	
	// GET que lista movimentos e detalhes dos containers
		@RequestMapping("/detalhes-container/{id}")
		public ModelAndView detalhesContainer(@PathVariable("id") long id) {
			Container container = cr.findById(id);
			ModelAndView mv = new ModelAndView("container/detalhes-container");
			mv.addObject("containers", container);

			// lista de movimentos baseada no id do funcionário
			Iterable<Movimento> movimentos = mr.findByContainer(container);
			mv.addObject("movimentos", movimentos);

			return mv;

		}

		// POST que adiciona movimento
		@RequestMapping(value="/detalhes-container/{id}", method = RequestMethod.POST)
		public String detalhesContainerPost(@PathVariable("id") long id, Movimento movimentos, BindingResult result,
				RedirectAttributes attributes) {
			
			if(result.hasErrors()) {
				attributes.addFlashAttribute("mensagem", "Verifique os campos!");
				return "redirect:/detalhes-container/{id}";
			}
			
			Container container = cr.findById(id);
			movimentos.setContainer(container);
			mr.save(movimentos);
			attributes.addFlashAttribute("mensagem", "Movimento adicionado com sucesso");
			return "redirect:/detalhes-container/{id}";
			
		}
	


	//GET que deleta container
	@RequestMapping("/deletarContainer")
	public String deletarContainer(long id) {
		Container container = cr.findById(id);
		cr.delete(container);
		return "redirect:/containers";
		
	}
	
	// Métodos que atualizam container
	// GET que chama o FORM de edição do container
	@RequestMapping("/editar-container")
	public ModelAndView editarContainer(long id) {
		Container container = cr.findById(id);
		ModelAndView mv = new ModelAndView("container/update-container");
		mv.addObject("/container", container);
		return mv;
	}
	
	// POST que atualiza o container
	@RequestMapping(value = "/editar-container", method = RequestMethod.POST)
	public String updateContainer(@Valid Container container,  BindingResult result, RedirectAttributes attributes){
		
		cr.save(container);
		attributes.addFlashAttribute("success", "Container alterado com sucesso!");
		
		long idLong = container.getId();
		String id = "" + idLong;
		
		return "redirect:/detalhes-container/" + id;
		
	}
	
	// GET que deleta movimento
	@RequestMapping("/deletarMovimento")
	public String deletarMovimento(long id) {
		Movimento movimento = mr.findById(id);
		
		Container container = movimento.getContainer();
		String codigo = "" + container.getId();
		
		mr.delete(movimento);
		return "redirect:/detalhes-container/" + codigo;
	
	}
	
	
	
	
}
