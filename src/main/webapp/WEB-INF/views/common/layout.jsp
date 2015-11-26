<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html ng-app xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/print.css">
<link rel="stylesheet" href="/css/validationEngine.jquery.css">
<link rel="stylesheet" href="/css/style.css">
<script src="/js/lib/angular.min.js" type="text/javascript"></script>
<script src="/js/lib/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="/js/lib/jquery.validationEngine.js" type="text/javascript"></script>
<script src="/js/lib/jquery.validationEngine-ja.js" type="text/javascript"></script>
<script src="/js/jquery.tablePagination.0.1.js"></script>
<script src="/js/window/openWindow.js"></script>
<script src="/js/search/paging.js"></script>
<title>spec管理</title>
</head>
<body>

<header class="printDemo noPrint">
	<c:import url="/WEB-INF/views/common/header.jsp" charEncoding="UTF-8"/>
</header>
<article>
	${param.content}
</article>
<footer class="printDemo noPrint">
	<c:import url="/WEB-INF/views/common/fotter.jsp" charEncoding="UTF-8"/>
</footer>

</body>
</html>