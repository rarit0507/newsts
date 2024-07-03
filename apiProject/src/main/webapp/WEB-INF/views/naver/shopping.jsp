<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<style>
		a{
			text-decoration : none;
			color : white;
		}
		#kh-bg{
			width : 800px;
			height : 450px;
			background-image : url(https://cdn.apollo.audio/one/media/65ca/1e15/7823/8605/a55d/29f7/keanu-reeves-dogstar-uk.jpg?quality=80&format=jpg&crop=0,0,674,1200&resize=crop);
			margin-top : 50px;
			margin : auto;
			background-repeat : no-repeat;
			background-size : contain;
			border-radius : 20px;
		}
		.items{
			width : 1000px;
			margin : auto;
			display:flex;
			flex-wrap : wrap;
			gap : 20px;
		}
	</style>
</head>
<body>
   
   <nav class="navbar navbar-expand-lg bg-dark">
      <div class="container-fluid">
          <a class="navbar-brand" href="#">
         <img src="/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
          	<label style="font-weight:bold; font-size:1.2em;">KH몰</label>
          </a>
     </div>
      <div class="container-fluid">
          <a class="navbar-brand"></a>
          <form class="d-flex" role="search">
            <input class="form-control me-2" id="keyword" type="search" placeholder="상품명을 입력하세요." aria-label="Search">
            <button id="search-btn" style="width:300px; boarder:1px solid white; color:white;"class="btn btn-outline-success" type="button">Search</button>
          </form>
        </div>
     </nav>
     
     <div id="kh-bg">
     
     </div>
     
     <hr/>
     <br/>
     
     <div id="total-count" style="display:none; padding:30px; text-align:right;">
     
     </div>
     <br>
     <div class="items">
     
     </div>
     
     <br><br>
     
     <button class="button btn-lg" style="background:lightgreen; color:white; font-weight:bold;" onclick="nextPage();">다음상품</button>

	<hr/>
	
	<script>
		
		$('#search-btn').click(() => {
			searchItem();
		});
		
		function nextPage() {
			startNo += 9;
			searchItem();
		}
		
		var startNo = 1;
	
		function searchItem() {
			
			const $keyword = $('#keyword').val();	//달러 붙인 거 : 이거 jQuery 쓴 변수다
			
			$.ajax({
				url : 'product',
				type : 'get',
				data : {
					keyword : $keyword,
					start : startNo
				},
				success : product => {
					$('#total-count').fadeOut(300);
					console.log(product);
					$('#total-count').html('총 <b>' + product.total + '</b>건의'
										   + '<label style="color:red; font-weight:bold;">' + $keyword + '</label>이(가) 검색되었습니다.')
										   .fadeIn(1000);
					
					const items = product.item;
					
					let item = '';
					
					for(let i in items) {
						
						item += '<div class="card" style="width: 18rem;">'
							  +	  '<img src="'+ items[i].image +'" class="card-img-top">'
							  +	  '<div class="card-body">'
							  +	    '<h5 class="card-title">' + items[i].brand + '</h5>'
							  +	    '<p class="card-text">' + items[i].title + '</p>'
							  +	  '</div>'
							  +	  '<ul class="list-group list-group-flush">'
							  +	    '<li class="list-group-item">가격</li>'
							  +	    '<li class="list-group-item">' + items[i].lprice + '원</li>'
							  +	  '</ul>'
							  +	  '<div class="card-body">'
							  +	    '<a href="' + items[i].link + '" class="btn btn-primary" target="_blank">구매하러가기</a>'
							  +	  '</div>'
							  +	'</div>';
					};
					
					$('.items').html(item);
				}
			});
		}
	
	</script>



</body>
</html>