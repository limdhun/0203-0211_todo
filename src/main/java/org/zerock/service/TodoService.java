package org.zerock.service;

import org.zerock.dao.TodoDAO;
import org.zerock.dto.TodoAddDTO;
import org.zerock.dto.TodoDTO;
import org.zerock.dto.TodoListDTO;

public enum TodoService {
	
	INSTANCE;
	
	public void add(TodoAddDTO dto)throws Exception{
	
		TodoDAO.INSTANCE.insert(dto);
		
	}
	
	public java.util.List<TodoListDTO> getList(int page) throws Exception {
		
		return TodoDAO.INSTANCE.list(page);
	}
	
	public int getTotal()throws Exception {
		
		return TodoDAO.INSTANCE.getTotal();
	}
	
	public TodoDTO read(int tno)throws Exception {
		
		return TodoDAO.INSTANCE.selectOne(tno);
		
	}
	
	public void modify(TodoDTO dto ) throws Exception {
		
		TodoDAO.INSTANCE.update(dto);
	}
	
	public void remove(int tno)throws Exception {
		
		TodoDAO.INSTANCE.delete(tno);
	}
	
}






