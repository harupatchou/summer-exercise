<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app>
<head>
<meta charset="UTF-8">
<title>キャスト情報更新画面</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>
<script type="text/javascript">
$(function(){
	$("input[name='target']").on("change",function(){
		$("#regist, #update").toggleClass("active passive");
	});
});

</script>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

		<input id="chk_1" type="radio" name="target" value="regist" checked>登録
		<input id="chk_2" type="radio" name="target" value="update">更新・削除

		<ol id="update" class="passive">
		<form:form modelAttribute="castForm" action="/search/findByCastId">
			変更したいキャストを選択<form:select path="castName" items="${castList}"/>
			<input class="btn" type="submit" value="選択"/>	
		</form:form>
		</ol>
		<form:form modelAttribute="castForm" action="/events/saveCast" name="EventForm">
		<table class="table table-striped">
			<tr>
	
			</tr>
			<tr>
				<th>キャスト名</th>
				<td>
				<form:input path="castName" placeholder="キャスト名" ng-model="castName" ng-init="castName='${cast.castName}'" required="required"/><br>
				<span ng-cloak style="color: red" ng-show="EventForm.castName.$invalid && EventForm.castName.$dirty">※キャスト名を入力してください。</span>
				</td>
			</tr>
			<tr>
				<th>読み仮名</th>
				<td><form:input path="reading" placeholder="読み仮名" ng-model="reading" ng-init="reading='${cast.reading}'" required="required"/><br>
				<span ng-cloak style="color: red" ng-show="EventForm.reading.$invalid && EventForm.reading.$dirty">※読み仮名を入力してください。</span></td>
			</tr>
		</table>
				<form:hidden path="castId" value="${cast.castId}"/>
				<input class="btn" type="submit" value="登録" > 
		</form:form>
		<form action="/events/deleteCast">
			<input type="hidden" name="castId" value="${cast.castId}"/>
			<input type="submit" value="削除" />
		</form>		
				<input type="button" value="閉じる" onclick="window.close()"/>
</body>
</html>