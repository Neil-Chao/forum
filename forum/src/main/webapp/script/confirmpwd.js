//实时提醒
$(function(){
    $("#fname").blur(function(){
        $.post("/forum/checkname",{"username":$("#fname").val()},function(data){
            var username = $("#fname").val();
            if(!username){
                return;
            }
            if(data == "true"){
                //在数据库中没有找到匹配的用户名
                $("#usernameMessage").show().html("用户名不存在").css("color","red");
            }else{
                //在数据库中找到了匹配的用户名，说明用户存在
                $("#usernameMessage").show().html("√").css("color","green");
            }
        },"text");
    });
});
//检查用户信息是否填写,给确认按钮绑定点击事件
$(function(){
    $("#confirmbtn").click(function(){
        var fname = $("#fname").val();
        if(!fname){
            alert("用户名未填写");
            $("#fname").focus();
            return;
        }
        var fpwd = $("#email").val();
        if(!fpwd){
            alert("电子邮箱未填写");
            $("#email").focus();
            return;
        }
        
        
        $.post("/forum/forget",$("[name]").serialize(),function(data){
            if(data.uid){
                location.href="/forum/changepwd.html";
            }else{
                alert("信息错误！");
            }
        },"json");
    });
});