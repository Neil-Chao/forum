function searchComments(){
	$(".comment").hide();
	
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
			$.ajax({
				type:"post",
				url:"/forum/searchallcomments",
				data:{"pid":$("#pid").val(),"pageNum":$("#pageNum").val(),"pageSize":$("#pageSize").val()},
				dataType:"json",
				async:false,
				success:function(data){
					if(data&&data.size>0){
						//添加总页数
						$("#totalPage").html(data.pages);
						//添加当前页数
						$("#curPage").html(data.pageNum);
						/* alert(data.list.length); */
						for(var i=0;i<data.list.length;i++){
							
							var comment=data.list[i];
							var seq=i;
						
							$("#postcontent>div:eq("+seq+")").css("display","block");
							$("#postcontent>div:eq("+seq+") b:eq(0)").html(comment.text);
							
							
							var uid=comment.uid;
							$.ajax({
								type:"post",
								url:"/forum/searchuserbyid",
								dataType:"json",
								data:{"uid":uid},
								async:false,
								success:function(data1){
									$("#postcontent>div:eq("+seq+") div:eq(1) a:eq(0)").html(data1.username);
								},
								error:function(msg){
									
								}
							});
							
							$("#postcontent>div:eq("+seq+") span:eq(1)").html(new Date(comment.commenttime).toLocaleString());
							
					
						}
					}
				},
				error:function(msg){
					alert(msg);
				}
			});
		}
	},"json");
	
	
	$.post("/forum/getcurvisit",null,function(data){
		if(data){
			$("#uid").val(data.uid);
		}
	},"json");
		
		
}

$(function(){
	
	
	searchComments();
	
	$("#reply").click(function(){
		var text=$("#textarea").val();
		if(!text){
			alert("请输入评论内容...");
		}
		else{
			$.post("/forum/insertcomment",{"pid":$("#pid").val(),"uid":$("#uid").val(),"text":text},function(data){
				if(data=="true"){
					searchComments();
					$("#textarea").val("");
				}
				else{
					alert("评论失败");
				}
			},"text");
		}
		
	});
	
	
	
	
	
	
	
	
	
});