$(function(){
	$("input[name='target']").on("change",function(){
		$("#time, #detail").toggleClass("active passive");
	});
});