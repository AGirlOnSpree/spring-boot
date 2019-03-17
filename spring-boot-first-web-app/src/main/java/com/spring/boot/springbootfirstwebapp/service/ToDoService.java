package com.spring.boot.springbootfirstwebapp.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.boot.springbootfirstwebapp.model.Todo;

@Service
public class ToDoService {
	
	private static List<Todo> toDos=new ArrayList<Todo>();
	private static int toDoCount=3;
	
	static
	{
		toDos.add(new Todo(1, "Vinita", "Learn Core Java", new Date(new Long("01012019")), false));
		toDos.add(new Todo(2, "Vinita", "Learn Advanced Java", new Date(new Long("01012019")), false));
		toDos.add(new Todo(3, "Vinita", "Learn Spring", new Date(new Long("01012019")), false));

	}
	
	public List<Todo> retrieveTodo(String user)
	{
		List<Todo> toDoForTheUser=new ArrayList<Todo>();
		for(Todo todo:toDos)
		{
			if(todo.getUser().equals(user))
				toDoForTheUser.add(todo);
		}
		return toDoForTheUser;
		
	}
	
	public void addTodo(String user,String desc,Date targetDate,boolean isDone) {
		toDos.add(new Todo(++toDoCount, user, desc, targetDate, isDone));
	}
	
	public void deleteTodo(int id)
	{
		Iterator<Todo> iterator=toDos.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getId()==id) {
				iterator.remove();
			}
		}
	}
	
	public Todo getTodo(int id)
	{
		for(Todo todo:toDos)
		{
			if(todo.getId()==id)
				return todo;
		}
		return null;
		
	}
	
	public void updateTodo(Todo todo) {
		toDos.remove(todo);
		toDos.add(todo);
	}

}
