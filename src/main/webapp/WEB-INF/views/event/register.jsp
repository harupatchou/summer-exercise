<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html ng-app>
<head>
<meta charset="UTF-8">
<title>イベント情報登録画面</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>
<script src="/js/window/openWindow.js"></script>
</head>
<body>

	<h2>イベント情報登録</h2>
	<c:out value="${user.user.name}" />さん こんにちは！
	<br><a href="/logout/sessionInvalidate">ログアウト</a>
	
	<form:form modelAttribute="eventForm" action="/events/create" name="EventForm">
		<table>
			<tr>
				<th>イベント名</th>
				<td><form:input path="name" ng-model="name" ng-init="name='${event.name}'" required="required" size="60"/>
				<span ng-cloak style="color: red" ng-show="EventForm.name.$invalid && EventForm.name.$dirty">※イベント名を入力してください。</span>
					</td>
			</tr>
			<tr>
				<th>キャスト</th>
 				<td>
 				<form:input path="cast" ng-model="cast" ng-init="cast='${event.cast}'" required="required" size="60"/>
 				<input type="button" value="キャスト一覧" onClick="return openWin('/events/flowSelectCast')">
 				<span ng-cloak style="color: red" ng-show="EventForm.cast.$invalid && EventForm.cast.$dirty">※キャスト名を入力してください。</span>
				</td> 
			</tr>
			<tr>
				<th>開催地</th>
				<td><form:input path="place" ng-model="place" ng-init="place='${event.place}'" required="required" size="60"/>
				<span ng-cloak style="color: red" ng-show="EventForm.place.$invalid && EventForm.place.$dirty">※開催地を入力してください。</span>
					</td>
			</tr>
			<tr>
				<th>チケット代</th>
				<td><form:input path="price" ng-model="price" ng-init="price='${event.price}'" required="required" ng-pattern="/^[0-9]+$/" size="5"/>円 
				<span ng-cloak style="color: red" ng-show="EventForm.price.$invalid && EventForm.price.$dirty">※チケット代を入力してください。</span>
				<span ng-cloak style="color: red" ng-show="EventForm.price.$error.pattern">※数値を入力してください</span> </td>
			</tr>
			<tr>
				<th>開催日</th>
				<td>
					<select name="year" onchange="setSelectMonth()"></select> 年
					<select name="month" onchange="setSelectDate()"></select> 月
					<select name="day"></select> 日
					
					<!-- スクリプト -->
					<script type="text/javascript">
					/*====[ 現日付を取得 ]====*/

					var Now = new Date();
					var NowYear = Now.getFullYear();
					var NowMonth = Now.getMonth()+1;
					var NowDate = Now.getDate();

					/*====[ うるう年判定 ]====*/

					function Uruu( Year ){
						var uruu = ( Year%400==0 ) ? true : ( Year%100==0 ) ? false : ( Year%4==0 ) ? true : false;
						return uruu;
					}
					/*====[ 関数 : オプションを更新（年） ]====*/

					function setSelectYear(){
						for(var y=NowYear;y<NowYear+5;y++){
						var select = document.EventForm.year;
						var option = select.appendChild( document.createElement('option') );
							option.value = y;
							option.text = y;
							option.selected = ( y==NowYear ) ? 'selected' : false;
					}
					setSelectMonth();
					}
					setSelectYear();
					/*====[ 関数 : オプションを更新（月） ]====*/

					function setSelectMonth(){
						var Year = document.EventForm.year.options[document.EventForm.year.selectedIndex].value;
						var select = document.EventForm.month;
						while( select.options.length ){
							select.removeChild( select.options[0] );
						}
						for(var m=1;m<=12;m++){
							var option = select.appendChild( document.createElement('option') );
								option.value = m;
								option.text = m;
								option.selected = ( Year==NowYear ) ?( ( m==NowMonth ) ? 'selected' : false ) :( ( m==1 ) ? 'selected' : false );
								option.disabled = ( Year==NowYear ) ?( ( m<NowMonth ) ? 'disabled' : false ) : false;
					}
					setSelectDate();
					}
					/*====[ 関数 : オプションを更新（日） ]====*/

					function setSelectDate(){
						var Year = document.EventForm.year.options[document.EventForm.year.selectedIndex].value;
						var Month = document.EventForm.month.options[document.EventForm.month.selectedIndex].value;
						var days = [31,( Uruu(Year) ? 29 : 28 ),31,30,31,30,31,31,30,31,30,31];
						var select = document.EventForm.day;
						while( select.options.length ){
							select.removeChild( select.options[0] );
						}
						for(var d=1;d<=days[Month-1];d++){
						var option = select.appendChild( document.createElement('option') );
							option.value = d;
							option.text = d;
							option.selected =( Year==NowYear && Month==NowMonth ) ?( ( d==NowDate ) ? 'selected' : false ) : ( ( d==1 ) ? 'selected' : false ); 
							option.disabled =( Year==NowYear && Month==NowMonth ) ?( ( d<NowDate ) ? 'disabled' : false ) : false;
					}
					}

					</script>
				  </td>
			</tr>
			<tr>
				<td></td>
				<td><form:hidden path="userId" value="${user.user.id}"/>
				<input class="btn" type="submit" value="登録">
				<input type="button" value="戻る" onclick="location.href='/search'"/></td>
			</tr>
		</table>
	</form:form>

</body>
</html>