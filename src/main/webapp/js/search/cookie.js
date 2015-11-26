$(function(){
	n = ($.cookie('opened'));

	if (n > 0) {
	    $(".tab li").removeClass("active");
	    $(".tab li").eq(n).addClass("active");
	    $(".tab").nextAll(".panel").hide();
	    $(".tab").nextAll(".panel").eq(n).show();
	}
	$(".tab li").click(function(){
	    var index = $(this).parent("ul").children("li").index(this);
	    $(this).siblings("li").removeClass("active");
	    $.cookie("opened",index);
	    $(this).addClass("active");
	    $(this).parent("ul").nextAll(".panel").hide();
	    $(this).parent("ul").nextAll(".panel").eq(index).show();
	});
	})