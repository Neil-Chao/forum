
$(function(){
     $.post("/forum/getforgetuser",null,function(data){
        if(data){
            $("#fuid").val(data.uid);
        }
     },"json");
    
    
    $("#confirmbtn").click(function(){
        var fpwd = $("#fpwd").val();
        if(!fpwd){
            alert("请填写新密码");
            $("#fpwd").focus();
            return;
        }
        var userpwd = $("#userpwd").val();
        if(!userpwd){
            alert("请输入确认密码");
            $("#userpwd").focus();
            return;
        }
        if(fpwd!=userpwd){
            alert("两次密码填写不一样，请重新输入");
            $("#userpwd").focus();
            $("#userpwd").select();
            return;
        }
       
        
        $.post("/forum/moduserpwd",{"uid":$("#fuid").val(),"userpwd":$("#fpwd").val()},function(data){
           
            if(data == "true"){
                alert("修改成功,点击跳转至登录页面");
                location.href="/forum/login.html";
            }else{
                alert("与原密码相同，修改失败");
            }
        },"text");
    });
});