let index ={
	init:function(){
		$("#btn-save").on("click",()=>{ //function(){} , ()=>{} this를 바인딩하기 위해
			this.save();
		});
		$("#btn-delete").on("click",()=>{ //function(){} , ()=>{} this를 바인딩하기 위해
			this.deleteById();
		});
		$("#btn-update").on("click",()=>{ //function(){} , ()=>{} this를 바인딩하기 위해
			this.update();
		});
		$("#btn-reply-save").on("click",()=>{ //function(){} , ()=>{} this를 바인딩하기 위해
			this.replySave();
		});
	},
	
	save: function(){
		let data ={
			title : $("#title").val(),
			content : $("#content").val(),
		}
	//console.log(data);
	
	//ajax 호출시 default가 비동기 호
	$.ajax({
		type: "POST",
		url: "/api/board/",
		data: JSON.stringify(data),
		contentType:"application/json; charset=utf-8",
		dataType:"json"
	}).done(function(resp){
		alert("글쓰기가 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
	
	},
	deleteById: function(){
		let id =$("#id").text();
	$.ajax({
		type: "DELETE",
		url: "/api/board/"+id,
		data: JSON,
		contentType:"application/json; charset=utf-8",
		dataType:"json"
	}).done(function(resp){
		alert("삭제가 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
	
	},
	
	update: function(){
	let id = $("#id").val();
	
	let data ={
		title : $("#title").val(),
		content : $("#content").val(),
	}
	//console.log(data);
	
	//ajax 호출시 default가 비동기 호
	$.ajax({
		type: "PUT",
		url: "/api/board/"+id,
		data: JSON.stringify(data),
		contentType:"application/json; charset=utf-8",
		dataType:"json"
	}).done(function(resp){
		alert("글수정이 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
	},
	replySave: function(){
	let data ={
		content : $("#reply-content").val(),
	}
	let boardId = $("#boardId").val();
	console.log(data);
	
	//ajax 호출시 default가 비동기 호
	$.ajax({
		type: "POST",
		url: `/api/board/${boardId}/reply`,
		data: JSON.stringify(data),
		contentType:"application/json; charset=utf-8",
		dataType:"json"
	}).done(function(resp){
		alert("댓글등록이 완료되었습니다.");
		location.href=`/board/${boardId}`;
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
	
	}
}

index.init();