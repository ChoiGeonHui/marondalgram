<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="d-flex justify-content-center" id="timecontent">
	<div>
		<div class="mt-2 d-flex justify-content-center">

			<div>

				<textarea rows="5px" cols="50px" placeholder="내용을 입력해주세요">
	
			</textarea>
				<br>
				<button class="btn btn-info">업로드</button>
			</div>

		</div>


		<div class="bg-secondary mt-2 d-flex justify-content-between">
			<b> <%-- ${userName} --%>marobiana</b>
			<a href="#"><img height="25px" alt="사진" src="/static/images/moreicon.jpg"></a>
		</div>
		<div class="mt-2 col-12">
			<img class="col-12" alt="사진"
				src="/static/images/milky.jpg">
		</div>
		<div class="mt-2">
			<img height="20px" alt="좋아요" src="/static/images/heart.jpg"><b>좋아요 <%-- ${like.count }--%> 30개
			</b>
		</div>

		<div class=" mt-2" id="title">
			<b> <%-- ${userName} --%>marobiana</b> 
			<span class="col-12"> <!--${post.content}  --> 밤하늘을 촬영했습니다~아름다워요~~~~~~</span>
		</div>

		<div>
			<div class="bg-secondary mt-2">
				<b> 댓글 </b>
			</div>
			<table>

				<%-- <c:forEach var="coment" items="${coment}"> --%>

				<tr>
					<th>아이디1 <!-- ${coment.userId} -->
					</th>
					<td>너무 이뻐요 <!-- ${coment.content} -->
					</td>
					<td><img height="15px" alt="닫기" src="/static/images/xi.jpg"> </td>
				</tr>

				<%-- </c:forEach> --%>
			</table>

		</div>
		
		<div class="mt-2 mb-2 input-group col-12">
		<div class="input-group-prepend">
		<input type="text" class="form-control">
		<button class="text-info btn btn-white">게시</button>
		</div>
		</div>


	</div>
</div>