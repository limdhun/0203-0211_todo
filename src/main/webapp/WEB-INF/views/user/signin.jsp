<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body
	class="d-flex justify-content-center align-items-center vh-100 bg-light">

	<div class="card p-4 shadow-lg" style="width: 350px;">
		<h3 class="text-center mb-4">로그인</h3>
		<form action="/signin" method="post">
			<div class="mb-3">
				<label for="uid" class="form-label">아이디</label> <input type="text"
					class="form-control" id="uid" name="uid" required>
			</div>
			<div class="mb-3">
				<label for="upw" class="form-label">비밀번호</label> <input
					type="password" class="form-control" id="upw" name="upw" required>
			</div>
			<button type="submit" class="btn btn-primary w-100">로그인</button>
		</form>
		<div class="text-center mt-3">
			<a href="#">비밀번호 찾기</a> | <a href="#">회원가입</a>
		</div>
	</div>

	<script>
		const msg = '${param.msg}'//single 쿼텐션? 굉장히 중요. 서버 사이드에서 동작하여 결과값 가지고 옴

		if (msg === 'signout') {//===이면 타입까지 같아야 한다는 것
			alert("정상적으로 로그아웃 되었습니다.")//세미콜론은 쓸 수도 있고 안 쓸 수도 있음
		}
		if (msg === 'not') {
			alert("이미 로그아웃 되었습니다.")
		}
	</script>

</body>
</html>