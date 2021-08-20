<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="d-flex justify-content-center" id="timecontent">
	<div>
		<div class="mt-2 d-flex justify-content-center">

			<div>

				<textarea rows="5" cols="90" placeholder="내용을 입력해주세요"></textarea>
				<div class="d-flex justify-content-between">
				<input id="file" name="file" accept=".jpg,.jpeg,.png,.gif" type="file" class="d-none">
				<a href="#"id="fileUploadBtn"><img width="35" src="/static/images/cm.jpg"></a>
						<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
						<div id="fileName" class="ml-2">
						</div>
				<button id="saveBtn" type="button" class="btn btn-info">업로드</button>
				</div>
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


<script>
$(document).ready(function(){
	$('#fileUploadBtn').on('click',function(e){
		e.preventDefault();
		$('#file').click();
	});
	
	
	//사용자가 파일 업로드를 했을 때
	$('#file').on('change',function(e){
		let fileName =e.target.files[0].name;
		
		//확장자
		let ext = fileName.split('.');
		
		if(ext.length<2 || 
				(ext[ext.length -1] != 'gif'
				&&ext[ext.length -1] != 'png'
				&&ext[ext.length -1] != 'jpg'
				&&ext[ext.length -1] != 'jpeg')){
			alert('부적절한 파일입니다');
			$(this).val("");
			return;
		}
		
		$('#fileName').text(fileName);
	});
	
	$('#saveBtn').on('click',function(e){
		let content = $('textarea').val();
		
		if(content.length <1){
			alert('내용을 입력해주세요');
			return;
		}
		
		
		
		let formData = new FormData();
		formData.append("content",content);
		formData.append("file",$('input[name=file]')[0].files[0]);
		
		$.ajax({
			method:'post',
			url:'/post/create',
			data:formData,
			processData:false,
			contentType:false,
			success:function(data){
				if(data.result=='success'){
					alert("저장되었습니다.");
					location.href="/timeline"
				}
			},
			error:function(e){
					alert("글작성에 실패했습니다. 로그인을 안했거나 파일 내부문제입니다.");
			}
		});
		
	});
	
	
});


</script>


