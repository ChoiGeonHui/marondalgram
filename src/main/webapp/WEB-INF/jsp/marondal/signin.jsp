<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="col-6">
    
    <img id="imageControl" alt="이미지" src="/static/images/ym1.jpg">
    </div>

    <div class="mb-2 col-6">
    <h1>로그인</h1>
    <form method="post" id="loginForm" action="/log/sign_in" >
    	<div class="input-gruop">
			<%--input-group-prepend--%>
				<div class="input-group-prepend">
					<span class="input-group-text">ID</span>
				<input type="text" class="form-control" id="loginId" name="loginId">
				</div>
			</div>
			
			<div class="input-gruop mt-3 mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				<input type="text" class="form-control" id="password" name="password">
				</div>
			</div>
			
			<input type="submit" class="mb-3 btn btn-primary btn-block" value="로그인">
			<a href="/marondalgram/log/sign_up" class="btn btn-dark btn-block">회원가입</a>
		
    
    </form>
    
    </div>
    
    
<script type="text/javascript">


	$(document).ready(function() {
		
		
		 let bannerSrcArr = ['/static/images/ke.jpg', '/static/images/love.jpg',
			 '/static/images/team.jpg', '/static/images/ym1.jpg'];
                var currentIndex = 0;
                setInterval(function() {
                    $('#imageControl').attr('src', bannerSrcArr[currentIndex]);
                    currentIndex++;

                    if (currentIndex >= bannerSrcArr.length) {
                        currentIndex = 0;
                    }
                }, 3000); 
		

		$('#loginForm').submit(function(e) {
			e.preventDefault();

			let loginId = $('#loginId').val().trim();
			if (loginId == '') {
				alert('아이디를 입력하세요');
				return;
			}

			let password = $('#password').val();
			if (password == '') {
				alert('비밀번호를 입력하세요');
				return;
			}

			let url = $(this).attr("action");
			let params = $(this).serialize();
			$.post(url, params).done(function(data) {

				if (data.result == 'success') {
					alert('로그인성공');
					location.href = '/timeline';
				} else {
					alert('로그인에 실패했습니다.');
				}

			});
		});

	});
</script>
    