<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="mt-2 mb-2">
<form id="insertSignup" method="post" action="/log/signup_forajax">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" id="loginId" name="loginId"
					class="from-control"> <input id="btnChk" type="button"
					class="btn btn-success" value="중복확인"></td>
			</tr>
			<tr>
				<td id="idLength" class="text-danger d-none">아이디를 4자 이상 입력하세요</td>
				<td id="idDuc" class="text-danger d-none">중복된 아이디 입니다</td>
				<td id="idChk" class="text-success d-none">사용가능한 아이디입니다</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" id="password" name="password"
					class="from-control"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="text" id="passwordChk" class="from-control">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name"
					class="from-control"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" id="email" name="email"
					class="from-control"></td>
			</tr>
		</table>
		<div class="d-flex justify-content-center mt-3">
		<button type="submit" id="btnSignup" class="btn btn-primary">가입하기</button>
		</div>
</form>
	</div>


<script>
	$(document).ready(function() {

		$('#btnChk').on('click', function() {
			
			let loginId = $('#loginId').val().trim();
			
			if (loginId.length <= 3) {
				$('#idLength').removeClass('d-none');
				$('#idDuc').addClass('d-none');
				$('#idChk').addClass('d-none');
				return;
			}

			$.ajax({
				type : 'get',
				url : '/log/is_dulicated_id',
				data : {
					"loginId" : loginId
				},
				success : function(data) {
					if (data.result) {
						$('#idLength').addClass('d-none');
						$('#idDuc').removeClass('d-none');
						$('#idChk').addClass('d-none');

					} else {
						$('#idLength').addClass('d-none');
						$('#idDuc').addClass('d-none');
						$('#idChk').removeClass('d-none');
					}

				},
				error : function() {
					alert('아이디 중복확인에 실패했습니다. 관리자에게 문의해 주세요.');
				}

			});

		});
		
		
		$('#insertSignup').submit(function(e){
			e.preventDefault();
			let loginId = $('#loginId').val().trim();
			if(loginId ==''){
				alert('아이디를 입력하세요');
				return;
			}
			
			let password = $('#password').val().trim();
			let passwordChk = $('#passwordChk').val().trim();
			if(passwordChk ==''||passwordChk!=password){
				alert('비밀번호, 비밀번호 확인을 일치해 주세요');
				return;
			}
			
			let name = $('#name').val().trim();
			if(loginId ==''){
				alert('이름을 입력하세요');
				return;
			}
			
			let email = $('#email').val().trim();
			if(loginId ==''){
				alert('이메일을 입력하세요');
				return;
			}
			
			
			
			if($('#idChk').hasClass('d-none')){
				alert('아이디 중복확인을 해주세요');
				return;
			}
			
			
			let data =$(this).serialize();//테그속 들어있는 nameinput이 구성된다.
			let url='/log/signup_forajax';
			$.post(url,data).done(function(data){
				alert('가입중');
				if(data.result=="success"){
					alert('회원가입을 환영합니다.');
					location.href="/marondalgram/log/sign_in";
				}else{
					alert('가입에 실패했습니다');
				}
			})
			
			
		});
			
		
		
		
		

	});
</script>
			
			