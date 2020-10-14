$(function(){
	$.post("/forum/getcuruser",null,function(data){
		
		$("#uid").val(data.uid);
		$.post("/forum/searchtargetuser",{"uid":$("#uid").val()},function(data){
			if(data){
				if(!data.portrait){
					$("#portraitnum").val(0);
				}
				else{
					$("#portraitnum").val(data.portrait);
				}
				var h=document.getElementById("myportrait");
				h.setAttribute('src','material/hPortrait'+$("#portraitnum").val()+'.jpg');
				var namerow=$("<tr></tr>").appendTo("#informationtable");
				$("<td style='text-align: center;vertical-align: middle;'><b>用户名</b></td>").appendTo(namerow);
				$("<td style='text-align: center;vertical-align: middle;'></td>").html(data.username).appendTo(namerow);
				
				var emailrow=$("<tr></tr>").appendTo("#informationtable");
				$("<td style='text-align: center;vertical-align: middle;'><b>电子邮箱</b></td>").appendTo(emailrow);
				$("<td style='text-align: center;vertical-align: middle;'></td>").html(data.email).appendTo(emailrow);
				
				var sexrow=$("<tr></tr>").appendTo("#informationtable");
				$("<td style='text-align: center;vertical-align: middle;'><b>性别</b></td>").appendTo(sexrow);
				$("<td style='text-align: center;vertical-align: middle;'></td>").html(data.sex).appendTo(sexrow);
			}
		},"json");
		
	},"json");
	
	$("#changePortrait").click(function(){
		$("#trigger").click();
	});
	
	$("#recordtrigger").click(function(){
		$("#modalmore").click();
		$("#recordtable tr:gt(0)").remove();
		$.post("/forum/searchrecords",{"uid":$("#uid").val()},function(data){
			for(var i=1;i<=data.length;i++){
				var row=$("<tr></tr>").appendTo("#recordtable");
				$("<td style='text-align: center;'></td>").html(i).appendTo(row);
				$("<td style='text-align: center;'></td>").html(new Date(data[i-1].logtime).toLocaleString()).appendTo(row);
			}
		},"json");
	});
	
	$(".changenum").click(function(){
		$("#portraitnum").val($(this).attr('value'));
	});
	
	$("#confirm").click(function(){
		var uid=$("#uid").val();
		var portrait=$("#portraitnum").val();
		$.post("/forum/modportrait",{"uid":uid,"portrait":portrait},function(data){
			if(data=="true"){
				alert("更改成功！");
				var h=document.getElementById("myportrait");
				h.setAttribute('src','material/hPortrait'+portrait+'.jpg');
				$("#close").click();
			}
			else{
				alert("更改失败");
			}
		},"text");
	});
	
	
	
});