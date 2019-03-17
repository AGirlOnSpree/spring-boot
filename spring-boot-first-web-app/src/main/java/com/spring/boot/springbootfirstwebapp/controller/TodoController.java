package com.spring.boot.springbootfirstwebapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.springbootfirstwebapp.model.Todo;
import com.spring.boot.springbootfirstwebapp.model.TodoTemp;
import com.spring.boot.springbootfirstwebapp.service.ToDoService;

@Controller
public class TodoController {

	private Logger log = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	ToDoService service;
	
	private Todo beingUpdated;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showListOfTodo(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodo(name));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
		model.addAttribute("todotemp", new TodoTemp("DEFAULT"));
		return "todo";
	}

	
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid @ModelAttribute("todotemp") TodoTemp todotemp,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String name = getLoggedInUserName(model);
		service.addTodo(name, todotemp.getDesc(), todotemp.getTargetDate(), false);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id,ModelMap model) {
		Todo todo=service.getTodo(id);
		beingUpdated = todo;
		model.addAttribute("todotemp", new TodoTemp(todo.getDesc(),todo.getTargetDate()));
		model.put("todo", todo);
		return "todo";
	}
	
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid @ModelAttribute("todotemp") TodoTemp todotemp,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		beingUpdated.setDesc(todotemp.getDesc());
		beingUpdated.setTargetDate(todotemp.getTargetDate());
		service.updateTodo(beingUpdated);
		return "redirect:/list-todos";
	}

}
