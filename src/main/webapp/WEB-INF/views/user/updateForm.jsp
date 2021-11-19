<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en" <%@ include file="../layout/header.jsp" %>
	<div class="container">
		<form action="/action_page.php">
		  <div class="form-group">
		  <input type="hidden" id="id" value=${principal.user.id }
		    <label for="username">Username</label>
		    <input type="text" value=${principal.user.username } class="form-control" placeholder="Enter username" id="username" readonly>
		  </div>
		  
		  <div class="form-group">
		    <label for="pwd">Password</label>
		    <input type="password" class="form-control" placeholder="Enter password" id="password">
		  </div>

		  <div class="form-group">
		    <label for="email">Email</label>
		    <input type="email" value=${principal.user.email } class="form-control" placeholder="Enter email" id="email">
		  </div>
		  
		</form>
		<button id="btn-update" class="btn btn-primary">update</button>
	</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>