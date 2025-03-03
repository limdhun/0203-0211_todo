<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="/todo/list">Todo App</a>
		<div class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<c:if test="${user != null}">
					<li class="nav-item"><span class="navbar-text text-white me-3">${user }님 환영합니다!
					</span></li>
					<li class="nav-item"><a class="btn btn-danger" href="/signout">로그아웃</a>
					</li>
				</c:if>
				<c:if test="${user==null }">
					<li class="nav-item"><a class="btn btn-primary" href="/signin">로그인</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>