<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/common/layout.jsp">
	<c:param name="content">
	
	<%--ここから下にコンテンツを挿入 --%>
	
	<h2>ユーザー情報登録</h2>
	
	<form:form modelAttribute="userForm" action="/user/create" name="user" >
				
				<p>氏名:
				<form:input path="name" placeholder="Name" ng-model="name" required="required"/>
				<span ng-cloak style="color: red" ng-show="user.name.$invalid && user.name.$dirty">※ユーザー名を入力してください。</span></p>
				<p>メールアドレス:
				<form:input path="mailAddress" placeholder="Email" ng-model="mailAddress" required="required"/>
				<span ng-cloak style="color: red" ng-show="user.mailAddress.$invalid && user.mailAddress.$dirty">※メールアドレスを入力してください。</span></p>
				
				<p>パスワード:
				<form:password path="password" placeholder="Password" ng-model="password" required="required"/>
				<span ng-cloak style="color: red" ng-show="user.password.$invalid && user.password.$dirty">※パスワードを入力してください。</span></p>
				<p>
				パスワード確認:
				<form:password path="secondPassword" placeholder="Password" ng-model="secondPassword" required="required"/>
				<span ng-cloak style="color: red" ng-show="user.secondPassword.$invalid && user.secondPassword.$dirty">※確認用パスワードを入力してください。</span></p>
				<input class="btn" type="submit" value="登録"> <input type="button" value="戻る" onclick="location.href='/loginiUser/'"/>
				
	</form:form>
	
	<%--ここから上にコンテンツを挿入 --%>

	</c:param>
</c:import>
