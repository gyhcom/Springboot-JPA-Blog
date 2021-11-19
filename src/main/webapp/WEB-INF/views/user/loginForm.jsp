<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="../layout/header.jsp" %>
<div class="container">
		<form action="/auth/loginProc" method="post">
		<div class="form-group">
		    <label for="username">Username</label>
		    <input type="text" name="username"class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
		    <label for="pwd">Password</label>
		    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
			<button id="btn-login" class="btn btn-primary">Login</button>
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=b379b98c4002b9d631b0f6813686ef50&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
			<img src ="/images/kakao_login.png" height="38px"></a>
		</form>
</div>

<%@ include file="../layout/footer.jsp"%>