<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/common/layout.jsp">
	<c:param name="content">
	
	<%--ここから下にコンテンツを挿入 --%>

	<form:form modelAttribute="loginForm" action="/loginUser/login" name="LoginForm" id="formId">
	<table >
		<tr>
			<th>メールアドレス：</th>
			<td><form:input path="mailAddress" value="${loginForm.mailAddress}" placeholder="Email" class="validate[required]"/></td>
	 	</tr>
	 	<tr>
	 		<th>パスワード：</th>
	 		<td><form:password path="password" placeholder="Password" class="validate[required]"/></td>
		</tr>
	 </table>
	 	<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
	 	<input type="submit" value="ログイン" />
	</form:form>
	<a href="/user/form" id="toInsertMember">メンバー登録はこちらから</a>
	
	<%--ここから上にコンテンツを挿入 --%>

	</c:param>
</c:import>