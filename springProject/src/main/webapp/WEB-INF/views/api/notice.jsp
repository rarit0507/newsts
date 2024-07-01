<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항이당당</title>
<style>
	#content {
		width : 800px;
		height : auto;
		margin : auto;
	}

	#outerDiv{
		width : 800px;
		height : 400px;
		padding-top : 50px;
	}

	.noticeEl {
		width: 100%;
		height : 60px;
		margin: auto;
		line-height: 60px;
		text-align: center;
	}

	.noticeEl > div {
		display: inline-block;
	}

	#title{
		margin-top : 100px;
		text-align: center;
	}

	#detail{
		background-color:#23C293; 
		width:800px; 
		margin: auto;
		text-align:center;
		color : white;
		height : 150px;
		display: none;
	}
	
	#detail > div{
		height : 50px;
		line-height: 50px;
		border : 1px solid rgba(255, 255, 255, 0.656);
	}

</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp"/>
	
	<div id="content">
		<h1 id="title">공지사항 게시판입니다!</h1>
		<button class="btn btn-danger btn-sm" data-toggle="modal" href="#noticeModal">글작성</button>

	</div>	
	
	<div id="detail">
		
	</div>
	
	<jsp:include page="../common/footer.jsp"/>

	<div class="modal fade" id="noticeModal">
		<div class="modal-dialog">
		  <div class="modal-content">
	
			<div class="card">
			  <div class="card-header text-white" style="background-color: #ff52a0;">게시글 작성하기</div>
			  <div class="card-body">       
				
				  <div class="form-group">
					<label>작성자</label>
					<input type="text" class="form-control" id='noticeWriter' value="${ sessionScope.loginUser.userId }" readonly>
				  </div>
				  
				  <div class="form-group">
					<label>제목</label>
					<input type="text" class="form-control" id='noticeTitle' value="">
				  </div>
		
				  <div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="5" id='noticeContent' style="resize: none;"></textarea>
				  </div>
				 
				  <a class="btn" data-dismiss="modal"
			  style="background-color: #ff52a0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">닫기</a>&nbsp;&nbsp;
					  <a class="btn" onclick="insert();"
				  style="background-color: red; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">작성하기</a>&nbsp;&nbsp;
				   
			  </div>
			</div>
		  </div>
		</div>
	</div>

	<div class="modal fade" id="updateModal">
		<div class="modal-dialog">
		  <div class="modal-content">
	
			<div class="card">
			  <div class="card-header text-white" style="background-color: #ff52a0;">게시글 수정하기</div>
			  <div class="card-body">       
				
					<input type="hidden" value="" id="updateNo"/>
				  <div class="form-group">
					<label>작성자</label>
					<input type="text" class="form-control" id='updateWriter' value="" readonly>
				  </div>
				  
				  <div class="form-group">
					<label>제목</label>
					<input type="text" class="form-control" id='updateTitle' value="">
				  </div>
		
				  <div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="5" id='updateContent' style="resize: none;"></textarea>
				  </div>
				 
				  <a class="btn" data-dismiss="modal"
			  style="background-color: #ff52a0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">닫기</a>&nbsp;&nbsp;
					  <a class="btn" onclick="update();"
				  style="background-color: orange; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">수정하기</a>&nbsp;&nbsp;
				   
			  </div>
			</div>
		  </div>
		</div>
	</div>

	<script>
	/*	이렇게 쓰는 걸 선호함!
	
		function() {
		
		}
	
		==>
		
		() => {
			
		}
		===================
		function(e) {
			
		}
		
		==>
		
		e => {
			
		}
		====================
		function(a, b) {
			
		}
		
		==>
		
		(a, b) => {
			
		}
	*/
	
	window.onload = () => {
		
		findAll();
		
	}
	
	//insert
	function insert() {	//writer, title, content 요소를 어떻게 DB로 가져가지? => notice로 가공해 객체에 담아서
			
		const requestData = {
			"noticeTitle" : $('#noticeTitle').val(),
			"noticeWriter" : $('#noticeWriter').val(),
			"noticeContent" : $('#noticeContent').val(),
		};
		//console.log(requestData);
		
		$.ajax({
			url : 'notice',
			type : 'post',
			data : requestData,
			success : response => {
				
				console.log(response);
				
	           if(response.message === '서비스 요청 성공'){
	               $('#outerDiv').remove();
	               findAll();
	               $('#noticeTitle').val('');
	               $('#noticeContent').val('');
	               
	            }

			}
		});
	}
	
	//delete
	function deleteById(noticeNo) {
		
		$.ajax({
			url : 'notice/'+noticeNo,
			type : 'delete',
			success : response => {
				
				//console.log(response);
				if(response.data === '삭제성공') {
					$('#detail').slideUp(300);
					$('#outerDiv').remove();
					findAll();
				};
			}
		});
	}
	
	//리스트 튜플 클릭 시 상세보기로 이동
	$('#content').on('click', '.noticeEl', e => {	//클릭하면, 부모요소를, 이벤트발생
	
		console.log($(e.currentTarget).children().eq(0).text());	// .noticeEl 클래스 아래의 text 요소를 지정
		
		const noticeNo = $(e.currentTarget).children().eq(0).text();
		
		$.ajax({
			url : 'notice/' + noticeNo,
			type : 'get',
			success : response => {
				
				// console.log(response);
				
				const notice = response.data;
				
				console.log(notice);
				
				const contentValue = '<div id="notice-detail">'
								   + '<div>' + notice.noticeTitle + '</div>'
								   + '<div>' + notice.noticeContent + '</div>'
								   + '<div>'
								   + '<a class="btn btn-sm btn-warning" data-toggle="modal" href="updateModal">'
								   + '수정하기'
								   + '</a> | '
								   + '<a class="btn btn-sm btn-secondary" onclick="deleteById('+ notice.noticeNo +')">삭제하기</a>'
								   + '</div>'
								   + '</div>';
				$('#detail').html(contentValue);
				$('#detail').slideDown(500);
				
			}
		});
	
	});
		
	
	const findAll = () => {
		
		$.ajax({
			url : 'notice',
			type : 'get',
			success : response => {	//response는 데이터 + 메시지
				
				//console.log(response);
				
				//데이터만 뽑고 싶으면,(response + .data)
				const noticeList = response.data;
				//console.log(noticeList);
				
				//근데 난 하나하나의 값(레코드)를 출력하고 싶어(div에 List로 뽑기)
				const outerDiv = document.createElement('div');
				outerDiv.id = 'outerDiv';
				//console.log(outerDiv);
				
				//map으로 순회(반복. List의 개수만큼)
				noticeList.map(o => {
					
					
					//console.log(o);
					
					//한 개 행에 대한 div
					const noticeEl = document.createElement('div');	// 한개 행에 대한 div
					noticeEl.className = 'noticeEl';	//속성값 부여를 위해 class 속성 부여
					
					/*
					//한 개 행 내 요소 div 생성(공지글 번호)
					const numEl = document.createElement('div');	//공지사항번호(map의 'o'에 있는 noticeNo를 가져와야 함)
					const numText = document.createTextNode(o.noticeNo);
					numEl.style.width = '50px;'	//여기서 스타일도 줄 수 있음
					//console.log(numEl);
					//console.log(numText);	//div와 텍스트가 따로임 ->
					numEl.appendChild(numText);	//numEl 아래 numText 요소를 자식요소로 추가
					//console.log(numEl);
					noticeEl.appendChild(numEl);	//전체 한 개 행에 한 개 행 내 요소 div 삽입
					*/
					
					//const numEl = createDiv(o.noticeNo, '50px')
					//console.log(numEl);
					/*
					//공지글 제목
					const titleEl = document.createElement('div');
					const titleText = document.createTextNode(o.noticeTitle);
					titleEl.style.width = '400px';
					titleEl.appendChild(titleText);	
					noticeEl.appendChild(titleEl);
					//공지글작성자
					const writerEl = document.createElement('div');
					const writerText = document.createTextNode(o.noticeWriter);
					writerEl.style.width = '150px';
					writerEl.appendChild(writerText);	
					noticeEl.appendChild(writerEl);
					//공지글날짜
					const dateEl = document.createElement('div');
					const dateText = document.createTextNode(o.createDate);
					dateEl.style.width = '200px';
					dateEl.appendChild(dateText);	
					noticeEl.appendChild(dateEl);
					
					*/
					//console.log(noticeEl);
					//근데 중복코드가 4개나 있음 => 유지보수 Hard
					
					// => 중복코드는 함수로 빼내서 수행할 수 있다.
					noticeEl.appendChild(createDiv(o.noticeNo, '50px'));
					noticeEl.appendChild(createDiv(o.noticeTitle, '400px'));
					noticeEl.appendChild(createDiv(o.noticeWriter, '150px'));
					noticeEl.appendChild(createDiv(o.createDate, '200px'));
					
					// console.log(noticeEl);
					outerDiv.appendChild(noticeEl);
				});
				
				//content 영역에 만들어둔 개별 행들을 자식요소로 추가
				document.getElementById('content').appendChild(outerDiv);
			}
		});
	}
	
	function createDiv(data, style) {
	      
	    const divEl = document.createElement('div');
	    const divText = document.createTextNode(data);
	    divEl.style.width = style;
	    divEl.appendChild(divText);
		      
	    return divEl;
	} 
	</script>
	

</body>
</html>