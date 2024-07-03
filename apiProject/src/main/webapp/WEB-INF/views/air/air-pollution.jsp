<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시/도별 대기 오염정보</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

   <h1>어느 지역의 대기오염정도가 궁금하신가요??</h1>
   
   시/도 : <br>
   <select id="sidoName">
      <option>서울</option>
      <option>부산</option>
      <option>대구</option>
      <option>인천</option>
      <option>광주</option>
      <option>대전</option>
      <option>울산</option>
      <option>경기</option>
      <option>강원</option>
      <option>충북</option>
      <option>충남</option>
      <option>전북</option>
      <option>전남</option>
      <option>경북</option>
      <option>경남</option>
      <option>제주</option>
      <option>세종</option>
   </select>
   
   <br>

   <button class="btn btn-info" id="btn">클릭해주세요~</button> | <button class="btn btn-info" id="btn-xml">xml버튼</button>
   <br><br>
   
   <table class="table table-hover">
      <thead class="thead-dark">
         <tr>
            <th>측정소명</th>
            <th>측정일시</th>
            <th>미세먼지농도</th>
            <th>오존농도</th>
            <th>통합 대기환경 수치</th>
         </tr>
      </thead>
      <tbody>
         <tr>
            <th colspan="5">지역을 선택한 뒤 버튼을 눌러주세요~</th>
         </tr>
      </tbody>   
   </table>

   <script>
   
   $(() => {
      
      $('#btn').on('click', () => {
         
         const selectValue = $('#sidoName').val();
         
         $.ajax({
            url : 'pollution',
            type : 'get',
            data : {
               sidoName : selectValue
            },
            success : result => {
               
               //console.log(result.response.body.items);
               
               const items = result.response.body.items;	//변수명.속성값 (변수명.속성값.속성값.속성값)
               
               let strEl = '';
               
               for(let i in items){
                  const item = items[i];
                  
                  strEl += '<tr>'
                         + '<td>' + item.stationName + '</td>'
                         + '<td>' + item.dataTime + '</td>'
                         + '<td>' + item.o3Value + '</td>'
                         + '<td>' + item.pm10Value + '</td>'
                         + '<td>' + item.khaiValue + '</td>'
                         + '</tr>';
                        
               };
               
               $('tbody').html(strEl);
            }
         });
      });
      
      // 문자열 데이터로 받아오기
      $('#btn-xml').on('click', () => {
     	 
     	 $.ajax({
     		url : 'pollution/xml',
     		type : 'get',
     		data : {
     			sidoName : $('#sidoName').val()
     		},
     		success : result => {
     			
     			//console.log(result);
     			//jQuery 선택자를 이용해 xml을 html로
     			console.log($(result).find('item'));
     			
     			// XML 형식의 응답데이터를 받았을 때
     			// jQuery 선택자를 이용해서 => jQuery객체
     			// find() : 기준이 되는 요소의 하위요소들 중 특정요소를 찾을 때 사용
     			
     			// 1. 응답 데이터에서 실제 화면에 출력해야 하는 데이터가 담겨 있는 요소를 선택
     			const items = $(result).find('item');
     			
     			//2. 반복문을 통해서 실제 데이터가 담긴 요소들에 접근해서 동적으로 요소를 만들어서 출력
     				// 여기선 each메서드 이용
     			let value = '';
     			items.each((i, item) => {	// i에는 index, item에는 element(실제요소)
     				
     				//console.log(i + '번째 요소 : ');
     				//console.log(item);
     				//console.log($(item).find('stationName').text());	// jQuery를 이용해 직접 요소에 접근, 요소 이름을 text로 뽑아냄
     			
     				value += '<tr>'
     					   + '<td>' + $(item).find('stationName').text() + '</td>'
     					   + '<td>' + $(item).find('dataTime').text() + '</td>'
     					   + '<td>' + $(item).find('o3Value').text() + '</td>'
     					   + '<td>' + $(item).find('pm10Value').text() + '</td>'
     					   + '<td>' + $(item).find('khaiValue').text() + '</td>'
     					   + '</tr>';
     			});
     			
     			// 3. 출력
     			$('tbody').html(value);
     		}
     	 });
      });
      
   });
   </script>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>