<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/jquery.tablePagination.0.1.js"></script>
<script src="/js/search/paging.js"></script>
<title>Insert title here</title>
</head>
<body>
	<br>
	<c:if test="${eventList.size() != null}">
		該当件数 <c:out value="${eventList.size()}"/> 件
	</c:if>
	
	<table border="1" id="page">
	<thead>
		<tr>
			<th width="650">イベント名</th>
			<th width="270">キャスト</th>
			<th width="370">開催地</th>
			<th width="80">チケット代</th>
			<th width="160">開催日</th>
			<th colspan="2">編集</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="event" items="${eventList}">
			<tr>
				<td><c:out value="${event.name}" /></td>
				<td><c:out value="${event.cast}" /></td>
				<td><c:out value="${event.place}" /></td>
				<td><fmt:formatNumber value="${event.price}" pattern="###,###" />円</td>
				<td><c:out value="${event.year}" />年 <c:out value="${event.month}" />月 <c:out value="${event.day}" />日</td>
				<td>
					<form action="/events/editForm" method="get">
						<input type="submit" name="form" value="編集" />
						<input type="hidden" name="id" value="<c:out value="${event.id}"/>" />
					</form>
				</td>
				<td>
					<form action="/events/delete" method="post">
						<input type="submit" value="削除" />
						<input type="hidden" name="id" value="<c:out value="${event.id}"/>" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
</body>
</html>