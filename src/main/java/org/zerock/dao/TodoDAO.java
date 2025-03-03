package org.zerock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.zerock.dto.TodoAddDTO;
import org.zerock.dto.TodoDTO;
import org.zerock.dto.TodoListDTO;

import lombok.Cleanup;

public enum TodoDAO {

	INSTANCE;

	// bad code
	public String makeConnection() throws Exception {

		@Cleanup
		Connection conn = ConnectionUtil.INSTANCE.getConnection();

		System.out.println(conn);

		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement("select now()"); // 문자열 ; 없도록 주의

		@Cleanup
		ResultSet resultSet = pstmt.executeQuery();

		resultSet.next();

		return resultSet.getString(1);
	}

	public void insert(TodoAddDTO todoDTO) throws Exception {

		// 마지막 ; 주의
		// 값을 제외한 순수한 SQL문 :v1 :v2
		String sql = "insert into tbl_todo (title,writer) values (?,?)";

		@Cleanup
		Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, todoDTO.getTitle());
		pstmt.setString(2, todoDTO.getWriter());

		// DML - insert, update, delete 결과가 몇 개의 행이 영향을 받았는지
		int count = pstmt.executeUpdate();

		if (count != 1) {
			throw new Exception("INSERT ERROR NOT 1");
		}

		// finally
		// pstmt.close()
		// conn.close()

	}

	public List<TodoListDTO> list(int page) throws Exception {

		//마지막에 ; 주의 없어야 함 
		String query = """
				select
					tno, title, writer, regdate
				from
					tbl_todo
				where
					tno > 0
				order by
				  tno desc
				limit 10 OFFSET ? """;
		
		@Cleanup
		Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(query);
		//물음표에 값 할당 - DB에서는 SQL을 컴파일이 완료된 후에 전달한 값을 파라미터화 
		pstmt.setInt(1, (page - 1) * 10);
		
		@Cleanup
		ResultSet rs = pstmt.executeQuery();
		
		
		List<TodoListDTO> list = new ArrayList<>();
		
		while(rs.next()) {
			//tno, title, writer, regdate
			TodoListDTO dto = TodoListDTO.builder()
					.tno(rs.getInt("tno"))
					.title(rs.getString("title"))
					.writer(rs.getString("writer"))
					.regDate(rs.getTimestamp("regDate").toLocalDateTime())
					.build();
			
			//만들어진 객체를 담는다. 
			list.add(dto);
			
		}//end while

		return list;
	}

	public int getTotal()throws Exception {
		
		@Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
		
		@Cleanup PreparedStatement pstmt
		  = conn.prepareStatement("select count(*) from tbl_todo");
		
		//ResultSet은 빨대 
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		//뚜껑따기 
		rs.next();
		
		int result = rs.getInt(1);
		
		return result;
		
	}

	public TodoDTO selectOne(int tno)throws Exception{
		
		//; 주의
		String sql ="select * from tbl_todo where tno  = ? ";
		
		@Cleanup
		Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, tno);

		@Cleanup
		ResultSet rs = pstmt.executeQuery();
		
		boolean nextResult = rs.next();
		
		if( !nextResult ) {
			throw new Exception("NOT FOUND");
		}
		
		TodoDTO dto = TodoDTO.builder()
				.tno(rs.getInt("tno"))
				.title(rs.getString("title"))
				.writer(rs.getString("writer"))
				.regDate(rs.getTimestamp("regDate").toLocalDateTime())
				.modDate(rs.getTimestamp("modDate").toLocalDateTime())
				.build();
		
		return dto;
	}
	
	public void delete(int tno) throws Exception {

		// 마지막 ; 주의
		// 값을 제외한 순수한 SQL문 :v1 :v2
		String sql = "delete from tbl_todo where tno =? ";

		@Cleanup
		Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, tno);
		// DML - insert, update, delete 결과가 몇 개의 행이 영향을 받았는지
		int count = pstmt.executeUpdate();

		if (count != 1) {
			throw new Exception("delete error NOT 1");
		}

	}
	
	public void update(TodoDTO dto) throws Exception {

		// 마지막 ; 주의
		// 값을 제외한 순수한 SQL문 :v1 :v2
		String sql = "update tbl_todo set title =?, modDate = now()  where tno =? ";

		@Cleanup
		Connection conn = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, dto.getTitle());
		pstmt.setInt(2, dto.getTno());
		
		
		// DML - insert, update, delete 결과가 몇 개의 행이 영향을 받았는지
		int count = pstmt.executeUpdate();

		if (count != 1) {
			throw new Exception("UPDATE error NOT 1");
		}

	}
}





