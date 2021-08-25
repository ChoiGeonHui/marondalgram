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

<c:forEach var="content" items="${contentList}">
		<div class="bg-secondary mt-2 d-flex justify-content-between">
			<b>${content.post.userName}</b>
			<a href="#" class="moreBtn">
			<img height="25px" alt="더보기" src="/static/images/moreicon.jpg">
			</a>
		</div>
		<div class="mt-2 col-12">
		<c:if test="${not empty  content.post.imagePath}">
			<img class="col-12" alt="사진"
				src="${content.post.imagePath}">
		</c:if>	
		</div>
		<div class="mt-2">
		<a href="#" class="btnlike">
			<img height="20px" alt="좋아요" src="/static/images/heart.jpg">
		</a>
			<b>좋아요 ${content.likeCount } 개</b>
		</div>

		<div class=" mt-2" id="title">
			<b>${content.post.userName}</b> 
			<span class="col-12">${content.post.content}</span>
		</div>

		<div>
			<div class="bg-info mt-2">
				<b> 댓글 </b>
			</div>
			<table>


				<c:forEach var="comment" items="${content.commentlist}"> 
				<tr>
					<th>${comment.userName}
					</th>
					<td>${comment.content}
					</td>
					
							<c:if test="${userId eq comment.userId }">
								<td>
									<a href="#" class="btndeleteLike"> 
										<img height="15px" alt="닫기" src="/static/images/xi.jpg">
									</a>
								</td>
							</c:if>
						</tr>

				</c:forEach>
			</table>
				 <c:if test="${not empty userId}"> 
					<div class="mt-2 mb-2 input-group col-12">
						<div class="input-group-prepend">
							<input type="text" id="commentText${content.post.id}" class="form-control" placeholder="댓글을 작성하세요">
							<button class="text-info btn btn-white commentBtn" data-post-id="${content.post.id }">게시</button>
						</div>
					</div>
				</c:if>
				<hr>

		</div>
</c:forEach>
			


		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#MMM">
  Launch demo modal
</button>



	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="MMM" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
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
		
		
		
		
		
	})
	
	
	
	
	
	
});


</script>


