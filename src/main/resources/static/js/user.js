let index ={
	init:function(){
		$("#btn-save").on("click",()=>{ //function(){} , ()=>{} this를 바인딩하기 위해
			this.save();
		});
		$("#btn-update").on("click",()=>{ //function(){} , ()=>{} this를 바인딩하기 위해
			this.update();
		});
	},
	
	save: function(){
		let data ={
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email ").val()
		}
	//console.log(data);
	
	//ajax 호출시 default가 비동기 호
	$.ajax({
		type: "POST",
		url: "/auth/joinProc",
		data: JSON.stringify(data), //http body데이터
		contentType:"application/json; charset=utf-8",//body type이 어떤 타입인
		dataType:"json" //요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자(생긴것이 json이라면) => javascript오브젝트로 변경
	}).done(function(resp){
		alert(resp.status);
		if(resp.status === 500){
			alert("회원가입이 실패하였습니다.");		
		}else{
			alert("회원가입이 완료되었습니다");
			//location.href="/";		
		}
	}).fail(function(error){
		console.log(error);
		alert("에러발생");
	
	});//ajax통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert 요청
	
	},
	
	update: function(){
		let data ={
			id : $("#id").val(),
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email ").val()
		}
	//console.log(data);
	
	//ajax 호출시 default가 비동기 호
	$.ajax({
		type: "PUT",
		url: "/user",
		data: JSON.stringify(data), //http body데이터
		contentType:"application/json; charset=utf-8",//body type이 어떤 타입인
		dataType:"json" //요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자(생긴것이 json이라면) => javascript오브젝트로 변경
	}).done(function(resp){
		alert("회원수정이 완료되었습니다");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	});//ajax통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert 요청
	
	}
}

index.init();