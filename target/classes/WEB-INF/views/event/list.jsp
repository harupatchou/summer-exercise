<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>イベント情報検索画面</title>
<link rel="stylesheet" href="../css/style.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/jquery.tablePagination.0.1.js"></script>
<script src="/js/window/openWindow.js"></script>
<script src="/js/search/paging.js"></script>
<script src="/js/search/toggle.js"></script>
<script src="/js/lib/ajax.js"></script>

</head>
<body>
	

	
	
	<h2>イベント情報検索</h2>
	<c:out value="${user.user.name}" />
	さん こんにちは！
	<br>
	<a href="/logout/sessionInvalidate">ログアウト</a>
	<a href="/events">イベント情報登録画面</a>
	
		<br><br><input type="radio" name="target" value="time" checked>時系列検索
		<input type="radio" name="target" value="detail">詳細検索<br>
	
	
	<section id="time" class="active" >
		<form:form action="/search/findAll" method="post">
			<input type="hidden" name="userId" value="${user.user.id}" id="ajaxIdAll"> 
			<input type="button" value="全件検索" id="ajaxSearchAll">
		</form:form>
		<form:form modelAttribute="eventForm" action="/search/findByYear">
			<form:select path="year" items="${yearMap}"  id="ajaxYearYear"/>
			<input type="hidden" name="userId" value="${user.user.id}" id="ajaxIdYear">
			<input type="button" value="年間検索" id="ajaxSearchYear">
		</form:form>
		<form:form modelAttribute="eventSearchForm" action="/search/findByYearAndMonth">
			<form:select path="year" items="${yearMap}" id="ajaxYearMonth"/>
			<form:select path="month" items="${monthMap}" id="ajaxMonthMonth"/>
			<form:hidden path="userId" value="${user.user.id}" id="ajaxIdMonth"/>
			 
			<input type="button" value="月間検索" id="ajaxSearchMonth">
		</form:form>
	</section>
	

	<section id="detail" class="passive">
			<form:form modelAttribute="eventSearchForm" action="/search/all" name="EventForm">
		<input type="hidden" name="id" value="${param.id}">
		<table>
			<tr>
				<th>イベント名</th>
				<td><form:input path="name" size="60" id="ajaxName"/></td>
			</tr>
			<tr>
				<th>キャスト</th>
 				<td><form:input path="cast" size="60" id="ajaxCast"/>
 				<input type="button" value="キャスト一覧" onClick="return openWin('/events/flowSelectCast')"></td> 
			</tr>
			<tr>
				<th>開催地</th>
				<td><form:input path="place" size="60" id="ajaxPlace"/></td>
			</tr>
			<tr>
				<th>チケット代</th>
				<td><form:input path="price" size="5" id="ajaxPrice"/>
				<select name="ticketFlag" id="ajaxTicketFlag">
					<option value= "1" checked="checked">以上</option>
					<option value="2">以下</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>開催日</th>
				<td><form:input path="year" size="5" id="ajaxYear"/>年 <form:input path="month" size="5" id="ajaxMonth"/>月 <form:input path="day" size="5" id="ajaxDay"/>日</td>
			</tr>
		</table>
		<form:hidden path="button" value="2"/>
		<form:hidden path="userId" value="${user.user.id}" id="ajaxId"/>
		<input type="button" value="検索" id="ajaxSearchDetail"/>
	</form:form>
	</section>
	
	
	<%-- Ajax用時計 --%>
		<SCRIPT>
		dd = new Date();
		document.write(dd.toLocaleString());
	</SCRIPT>
	
	<div id="ajaxSearchDisplay">
		<c:import url="/WEB-INF/views/event/ajaxSearch.jsp" charEncoding="UTF-8"/>
	</div>

</body>
</html>