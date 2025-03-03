<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Todo List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<!-- 네비게이션 바 추가 -->
	<%@include file="../includes/nav.jsp"%>>
	<div class="container mt-5">
		<div class="card shadow">
			<div class="card-header bg-primary text-white text-center">
				<h3>Todo List</h3>
				<a href="/todo/add" class="text-white">새로운 Todo 추가</a>
			</div>
			<div class="card-body">
				<ul id="todoList" class="list-group">

					<c:forEach items="${ dtoList }" var="todo">
						<li
							class="list-group-item d-flex justify-content-between align-items-center">
							<a href="/todo/read/${todo.tno }">${todo.tno } --- ${todo.title } -- 작성자 ${todo.writer }</a>
							<button class="btn btn-danger btn-sm" onclick="removeTodo(this)">삭제</button>
						</li>
					</c:forEach>

				</ul>
			</div>

			<div class="card-body">

				<ul class="pagination">
					<c:if test="${pagingDTO.prev }">
						<li class="page-item">
							<a class="page-link" href="/todo/list?page=${pagingDTO.start -1 }">이전</a>
						</li>
					</c:if>

					<c:forEach begin="${pagingDTO.start}" end="${pagingDTO.end}" var="num">

						<li class="page-item ${num == pagingDTO.current? 'active':'' }">
							<a class="page-link" href="/todo/list?page=${num}">${num }</a>
						</li>

					</c:forEach>

					<c:if test="${pagingDTO.next }">
						<li class="page-item"><a class="page-link"
							href="/todo/list?page=${pagingDTO.end + 1 }">다음</a></li>
					</c:if>
				</ul>
			</div>

		</div>
	</div>

	<script>
		const result = '${param.result}'; //query string 처리 가능 

		if (result === 'add') {

			window.alert("새로운 Todo가 등록되었습니다.");
			window.history.pushState(null, "", "?"); //새로고침 문제 해결을 위해서 사용

		} else if (result === 'remove') {

			window.alert("Todo가 삭제되었습니다.");
			window.history.pushState(null, "", "?"); //새로고침 문제 해결을 위해서 사용
		}
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
