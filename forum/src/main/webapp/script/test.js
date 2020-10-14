//分页查询的功能实现
$(function(){
     $("#searchBtn").click(function(){
        //发ajax请求
        $.post("/platformarchplan",$("[name]").serialize(),function(data){
          if(data && data.size > 0){
               $("#total").html(data.pageSize);
               $("#pages").html(data.pages);
               $("#curpage").html(data.pageNum);
               //添加记录
               $("#resulttable tr:gt(0)").remove();
               //遍历加数据
               for(var i=0; i <data.list.length; i++){
                  var planshowinfo = data.list[i];
                  
                  //生成行
                  var oTr = $("<tr></tr>");
                  
                  $("<td></td>").html(i+1).appendTo(oTr);
                  $("<td></td>").html(planshowinfo.plan.planid).appendTo(oTr);
                  $("<td></td>").html(planshowinfo.plan.orderid).appendTo(oTr);
                  $("<td></td>").html(planshowinfo.prodname).appendTo(oTr);
                  $("<td></td>").html(planshowinfo.prodnum).appendTo(oTr);
                  $("<td></td>").html(new Date(planshowinfo.orderdl).toLocaleDateString() ).appendTo(oTr);
                  $("<td></td>").html(new Date(planshowinfo.plan.startplan ).toLocaleDateString() ).appendTo(oTr);
                  $("<td></td>").html(new Date(planshowinfo.plan.endplan  ).toLocaleDateString() ).appendTo(oTr);
                  $("<td></td>").html(planshowinfo.plan.planstatus).appendTo(oTr);
                  $("<td></td>").html(i+1).appendTo(oTr);
              
                  oTr.appendTo("#resulttable");
               }
          
           $("#resultdiv").show();
           $("#pagectrl").show();
        
         
         
         
         }
          
       
         
          
          else{
            $("#resultdiv").hide();
            $("#pagectrl").hide();
          alert("没有查到数据");
          } 
          
          
        },"json");
     });

});