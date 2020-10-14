$(function(){
	$("#email,#fname,#fpwd,#conpwd").keydown(function(){
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e&&e.keyCode==13){ 
			$("#regbtn").click();
		}
	});
	
	$("#fpwd").keydown(function(){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e&&e.keyCode==20){ 
				if($("#mes1").css("display")=="none"){
					$("#mes1").css("display","inline"); 
				}
				else{
					$("#mes1").css("display","none"); 
				}	
			}
	});
	
	$("#conpwd").keydown(function(){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e&&e.keyCode==20){ 
				if($("#mes2").css("display")=="none"){
					$("#mes2").css("display","inline"); 
				}
				else{
					$("#mes2").css("display","none"); 
				}	
			}
	});
	
	$("#fpwd,#conpwd").blur(function(){
		$(".alert-warning").css("display","none"); 
	});
	
	$("#fname").blur(function(){
		$.post("/forum/checkname",{"username":$("#fname").val()},function(data){
			if(data=="true"){
				$("#usernameMessage").html("√").css("color","green");
				
			}
			else{
				$("#usernameMessage").html("用户名重复！").css("color","red");
			}
			
		},"text");
	});
	
	$("#email").blur(function(){
		var reg=new RegExp("([a-z]|[A-Z]|[0-9])+@([a-z]|[A-Z]|[0-9])+.com");
		var email=$("#email").val();
		if(reg.test(email)){
			$("#emailMessage").html("√").css("color","green");
		}
		else{
			$("#emailMessage").html("请填写正确的邮箱格式！").css("color","red");
		}
	});
});

$(function(){
	$("#regbtn").click(function(){
		var fname=$("#fname").val();
		if(!fname){
			alert("用户名未填写！");
			$("#fname").focus();
			return;
		}
		var fpwd=$("#fpwd").val();
		if(!fpwd){
			alert("密码未填写！");
			$("#fpwd").focus();
			return;
		}
		var conpwd=$("#conpwd").val();
		if(conpwd!=fpwd){
			alert("两次密码填写不一样！");
			$("#conpwd").focus();
			return;
		}
		var email=$("#email").val();
		if(!email){
			alert("邮箱未填写！");
			$("#email").focus();
			return;
		}
		var reg=new RegExp("([a-z]|[A-Z]|[0-9])+@([a-z]|[A-Z]|[0-9])+.com");
		var emailtest=$("#email").val();
		if(!reg.test(emailtest)){
			alert("邮箱格式错误！");
			$("#email").focus();
			return;
		}
		$.post("/forum/reg",$("[name]").serialize(),function(data){
			if(data=="false"){
				alert("用户名已被注册，请重新输入");
				$("#fname").focus();
				$("#fname").select();
			}
			else{
				alert("注册成功！点击跳转至登陆页面！");
				location.href="/forum/login.html";
			}
		},"text");
		
		
	});
});