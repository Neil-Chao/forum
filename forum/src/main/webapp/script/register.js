$(function () {
    //按回车进行注册
    $("#email,#fname,#fpwd,#conpwd").keydown(function () {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
            $("#regbtn").click();
        }
    });
    //密码大小写锁定
    $("#fpwd").keydown(function () {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 20) {
            if ($("#mes1").css("display") == "none") {
                $("#mes1").css("display", "inline");
            } else {
                $("#mes1").css("display", "none");
            }
        }
    });
    //确认密码大小写锁定
    $("#conpwd").keydown(function () {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 20) {
            if ($("#mes2").css("display") == "none") {
                $("#mes2").css("display", "inline");
            } else {
                $("#mes2").css("display", "none");
            }
        }
    });

    $("#fpwd,#conpwd").blur(function () {
        $(".alert-warning").css("display", "none");
    });

    //电子邮箱实时提醒
    /*$("#email").blur(function(){
		$.post("/forum/checkemail",{"email":$("#email").val()},function(data){
            alert(data);
			if(data=="true"){
				$("#emailMessage").html("√").css("color","green");
				
			}
			else{
				$("#emailMessage").html("电子邮箱已被注册！").css("color","red");
			}
			
		},"text");
	});*/

    //电子邮箱格式和电子邮箱是否冲突的检查
    $("#email").blur(function () {
        var reg = new RegExp("([a-z]|[A-Z]|[0-9])+@([a-z]|[A-Z]|[0-9])+.com");
        var email = $("#email").val();
        if (!reg.test(email)) {
            /*$("#emailMessage").html("√").css("color","green");
		}
		else{*/
            $("#emailMessage").html("请填写正确的邮箱格式！").css("color", "red");
        } else {
            $.post("/forum/checkemail", {
                "email": $("#email").val()
            }, function (data) {
                if (data == "true") {
                    $("#emailMessage").html("√").css("color", "green");

                } else {
                    $("#emailMessage").html("电子邮箱已被注册！").css("color", "red");
                }

            }, "text");
        }
    });
});

$(function () {
    $("#regbtn").click(function () {
        var fname = $("#fname").val();
        if (!fname) {
            alert("用户名未填写！");
            $("#fname").focus();
            return;
        }
        var fpwd = $("#fpwd").val();
        if (!fpwd) {
            alert("密码未填写！");
            $("#fpwd").focus();
            return;
        }
        var conpwd = $("#conpwd").val();
        if (conpwd != fpwd) {
            alert("两次密码填写不一样！");
            $("#conpwd").focus();
            return;
        }
        var email = $("#email").val();
        if (!email) {
            alert("邮箱未填写！");
            $("#email").focus();
            return;
        }
        var reg = new RegExp("([a-z]|[A-Z]|[0-9])+@([a-z]|[A-Z]|[0-9])+.com");
        var emailtest = $("#email").val();
        if (!reg.test(emailtest)) {
            alert("邮箱格式错误！");
            $("#email").focus();
            return;
        }
        $.post("/forum/reg", $("[name]").serialize(), function (data) {
            if (data == "false") {
                alert("电子邮箱已被注册，请重新输入");
                $("#email").focus();
                $("#email").select();
            } else {
                alert("注册成功！点击跳转至登陆页面！");
                location.href = "/forum/login.html";
            }
        }, "text");


    });
});
