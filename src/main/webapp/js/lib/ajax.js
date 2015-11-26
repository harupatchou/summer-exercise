

$(function(){
	//非同期検索(詳細検索)
	$("#ajaxSearchDetail").click(function(){
	
		var JSONdata = {
				userId: $("#ajaxId").val(),
				name: $("#ajaxData").val(),
				place: $("#ajaxPlace").val(),
				cast: $("#ajaxCast").val(),
				price: $("#ajaxPrice").val(),
				ticketFlag: $("#ajaxTicketFlag").val(),
				year: $("#ajaxYear").val(),
				month: $("#ajaxMonth").val(),
				day: $("#ajaxDay").val()
		};

		$.ajax({
			type:'post',
			url:'/search/ajaxSearch',
			dataType:'json',
			data:JSON.stringify(JSONdata),
			contentType:'application/JSON',
			scriptCharset:'utf-8'
		
		}).done(function(data, textStatus, jqXHR){
			//成功時処理
			alert("成功");
			$('#ajaxSearchDisplay').load('/search/ajaxSearchIndex');
		}).fail(function(data){
			//失敗時処理
			$('#ajaxSearchDisplay').load('/search/ajaxSearchIndex');
		});
	
	});

	//非同期検索(全件検索)
	$("#ajaxSearchAll").click(function(){
	
		var JSONdata = {
				"userId": $("#ajaxIdAll").val(),
		};
	
		$.ajax({
			type:'post',
			url:'/search/ajaxSearch',
			dataType:'json',
			data:JSON.stringify(JSONdata),
			contentType:'application/JSON',
			scriptCharset:'utf-8'
		
		}).done(function(data, textStatus, jqXHR){
			//成功時処理
			alert("成功");
			$('#ajaxSearchDisplay').load('/search/ajaxSearchIndex');
		}).fail(function(data){
			//失敗時処理
			$('#ajaxSearchDisplay').load('/search/ajaxSearchIndex');
		});
	});
	
	//非同期検索(年検索)
	$("#ajaxSearchYear").click(function(){
		
		var JSONdata = {
				"userId": $("#ajaxIdYear").val(),
				"year": $("#ajaxYearYear").val()
		};
	
		$.ajax({
			type:'post',
			url:'/search/ajaxSearch',
			dataType:'json',
			data:JSON.stringify(JSONdata),
			contentType:'application/JSON',
			scriptCharset:'utf-8'
		
		}).done(function(data, textStatus, jqXHR){
			//成功時処理
			alert("成功");
			$('#ajaxSearchDisplay').load('/search/ajaxSearchIndex');
		}).fail(function(data){
			//失敗時処理
			$('#ajaxSearchDisplay').load('/search/ajaxSearchIndex');
		});
	});
	
	//非同期検索(年検索)
	$("#ajaxSearchMonth").click(function(){
		
		var JSONdata = {
				"userId": $("#ajaxIdMonth").val(),
				"year": $("#ajaxYearMonth").val(),
				"month": $("#ajaxMonthMonth").val()
		};
	
		$.ajax({
			type:'post',
			url:'/search/ajaxSearch',
			dataType:'json',
			data:JSON.stringify(JSONdata),
			contentType:'application/JSON',
			scriptCharset:'utf-8'
		
		}).done(function(data, textStatus, jqXHR){
			//成功時処理
			alert("成功");
			$('#ajaxSearchDisplay').load('/search/ajaxSearchIndex');
		}).fail(function(data){
			//失敗時処理
			$('#ajaxSearchDisplay').load('/search/ajaxSearchIndex');
		});
	});
});