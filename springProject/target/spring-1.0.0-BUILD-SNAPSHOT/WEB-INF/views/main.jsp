<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>B강의장 프로젝트</title>
<style>
	.innerOuter{
      height:800px;
   }
</style>
</head>
<body>
	<jsp:include page="common/menubar.jsp" />
	
	<div class="innerOuter">
	
		<h3>게시글 조회수 TOP 5</h3>
		<br/>
		<a href="boardList" style="float:right; color:lightgray; font-seight:800; font-size:14px;">더보기 >> </a>
		<br/><br/>
		
		<table class="table table-hover" align="center" id="boardList">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
					<th>첨부파일</th>
				</tr>
			</thead>
			<tbody>
			
				<!-- BOARD 테이블에서 count컬럼의 값이 높은 상위 5개의 게시글을 조회해서 뿌려줄 것 -->
				
			</tbody>
		</table>
		<br/><br/>
		<table id="board-detail" class="table">
					
		</table>
	</div>
	
	<!--  =>tr이 아니라 그 상위 요소에 이벤트 달기. 주로 document
	//첫 번째 : 상위 인자(document), 하위요소(이벤트 명 뒤에 작성)
	
	//console.log($(e.currentTarget).children().eq(0).text());
	// boardsController의 get으로 가야 함. @RequestMapping("boards") 달아놨으므로 'boards'로 시작, 이후 + PK
	--> 
	<script>
	
	$(document).on('click', '#boardList > tbody  > tr', e => {	//내가 이벤트를 주고 싶은 요소의 상위 요소에 거는 것이 일반적.
		
		const boardNo = $(e.currentTarget).children().eq(0).text() // <== 게시글 번호

		$.ajax({
			url : 'boards/' + boardNo,
			type : 'get',
			success : result => {
				
				//console.log(result);
				
				let value = '<tr><td colspan="3">게시글 상세보기</td><tr>';
				
				value += '<tr>'
					   + '<td width="300">'	+ result.boardTitle + '</td>'
					   + '<td width="300">'	+ result.boardContent + '</td>'
					   + '<td width="300">'	+ result.boardWriter + '</td>'
					   + '</tr>';
				
				document.getElementById('board-detail').innerHTML = value;
			}
		});
	
	});		
	/*
		상세보기
		
		조회된 게시글 목록에서
		게시글을 클릭하면
		선택한 글번호를 가지고
		하나의 정보를
		AJAX 요청을 통해 응답받아서
		id속성값이 board-detail인 table에 자식요소를 만들어서 출력
	*/
	
		
	$(() => {
		findTopFiveBoard();
	})
	
	function findTopFiveBoard() {
		
		$.ajax({
			url : 'boards',
			type : 'get',
			success : boards => {
				
				// console.log(boards),
				
				let value = "";
			
				//나중에 생겨난 것. '동적 요소'라고도 부름
				for(let i in boards) {
					value += '<tr>'
						   + '<td>' + boards[i].boardNo + '</td>'
						   + '<td>' + boards[i].boardTitle + '</td>'
						   + '<td>' + boards[i].boardWriter + '</td>'
						   + '<td>' + boards[i].count + '</td>'
						   + '<td>' + boards[i].createDate + '</td>'
						   + '<td>';
						   if(boards[i].originName != null) {
							   value += '★';
						   }
						   + '</td></tr>';
				}
				$('#boardList tbody').html(value);
			}
		});
	}
	</script>
	
	<<jsp:include page="common/footer.jsp" />
</body>
</html>