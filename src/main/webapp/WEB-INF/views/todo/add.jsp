<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<h3>Todo Add</h3>
			</div>
			<div class="card-body">
				<form action="/todo/add" method="post">
					<div class="input-group mb-3">
						<input type="text" name="title" class="form-control"
							placeholder="할 일 제목">
					</div>
					<div class="input-group mb-3">
						<input type="text" name="writer" class="form-control"
							readonly="readonly" value="${user }">
					</div>
					<button class="btn btn-primary w-100 mb-3">추가</button>
				</form>
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
