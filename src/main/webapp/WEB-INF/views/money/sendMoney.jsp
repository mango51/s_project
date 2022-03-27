<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function toConfirm(){
	var personToSend =$('#personToSend').val();
	$.ajax({
		async:true,
		type:'GET',
		data:{personToSend:personToSend},
		url:'confirm.do',
		dataType:"text",
		success:function(resp){
			console.log(resp);
			if(resp=="0"){
				alert("계좌번호가 존재하지 않습니다!");
				$('#personToSend').focus();
			}
			else{
				//alert(resp);
				//여기서부터 시작하기 (한글 처리 안되는 듯)
				//console.log(data); //여기서 콘솔창에 입력 안됨!!!
				//$("#personToSendConfirm").val(data);
				//val nameForId = resp;
				//$('input[name=personToSendConfirm]').attr('value',nameForId);
				document.getElementById('personToSendConfirm').value=resp;
				// 이거 해결하고 해당 고객에게 값 전송하는 기능 사용하기
			}
		},
		error:function(error){
			alert("error");
		}
	});
	var dataStr = document.getElementById("personToSend").value;
	console.dir(dataStr);
}
</script>
</head>
<body>
	<c:import url="../default/header.jsp" />
	<form action="${contextPath }/money/sendingMoney" method="post">
	${loginName }
	<input type="hidden" name="loginName" value="${loginName }"> <%--폼을 통해 보낼 용도로 사용 --%>
	님 안녕하세요
	
	<br>
	 아이디 : ${loginUser }
	<input type="hidden" name="loginUser" value="${loginUser }"> <%--폼을 통해 보낼 용도로 사용 --%>
	<br>
	${money }
	<br>
	이체하기	
	
	<input type="text" name="amount" placeholder="이체금액" />
	
	
	보낼 계좌 : <input type="text" id="personToSend" name="personToSend" placeholder="계좌번호"/>
	<button type="button" id="confirmButton" onclick = "toConfirm()">
	확인
	</button>
	
	받는 분 : <!--${personToSendConfirm }-->
	<input type="text" id="personToSendConfirm" name="personToSendConfirm" readonly value=""/>
	<br>
	<input type="submit" value="이체" />
	
	</form>

	
</body>
</html>