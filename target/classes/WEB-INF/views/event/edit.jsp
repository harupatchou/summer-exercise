<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html ng-app>
<head>
<meta content="text/html; charset=UTF-8">
<title>イベント情報変更画面</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>
<script src="/js/window/openWindow.js"></script>
</head>
<body>
<c:out value="${user.user.name}"></c:out><c:out value="${user.user.name}"></c:out><c:out value="${user.user.name}"></c:out>
	<h2>イベント情報編集</h2>
	<c:out value="${user.user.name}" />
	さん こんにちは！
	<form:form modelAttribute="eventForm" action="/events/edit" name="EventForm" >
		<input type="hidden" name="id" value="${param.id}">
		<table>
			<tr>
				<th>イベント名</th>
				<td><form:input path="name" ng-init="name='${event.name}'" ng-model="name" required="required" size="60"/>
					<span ng-cloak style="color: red" ng-show="EventForm.name.$invalid && EventForm.name.$dirty">※イベント名を入力してください。</span>  
					</td>
			</tr>
			<tr>
				<th>キャスト</th>
 				<td><form:input path="cast" ng-init="cast='${event.cast}'" ng-model="cast" required="required" size="60"/>
 				<input type="button" value="キャスト一覧" onClick="return openWin('/events/flowSelectCast')">
				<span ng-cloak style="color: red" ng-show="EventForm.cast.$invalid && EventForm.cast.$dirty">※キャスト名を入力してください。</span>
				</td> 
			</tr>
			<tr>
				<th>開催地</th>
				<td><form:input path="place" ng-init="place='${event.place}'" ng-model="place" required="required" size="60" />
				<span ng-cloak style="color: red" ng-show="EventForm.place.$invalid && EventForm.place.$dirty">※開催地を入力してください。</span>
					</td>
			</tr>
			<tr>
				<th>チケット代</th>
				<td><form:input path="price" ng-init="price='${event.price}'" ng-model="price" required="required" size="5" />
				<span ng-cloak style="color: red" ng-show="EventForm.price.$invalid && EventForm.price.$dirty">※チケット代を入力してください。</span>
				<span ng-cloak style="color: red" ng-show="EventForm.price.$error.pattern">※数値を入力してください</span> </td>
			</tr>
			<tr>
				<th>開催日</th>
				<td><form:input path="year" size="5" />年 <form:input
						path="month" size="5" />月 <form:input path="day" size="5" />日 </td>
			</tr>
		</table>
		<form:hidden path="userId" value="${user.user.id}"/>
		<input type="submit" value="更新" />
		<input type="submit" name="goToTop" value="戻る" />
	</form:form>
</body>
</html>