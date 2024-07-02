<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>var, let, const</h1>
	
	<h2>변수 선언 시 사용할 수 있는 키워드들</h2>
	
	<button onclick="defDeclare()">안녕 나는 버튼</button>
	
	<script>
		function defDeclare() {
			
			var userId = 'user01';
			console.log((userId);
			
			var userId = 'user02';
			console.log((userId);
			
			var userId = 'user03';
			console.log((userId);
			
			console.log('-----------------');
			
			let userName = "홍길동";
			console.log(userName);
			
			//let userName = "머시기";
			//console.log(userName);
			
			userName = '머시기';	//값 재할당은 가능
			console.log(userName);

			console.log('-----------------');

			// const userPwd;
			// userPwd = 123;
			const userPwd = 123;	// 선언과 초기화 동시에 해야
			
			// const userPwd = 234;	// 초기화 안 됨
			// userPwd = 234;		// 값 재할당도 안 됨

			// let, const를 위주로 사용해라
			
			const titleE1 = document.getElementById('title');
		}
	</script>
	
	<h1 id="title">요소</h1>
	
	<button onclick="keywordScope()">나는 버튼</button>
	
	<script>
		function keywordScope() {
			
			// let, const
			if(1) {
				let gender = 'M';
				const hobby = '취미다';
			}
			
			//console.log(hobby);
			
			//var == functionScope == 변수가 선언된 "함수영역" 내에서 사용 가능
			
			for(var i = 0; i < 5; i++) {
				
			}
			
			haha = "하하";	//아무것도 안 붙인 선언
							// 그래도 변수 선언 했구나 이럼 ㄱㅊ음
			
			console.log(i);
			console.log(haha);
			
			//var도 써야 할 땐 쓸 수 있다. let과 const로 커버가 어려운 경우가 존재
		}
	</script>
</body>
</html>