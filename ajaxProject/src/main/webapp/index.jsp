<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>

		<h1>Spring에서의 비동기 통신 활용법~~!!</h1>
		
		<h3>AJAX</h3>
		
		<p>
			Asynchronous JavaScript And XML의 약자로 <br>
			서버로부터 데이터를 응답받아 전체 페이지를 다시 만드는 것이 아니라 일부만 내용물을 바꿀 수 있는 기법<br><br>
			
			참고로, 우리가 그동안 a태그 /form 태그를 이용해서 요청한 방식은 동기식 요청 <br>
			=> HTML전체를 응답 받아서 브라우저는 처음부터 끝까지 화면을 렌더링해야 결과를 확인할수 있었음!<br>
			
			비동기 처리를 위해서는 AJAX라는 기술을 사용해야함 !!<br><br>
			* AJAX구현 방식 : JavaScript / jQuery / fetchAPI / axios ..
			
			* 동기 / 비동기 차이
			- 동기식 : 서버가 요청 처리 후 응답 HTML데이터가 돌아와야만 그 다음 작업이 가능<br>
					   만약 서버에서 응답페이지를 돌려주는 시간이 지연되면 무작정 기다리고 있어야함<br>
					   응답 데이터가 돌아오면 전체화면을 파싱<br><br>
					   
			- 비동기식 : 현재 페이지를 그대로 유지하는 동안 중간중간에 추가 요청을 보낼수 있음<br>
						 요청을 보낸다고해서 다른페이지가 새롭게 렌더링 되는것이 아님(현재 페이지가 그대로 유지)<br>
						 요청을 보내놓고 응답이 올때까지 다른작업을 할 수 있음 <br><br>
						 
			예시 ) 예약, 중복확인, 검색어 자동생성 등 ... 사실상 오늘날 SPA는 전부 비동기 처리 <br><br>
			
			* 비동기식 단점 <br><br>
			
			- 요청 후 돌아온 응답데이터를 가지고 현재 페이지에서 새로운 요소를 동적으로 만들어줘야함!
			=> DOM요소를 새롭게 만드는 구문을 잘 익혀둬야함 <br><br>
			- 페이지 내 복잡도가 기하급수적으로 증가
		</p>
		
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		
		
		<pre>
			* jQuery에서의 AJAX통신
			1. 객체 || 2. 배열
			
			[ 표현법 ]
			$.ajax({
				속성명 : 속성값,
				속성명 : 속성값,
				속성명 : 속성값
				...
			});
			
			* 주요속성 : 
			- url : 요청할 url(필수로 작성) => form태그의 action 속성
			- type : 요청 전송방식(GET/POST/PUT/DELETE => 생략 시 기본값은 GET) => form태그의 mothod속성
			- data : 요청시 전달할 값({키:밸류, 키:밸류 ...}) => for태그의 input요소의 value속성
			- success : AJAX통신 성공 시 콜백함수를 정의
			
			------------------------------------------------------------------------------------------------
			
			- error : AJAX통신 실패 시 콜백함수를 정의
			- complete : AJAX통신이 성공하든 실패하든 무조건 끝나면 실행할 콜백함수
			- async : 서버와 비동기 처리방식 설정 여부 (기본값 true)
			- contentType : 요청 시 데이터 인코딩 방식 정의(보내는 측의 데이터 인코딩)
			- dataType : 서버에서 응답시 돌아오는 데이터의 형태 설정, 작성안하면 스마트하게 판단함
						xml : 트리형태
						json : 맵 형태의 구조 (일반적인 데이터 구조)
						script : javascript 및 일반 String형태의 데이터
						html : html태그 자체를 return하는방식
						text : String 데이터
			
		</pre>
		
		<h4>AJAX로 요청 보내고 응답 받아오기</h4>
		
		<h5>1. 서버로 요청 시 인자값을 전달하고 응답데이터를 받아서 화면에 출력</h5>
		
		<label>오늘 먹을거 : </label>알밥, 돈까스, 서브웨이, 김치찜, 막국수<br>
		
		메뉴 : <input type="text" id="menu"/><br>
		수량 : <input type="number" id="amount"><br><br>
		
		<button id="btn">서버로 전송</button> 
		
		<div id="resultMsg">
		
		</div>
		
		<!-- 버튼을 클릭 시 => 메뉴에 입력한 음식명과 수량에 입력한 개수를 가지고 서버에 요청해서 => 응답받은 응답데이터를 div요소의 content영역에 출력 -->
		
	<script>
	      
	      document.getElementById('btn').onclick = () => {
	         
	         const menuValue = document.getElementById('menu').value;
	         const amountValue = document.getElementById('amount').value;
	         
	         $.ajax({
	            url : 'ajax1.do', //필수정의 속성(매핑값)
	            type : 'get',     //요청 시 전달 방식
	            data : { // 요청시 전달 값 (key-value)
	               menu : menuValue,
	               amount : amountValue
	            },
	            success : result => {
	               //console.log(result);
	               const resultValue = result != 0 '선택하신 메뉴 ' + menuValue +'의 '+ amountValue + '개의 가격은' +  result + '원 입니다.';
	               document.getElementById('resultMsg').innerHTML= resultValue;
	            },
	            error : () => {
	               console.log('오류');
	            }
	         });
	      }
	         
	</script>
		
		
		
		
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	<h3>2. DB에서 SELECT문을 이용해서 조회했다는 가정하에 VO객체를 응답받아서 화면상에 출력해보기</h3>
	
	조회할 음식번호 : <input type="number" id="menuNo" /> <br><br>
	
	<button id="select-btn">조회</button><br>
	
	<div id="today-menu">
	
	</div>
	
	<!-- 사용자가 1. 값입력, 2. 버튼 누름 -> 3. AJAX 방식으로 값 받아옴 -->
	<script>
	
		window.onload = () => {
			
			document.getElementById('select-btn').onclick = () => {		//'select-btn': 이벤트 타겟, .onclick : 이벤트 타입, 그 다음 괄호 : 이벤트핸들러(어떤이벤트냐(여기선AJAX))
				
				$.ajax({
					//url : 'ajax2.do',
					url : 'ajax3.do',
					type : 'get',
					data : {	//넘기는 데이터. (Key, Value)
						menuNumber : document.getElementById('menuNo').value	// Value값은 input요소의 value값에 있다.(id로 받아오기)
					},
					success : result => {
						
						console.log(result);	//항상 내가 원하는 값이 나오는 게 맞는지 확인하고 진행.
						
						const obj = {
							 "menuNumber" : "1",
							 "menuName" : "순두부",
							 "price" : "9500",
							 "material" : "순두부"
						};
						
						console.log(obj);
						
						const menu = '<ul>오늘의 메뉴 : '
								   + '<li>' + result.menuName + '</li>'
								   + '<li>' + result.price + '원</li>'
								   + '<li>재료 : ' + result.material + '</li>'
								   + '<ul>'
						document.getElementById('today-menu').innerHTML = menu;
					},
					error : e => {
						console.log(e);
					}	// 여기까지 하고 뒷 단 가서 Controller 작업
				});
			}
		}
		
	</script>
	

	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

	<!-- 그럼 VO 여러 개 받아갈 때는 어쩔건데 -->
	<h3>3. 조회 후 리스트를 응답받아서 출력</h3>
	
	<button onclick="findAll()">메뉴 전체 조회</button>
	<br><br>
	
	<table boarder="1" id="find-result">
		<thead>
			<tr>
				<th>메뉴명</th>
				<th>가격</th>
				<th>재료</th>
			</tr>
		</thead>
		<tbody id="tbody">
		
		
		
		</tbody>
	</table>
	
	<script>
		function findAll() {
			
			$.ajax({
				url : 'find.do',
				type : 'get',
				success : result => {
					
					// console.log(result[0].menuName);		// 배열 요소에서 첫번째 요소
					console.log(result);
					/*
					<tr>
					<td>김치찌개</td>
					<td>12000</td>
					<td>김치</td>
					</tr>
					*/
					
					// 순수 자바스크립트로 만들기
					const tbodyE1 = document.getElementById('tbody');
					
						
					// 반복문을 위한 Map 함수
					// o : 매개변수	/ i : 인덱스 => 배열의 길이만큼만 반복
//					result.map((o, i) => {
					result.map(o => {
						// console.log(o);
						// console.log(i);
						const trE1 = document.createElement('tr');
						
						const tdFirst = document.createElement('td');
						const firstText = document.createTextNode(o.menuName);
						tdFirst.style.width = '200px';
						tdFirst.appendChild(firstText);
						
						const tdSecond = document.createElement('td');
						const secondText = document.createTextNode(o.price);
						tdSecond.style.width = '200px';
						tdSecond.appendChild(secondText);
						
						const tdThird = document.createElement('td');
						const thirdText = document.createTextNode(o.material);
						tdThird.style.width = '100px';
						tdThird.appendChild(thirdText);
						
						trE1.appendChild(tdFirst);
						trE1.appendChild(tdSecond);
						trE1.appendChild(tdThird);
						
						tbodyE1.appendChild(trE1);
					});
					
					/*
					const trE1 = document.createElement('tr');
					//console.log(trE1);
					
					const tdFirst = document.createElement('td');
					//console.log(tdFirst);
					const firstText = document.createTextNode(result[0].menuName);
					tdFirst.style.width = '200px';
					tdFirst.appendChild(firstText);
					
					const tdSecond = document.createElement('td');
					const secondText = document.createTextNode(result[0].price);
					tdSecond.style.width = '200px';
					tdSecond.appendChild(secondText);
					
					const tdThird = document.createElement('td');
					const thirdText = document.createTextNode(result[0].material);
					tdThird.style.width = '100px';
					tdThird.appendChild(thirdText);
					
					trE1.appendChild(tdFirst);
					trE1.appendChild(tdSecond);
					trE1.appendChild(tdThird);
					
					tbodyE1.appendChild(trE1);
					console.log(trE1);
					*/		
					
					// 배열 요소를 반복문으로 쓰고 싶다? => 맵Map 사용
				}
			});
		}
	</script>


















</body>
</html>