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
					<input id="file" name="file" accept=".jpg,.jpeg,.png,.gif"
						type="file" class="d-none"> <a href="#" id="fileUploadBtn"><img
						width="35" src="/static/images/cm.jpg"></a>
					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2"></div>
					<button id="saveBtn" type="button" class="btn btn-info">업로드</button>
				</div>
			</div>

		</div>

		<c:forEach var="content" items="${contentList}">
			<div class="bg-secondary mt-2 d-flex justify-content-between">
				<b class="text-white ml-2">${content.post.userName}</b>

				<c:if test="${content.post.userName eq userName}">
					<a href="#" class="moreBtn" data-toggle="modal"
						data-target="#moreSet" data-post-name="${content.post.userName}"
						data-post-id="${content.post.id}" data-post-image="${content.post.imagePath }" > 
						<img height="25px" alt="더보기" src="/static/images/moreicon.jpg">
					</a>
				</c:if>


			</div>
			<div class="mt-2 col-12">
				<c:if test="${not empty content.post.imagePath}">
					<b>${content.post.imagePath}</b>
					<img class="col-12" alt="사진" src="${content.post.imagePath}">
				</c:if>
			</div>
			<div class="mt-2">
			<c:choose>
			<c:when test="${content.fillLike eq true }">
						<a href="#" class="btnlikeNo" data-post-id="${content.post.id }">
							<img height="20px" alt="좋아요취소" src="/static/images/heart2.jpg">
						</a>
			
			</c:when>
			<c:otherwise>
						<a href="#" class="btnlike" data-post-id="${content.post.id }">
							<img height="20px" alt="좋아요" src="/static/images/heart.jpg">
						</a>
			</c:otherwise>
			</c:choose>
				<b>좋아요 ${content.likeCount } 개</b>
				
			</div>

			<div class=" mt-2" id="title">
				<b>${content.post.userName}</b> <span class="col-12">${content.post.content}</span>
			</div>

			<div>
				<div class="bg-info mt-2">
					<b> 댓글 </b>
				</div>
				<table>


					<c:forEach var="comment" items="${content.commentlist}">
						<tr>
							<th>${comment.userName}</th>
							<td>${comment.content}</td>
							
							<c:if test="${userId eq comment.userId }">
								<td><a href="#" class="btndeleteCom" data-post-comment="${comment.content}"  data-post-id="${content.post.id }"> 
								<img height="15px" alt="닫기" src="/static/images/xi.jpg">
								</a></td>
							</c:if>
						</tr>

					</c:forEach>
				</table>
				<c:if test="${not empty userId}">
					<div class="mt-2 mb-2 input-group col-12">
						<div class="input-group-prepend">
							<input type="text" id="commentText${content.post.id}"
								class="form-control" placeholder="댓글을 작성하세요">
							<button class="text-info btn btn-white commentBtn"
								data-post-id="${content.post.id }">게시</button>
						</div>
					</div>
				</c:if>
				<hr>

			</div>
		</c:forEach>

	</div>
</div>

<%-- 글 삭제를 위한 Modal Layer --%>
<div class="modal" id="moreSet" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-sm modal-dialog-centered" role="document">
		<div class="modal-content">
			<%-- Modal 창 안에 내용 넣기 --%>
			<div class="w-100">
				<div class="my-3 text-center">
					<a href="#" class="del-post d-block">삭제하기</a><%-- 클릭할 수 있는 영역을 넓히기 위해 d-block --%>
				</div>
				<div class="border-top py-3 text-center">
					<%-- data-dismiss: 모달창 닫힘 --%>
					<a href="#" class="cancel d-block" data-dismiss="modal">취소</a> <%-- 클릭할 수 있는 영역을 넓히기 위해 d-block --%>
				</div>
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
	
	
	
	//post삭제
	$('.del-post').on('click',function(e){
		e.preventDefault();
		let postId = $(".moreBtn").data('post-id');
		let postuserName =$(".moreBtn").data('post-name');
		
		$.ajax({
			method:'post',
			url:'/post/delete',
			data:{'postId':postId, 'postUserName':postuserName},
			success:function(data){
				if(data.result=="success"){
					alert("삭제 완료");
					location.reload(); // 새로고침
				}else{
					alert("오류발생");							
				}
				
				
			},
			error:function(e){
				alert("에러발생");
			}		
			
		});
		
		
	});
	
	
	
	
	
	//댓글 작성
	$(".commentBtn").on('click',function(e){
		e.preventDefault();
		
		
		let postId = $(this).data('post-id');
		let commetText = $('#commentText' + postId).val();
		
		if(commetText ==null){
			alert("댓글을 작성하세요");
			return;		
		}
		
		$.ajax({
			method:'post',
			url:'/comment/create',
			data:{'postId':postId,'content':commetText},
			success:function(data){
				if(data.result=="success"){
				alert("작성 완료");
				location.reload(); // 새로고침
					
				}else{
				alert("오류발생");				
				}
				
				
			},
			error:function(){
				alert("에러발생");
			}		
			
		});
		
	});
	
	
	
	//댓글삭제
	$('.btndeleteCom').on('click',function(e){
		e.preventDefault();
		let postId = $(this).data('post-id');
		let comment = $(this).data('post-comment');
		//alert(postId+" : "+ comment);
		
		$.ajax({
			method:'post',
			url:'/comment/delete',
			data:{'postId':postId,'content':comment},
			success:function(data){
				if(data.result=="success"){
					alert("댓글을 삭제 했습니다");
					location.reload(); // 새로고침
						
					}else{
					alert("오류발생");				
					}
			},
			error:function(){
				alert('에러발생');
			}
		});
		
		
	});
	
	
	
	//좋아요
	$('.btnlike').on('click',function(e){
		e.preventDefault();
		let postId = $(this).data('post-id');
		
		$.ajax({
			method:'post',
			url:'/like/like',
			data:{'postId':postId},
			success:function(data){
				if(data.result=="success"){
					alert("좋아요를 눌렀습니다");
					location.reload(); // 새로고침
						
					}else if(data.result=="userNo"){
					alert("먼저 로그인을 하세요.");
						
					}
				
				
				else{
					alert("오류발생");				
					}
			},
			error:function(){
				alert('에러발생');
			}
			
		});
	});
	//좋아요취소
	$('.btnlikeNo').on('click',function(e){
		e.preventDefault();
		let postId = $(this).data('post-id');
		
		$.ajax({
			method:'post',
			url:'/like/likeCancel',
			data:{'postId':postId},
			success:function(data){
				if(data.result=="success"){
					alert("좋아요취소 했습니다");
					location.reload(); // 새로고침
						
					}else if(data.result=="userNo"){
					alert("먼저 로그인을 하세요.");
						
					}
				
				
				else{
					alert("오류발생");				
					}
			},
			error:function(){
				alert('에러발생');
			}
			
		});
	});
	
	
	


	
	
});


</script>


