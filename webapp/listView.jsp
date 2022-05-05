<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student List</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	*{
		text-align:center;
	}
</style>
</head>
<body>
	<table border=1 align=center>
		<tr>
			<th colspan=8>Student List
		</tr>
		<tr>
			<th>rank
			<th>sid
			<th>name
			<th>kor
			<th>eng
			<th>math
			<th>sum
			<th>avg
		</tr>
		<c:forEach var="i" items="${list }">
			<tr>
				<td>${i.rank}
				<td>${i.sid }
				<td>${i.name }
				<td>${i.kor }
				<td>${i.eng }
				<td>${i.math }
				<td>${i.kor + i.eng + i.math }
				<td><fmt:formatNumber value="${(i.kor+i.eng+i.math)/3.0 }" pattern=".0"/>
			</tr>
		</c:forEach>
		<tr>
			<td colspan=8><input type=text name=name id=name placeholder="Name to search"><button id=search>Search</button>
		</tr>
		<tr>
			<td colspan=8><button id="back">back</button>
		</tr>
	</table>
	<script>
		$("#search").on("click",function(){
			location.href="SearchController?name="+$("#name").val();
		})
		$("#back").on("click",function(){
			location.href="index.jsp";
		})
	</script>
</body>
</html>