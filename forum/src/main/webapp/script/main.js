$(function(){
	$(".titletext").click(function(){
		$.post("/forum/href",{"pid":$(this).attr("pid")},function(data){
			if(!data.pid){
				alert("出错了...");
			}
		},"json");
	});
});


function searchPosts(){
	$(".post").hide();
	
	$.ajax({
		type:"post",
		url:"/forum/searchallposts",
		data:{"pageNum":$("#pageNum").val(),"pageSize":$("#pageSize").val()},
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
					
					var post=data.list[i];
					var seq=i;
				
					$("#posts>div:eq("+seq+")").css("display","block");
					$("#posts>div:eq("+seq+") a:eq(0)").html(post.title).attr("pid",post.pid);
					
					
					var uid=post.uid;
					$.ajax({
						type:"post",
						url:"/forum/searchuserbyid",
						dataType:"json",
						data:{"uid":uid},
						async:false,
						success:function(data1){
							$("#posts>div:eq("+seq+") div:eq(1) a:eq(0)").html(data1.username);
						},
						error:function(msg){
							
						}
					});
					
					
					/* $.post("/forum/searchuserbyid",{"uid":uid},function(data1){
						$("#posts>div:eq("+seq+") div:eq(1) a:eq(0)").html(data1.username);
					},"json"); */
					
					$("#posts>div:eq("+seq+") span:eq(1)").html(new Date(post.begintime).toLocaleString());
			
				}
			}
		},
		error:function(msg){
			alert(msg);
		}
	});
	
	
	
	/* $.post("/forum/searchallposts",{"pageNum":$("#pageNum").val(),"pageSize":$("#pageSize").val()},function(data){
		if(data&&data.size>0){
			//添加总页数
			$("#totalPage").html(data.pages);
			//添加当前页数
			$("#curPage").html(data.pageNum);
			
			for(var i=0;i<data.list.length;i++){
				
				var post=data.list[i];
				var seq=i;
			
				$("#posts>div:eq("+seq+")").css("display","block");
				$("#posts>div:eq("+seq+") a:eq(0)").html(post.title);
				
				var uid=post.uid;
				$.ajax({
					type:"post",
					url:"/forum/searchuserbyid",
					dataType:"json",
					async:false,
					success:function(data1){
						$("#posts>div:eq("+seq+") div:eq(1) a:eq(0)").html(data1.username);
					},
					error:function(msg){
						console.log(msg);
					}
				});
				
				
				 $.post("/forum/searchuserbyid",{"uid":uid},function(data1){
					$("#posts>div:eq("+seq+") div:eq(1) a:eq(0)").html(data1.username);
				},"json"); 
				
				$("#posts>div:eq("+seq+") span:eq(1)").html(new Date(post.begintime).toLocaleString());
		
			}
		}
	},"json"); */
}

$(function(){
	$.post("/forum/getcuruser",null,function(data){
		if(data){
			if(data.username!="root"){
				$("#welcome").show();
				$("#curname").html(data.username);
				$("#headfont1,#headfont2,#headfont3").hide();
				$("#fid").val(data.uid);
				var fid=$("#fid").val();
			}
			else{
				$("#welcome").show();
				$("#curname").html("管理员");
				$("#headfont1,#headfont2,#headfont3").hide();
				$("#fid").val(data.uid);
				var fid=$("#fid").val();
				$("#dropMenu li:eq(0) a:eq(0)").html("管理用户").attr("href","javascript:void(0)");
			}
		}
	},"json");
	
	$("#logout").click(function(){
		$.post("/forum/logout",null,function(){
			/* 进行注销 */
			$("#welcome").hide();
			$("#headfont1,#headfont2,#headfont3").show();
		},"text");
	});
	
	searchPosts();
	
	/* $.post("/forum/searchallposts",{"pageNum":1,"pageSize":$("#pageSize").val()},function(data){
		if(data&&data.size>0){
			//添加总页数
			$("#totalPage").html(data.pages);
			//添加当前页数
			$("#curPage").html(data.pageNum);
			for(var i=0;i<data.list.length;i++){
				$("#posts div:eq("+i+")").css("display","block");
				var post=data.list[i];
				var seq=i;
				$("#posts div:eq("+seq+") a:eq(0)").html(post.title);
				
				var uid=post.uid;
				$.post("/forum/searchuserbyid",{"uid":uid},function(data){
					
					
					$("#posts div:eq("+seq+") div:eq(1) a:eq(0)").html(data.username);
				
				},"json");
				
				$("#posts div:eq("+i+") span:eq(1)").html(new Date(post.begintime).toLocaleString());
			}
		}
	},"json"); */
	
	$("#publish").click(function(){
		/* alert($("#fid").val()); */
		if(($("#headfont1").css("display"))=="block"){
			alert("请您登陆后再发帖!");
		}
		
		else if(!$("#texttitle").val()){
			alert("请输入帖子标题...");
		}
		
		else if(!$("#textarea").val()){
			alert("请输入帖子内容...");
		}
		else{
			$.post("/forum/insertpost",{"uid":$("#fid").val(),"title":$("#texttitle").val(),"text":$("#textarea").val()},function(data){
				if(data=="true"){
					alert("发表成功！");
					$("#texttitle").val("");
					$("#textarea").val("");
				    searchPosts();
				}
				else{
					alert("发表失败！");
				}
			},"text");
		}
	});
	
	$("#pre").click(function(){
		if($("#curPage").html()=="1"){
			alert("已经是第一页了...");
		}
		else{
			$("#pageNum").val($("#pageNum").val()-1);
			searchPosts();
		}
	});
	
	$("#next").click(function(){
		
		if($("#curPage").html()==$("#totalPage").html()){
			alert("已经是最后一页了...");
		}
		else{
			$("#pageNum").val($("#pageNum").val()*1+1);
			searchPosts();
		}
	});
});



