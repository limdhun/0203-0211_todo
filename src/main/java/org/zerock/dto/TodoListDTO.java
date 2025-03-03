package org.zerock.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoListDTO {

	private Integer tno;
	private String title;
	private String writer;
	
	private LocalDateTime regDate;
	
}
