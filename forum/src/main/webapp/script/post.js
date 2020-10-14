$(function(){
	$.post("/forum/getcurhref",null,function(data){
		if(data){
			$("#pid").val(data.pid);
			$("#title").html(data.title);
			$("#text").html(data.text);
			$("#begintime").html(new Date(data.begintime).toLocaleString());
			$("#portrait").attr("uid",data.uid);
			$.post("/forum/searchuserbyid",{"uid":data.uid},function(data){
				if(data){
					$("#p").attr("src","material/hPortrait"+data.portrait+".jpg");
					$("#sponsorname").html(data.username);
				}
			},"json");
		}
	},"json");
	
	
});