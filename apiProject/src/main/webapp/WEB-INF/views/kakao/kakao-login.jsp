<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 하지 말고 나가</title>

	<style>
		a{
			display : inline-block;
		}
	</style>
</head>
<body>

	<a id="login-btn">
		<img alt="로그인버튼" src="resources/img/kakao_login_medium_narrow.png">
	</a>
	<button id="logout">로그아웃</button>

	${ loginUser.id }님 환영합니다.
	${ loginUser.nickName }은 없으시네요.




	<script>
		document.getElementById('login-btn').onclick = () => {
				
			location.href = 'https://kauth.kakao.com/oauth/authorize'
						  + '?client_id=ab97d365595a74923135c285d82ea29f'	//RESTapi 앱 키
						  + '&redirect_uri=http://localhost/api/code'
					      + '&response_type=code'
					      + '&scope=profile_nickname,profile_image';		// 얜 필수 아니었음
			
		}
		
		document.getElementById('logout').onclick => {
			location.href = "logout";
		}
	</script>










	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>