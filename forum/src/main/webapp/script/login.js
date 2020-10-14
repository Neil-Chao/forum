/* function detectCapsLock(event) {
	var e = event || window.event;
	var o = e.target || e.srcElement;
	var oTip = o.nextSibling;
	var keyCode = e.keyCode || e.which; // 按键的keyCode 
	var isShift = e.shiftKey || (keyCode == 16) || false; // shift键是否按住
	if (
		((keyCode >= 65 && keyCode <= 90) && !isShift) // Caps Lock 打开，且没有按住shift键 
		|| ((keyCode >= 97 && keyCode <= 122) && isShift)// Caps Lock 打开，且按住shift键
	) {
	oTip.style.display = '';
	}
	else {
		oTip.style.display = 'none';
	}
} */

$(function(){
	$("#fpwd,#fname").keydown(function(){
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e&&e.keyCode==13){ 
			$("#loginbtn").click();
		}
	});
	$("#fpwd").keydown(function(){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e&&e.keyCode==20){ 
				if($("#mes").css("display")=="none"){
					$("#mes").css("display","inline"); 
				}
				else{
					$("#mes").css("display","none"); 
				}	
			}
	});
	
	$("#fpwd").blur(function(){
		$("#mes").css("display","none"); 
	});
});



$(function(){
	$("#loginbtn").click(function(){
		var fname=$("#fname").val();
		if(!fname){
			alert("用户名未填写");
			$("#fname").focus();
			return;
		}
		var fpwd=$("#fpwd").val();
		if(!fpwd){
			alert("用户名未填写");
			$("#fpwd").focus();
			return;
		}
		$.post("/forum/login",$("[name]").serialize(),function(data){
			if(data.uid){
				$.post("/forum/recordlogin",{"uid":data.uid},function(data){
				},"text");
				location.href="/forum/main.html";
			}else{
				alert("用户名或密码错误");
			}
		},"json");
	});
	
	$("#eyeicon").click(function(){
		/* alert($("#fpwd").attr("type")); */
		if($("#fpwd").attr("type")=="password"){
			$("#fpwd").attr("type","text");
		}
		else{
			$("#fpwd").attr("type","password");
		}
	});
	
	
	
});