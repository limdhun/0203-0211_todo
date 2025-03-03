package org.zerock.dto;

/**
 * Todo등록시에 제목과 작성자를 수집해서 전달하기 위한 용도로 제작된 클래스
 * 파라미터 타입 , 리턴 타입이 되기도 함 
 */

import lombok.Data;

@Data

public class TodoAddDTO {

	private String title;
	private String writer;
	
}
