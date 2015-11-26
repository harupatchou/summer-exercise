<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>キャスト選択</title>
<link rel="stylesheet" href="/css/jquery-ui-1.9.2.custom.css">
<link rel="stylesheet" href="/css/jquery.multiselect.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/prettify.css">
<link rel="stylesheet" href="/css/specDetail.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/jquery-ui-1.9.2.custom.js"></script>
<script src="/js/jquery.multiselect.js"></script>
<script src="/js/jquery.table_transpose.js"></script>
<script src="/js/prettify.js"></script>

<!-- 親窓にデータ反映 -->
<script type="text/javascript">

function goBack(){
	var ckList=document.getElementsByName("cast");
	var value = "";
	for(var i=0;i<ckList.length;i++){
	if(ckList[i].checked)
	value+=(ckList[i].value)+",";
	}
	value = value.substr( 0 , (value.length-1) );
	window.opener.document.EventForm.cast.value=value;
	window.close();
	}
</script>

<!-- 選択解除 -->
<script type="text/javascript">
function unchecked(){
	$("input:checkbox").attr("checked",false); 
	$("option").attr("selected",false); 
}
</script>
<!-- テーブル縦横変換 -->
<script type="text/javascript">
$(document).ready(function(){
	$("#table1").table_transpose();
});

</script>

<!-- 別窓 -->
<script type="text/javascript">
var w = window;
function openWin(url) {
if ((w == window) || w.closed) {
w = open(url, "_blank", "width=300,height=300"); 
} else {
w.focus(); 
}
return(false);
}
</script>
</head>
<body>
		<div id="headerArea">
		<span id="heading" style="color: black">キャスト一覧(五十音順)</span><br>
		<input type="button" value="選択解除"  onClick="unchecked()">
		<button type="button" onclick="goBack()">確定</button>
		<input type="button" value="閉じる" onclick="window.close()"/><br>
		<input type="button" value="キャスト登録・更新" onClick="return openWin('/events/flowSaveCast')">
		<input type="button" value="更新" onclick="location.href='/events/flowSelectCast'">
		</div>

<form name="FORMA">
<table id="table1">
<c:forEach var="cast" items="${castList}">
	<td><span><input  value="${cast.castName}" name="cast" type="checkbox"><c:out value="${cast.castName}"/></span></td>
</c:forEach>
</table>
</form>

</body>
</html>