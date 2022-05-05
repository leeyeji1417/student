<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	table{
		width:200px;
	}
	table button{
		width:100%;
		height:100%;
	}
</style>
</head>
<body>
	<table border=1 align=center>
		<tr>
			<th>Student Management
		</tr>
		<tr>
			<td><button id="insert">New Student</button>
		</tr>
		<tr>
			<td><button id="selectAll">Student List</button>
		</tr>
		<tr>
			<td><button id="delete">Delete</button>
		</tr>
		<tr>
			<td><button id="update">Modify</button>
		</tr>
	</table>
	<script>
		$("#insert").on("click",function(){
			location.href="input.jsp";
		})
		$("#selectAll").on("click",function(){
			location.href="ListController";
		})
		$("#delete").on("click",function(){
			location.href="DeleteListController";
		})
		$("#update").on("click",function(){
			location.href="UpdateListController";
		})
		
		
	</script>
</body>
</html>