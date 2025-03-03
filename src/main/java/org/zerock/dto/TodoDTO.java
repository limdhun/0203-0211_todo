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
public class TodoDTO {

	private int tno;
	private String title;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	
}
