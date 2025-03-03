<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Todo Read</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

<!-- 네비게이션 바 추가 -->
 <%@include file="../includes/nav.jsp" %>
<!-- 네비게이션 바 추가 끝 -->

	<div class="container mt-5">
		<div class="card shadow">
			<div class="card-header bg-primary text-white text-center">
				<h3>Todo Read</h3>
			</div>
			<div class="card-body">
				<div class="input-group mb-3">
					<input type="text" name="tno" class="form-control"
						value="${dto.tno }" readonly>
				</div>
				
				<div class="input-group mb-3">
					<input type="text" class="form-control" value="${dto.title }"
						readonly>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control"
						value="${dto.writer }" readonly>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control"
						value="${dto.regDate }" readonly>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control"
						value="${dto.modDate }" readonly>
				</div>
				
				<c:if test="${dto.writer == user }">
				 <a class="btn btn-primary w-100 mb-3" href="/todo/modify/${dto.tno }">수정/삭제</a>
				</c:if>
			</div>
		</div>
	</div>

	<script>
		function addTodo() {
			const titleInput = document.getElementById("todoTitle");
			const authorInput = document.getElementById("todoAuthor");
			const title = titleInput.value.trim();
			const author = authorInput.value.trim();
			if (title === "" || author === "")
				return;

			const li = document.createElement("li");
			li.className = "list-group-item d-flex justify-content-between align-items-center";
			li.innerHTML = `<div><strong>${title}</strong><br><small>작성자: ${author}</small></div> <button class="btn btn-danger btn-sm" onclick="removeTodo(this)">삭제</button>`;

			document.getElementById("todoList").appendChild(li);
			titleInput.value = "";
			authorInput.value = "";
		}

		function removeTodo(button) {
			button.parentElement.remove();
		}
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
