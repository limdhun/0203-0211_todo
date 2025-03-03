package org.zerock.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingDTO {

	private int start; //페이지 시작 번호 
	
	private int end; //마지막 페이지 번호
	
	private int current; //현재 페이지 번호
	
	private boolean prev;
	
	private boolean next;
	
	private int total;
	
	public PagingDTO(int pageNum, int total) {
		
		this.total = total;
		
		this.current = pageNum;
		
		int tempEnd = (int)(Math.ceil( pageNum / 10.0) * 10); 
		
		this.start = tempEnd - 9; //11
		
		if(this.start != 1) {
			this.prev = true;
		}
		
		int realEnd = (int)( Math.ceil(total/ 10.0) );
		
		if(realEnd > tempEnd) {
			this.next = true;
		}
		
		if(realEnd < tempEnd) {
			this.end = realEnd;
		}else {
			this.end = tempEnd;
		}
		
		
		
	}
	
}



