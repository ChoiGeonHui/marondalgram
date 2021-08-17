<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<header class="bg-secondary">
<div class="d-flex">
		<div class="col-3">
			<h1 class="text-white">Marondalgram</h1>
		</div>

		<div class="col-9 d-flex justify-content-end">

			<c:if test="${not empty userName}">
				<div class="mt-5">
					<span class="text-white"><b>${userName}</b>님 안녕하세요</span> <a
						class="font-weight-bold text-white ml-2"
						href="/marondalgram/log/log_out">로그 아웃</a>
				</div>
			</c:if>


			<c:if test="${empty userName}">
				<div class="mt-3">
					<a class="font-weight-bold text-white mr-3"
						href="/marondalgram/log/sign_in">로그인</a>
				</div>
			</c:if>
		</div>
	</div>
</header>