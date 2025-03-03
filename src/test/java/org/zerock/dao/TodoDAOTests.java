package org.zerock.dao;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.zerock.dto.TodoAddDTO;
import org.zerock.dto.TodoDTO;

public class TodoDAOTests {

	@Test
	public void test1()throws Exception {
		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 20; i++) {

			String now = TodoDAO.INSTANCE.makeConnection();
			
			System.out.println(now);	
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
	}
	
	//@Disabled
	@Test
	public void testInsert()throws Exception {
		
		TodoAddDTO dto = new TodoAddDTO();
		dto.setTitle("한글 Test Code Title");
		dto.setWriter("user00");
		
		TodoDAO.INSTANCE.insert(dto);
		
	}
	
	@Test
	public void testList()throws Exception{
		
		System.out.println(TodoDAO.INSTANCE.list(1));
		
	}
	
	@Test
	public void testSelectOne()throws Exception {
		
		int tno = 234234;
		
		TodoDTO dto = TodoDAO.INSTANCE.selectOne(tno);
		
		System.out.println(dto);
		
	}
	
	
}










