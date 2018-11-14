 var $ = layui.$;
    var element = layui.element;
    var table = layui.table;
    var form = layui.form;
   var util = layui.util;

    table.render({
    elem: '#demo'
    ,url:'cashData'
    ,id:'phone'
     ,page: true
     ,limits: [5,10,20,50,100]
	  ,limit: 5
    ,cols: [
        [
      {field:'nickname ', title:'会员', width: 200,templet: function(d){
        return  '<img  style="width:50px;height:50px;" src="'+d.avatar+'" alt=""/>'+'<span style="margin-left: 10px">'+ d.nickname +'</span>'
      }}
      ,{field:'money', title:'应到账/实到账/手续费', minWidth:100,templet:function(d){
        var m =  d.money.toFixed(2);
        return  '<p class="m">提现金额：￥'+ m +'</p>'+ '<p class="m">实到金额：￥'+ (m*9/10).toFixed(3) +'</p>'+ '<p class="m">手续费：￥'+ (m/10).toFixed(3) +'</p>'
      }}
      ,{field:'mobile', title:'提现手机号', width:150, sort: true}
      ,{field:'createtime', title:'提现时间', width:200,sort: true, templet: "<div>{{layui.util.toDateString(d.createtime*1000, 'yyyy-MM-dd HH:mm:ss')}}</div>"}
      ,{field:'status', title:'状态', width:100, sort: true,templet:function(d){
        return getstatus(d.status);
       }}
      ,{fixed: 'right', title:'操作', width:150,templet:function(d){
        return showBtn(d.status);
       }}
        ]
    ]
  });

function showBtn(s){
    if(s == 0){
        return  '<a class="layui-btn layui-btn-xs" lay-event="tg">通过</a>'+
        '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="jj">拒绝</a>'
    }else{
        return '';
    }
}

function getstatus(s){
    if(s==0){
         return "申请中";
    }else if(s ==1){
        return "通过";

    }else if(s ==-1){
        return "拒绝";
    }
    }

    var $ = layui.$, active = {
    reload: function(){
      var demoReload  = $('#mobile');

      //执行重载
      table.reload('phone', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
            phone: demoReload.val()
        }
      });
    }
  };
   $('#searchBtn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });

    //监听行工具事件
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    //console.log(obj)
    if(obj.event === 'jj'){
      layer.confirm('确定拒绝么', function(index){
                $.ajax({
                type: "POST",
                dataType: "json",
                url: "updateStatus" ,
                data: {
                    "id":data.id,
                    "status":-1,
                    "money":data.money,
                     "uid":data.uid
                },
                success: function (result) {
                    if (result.code == 200) {
                        obj.update({
                              status: '拒绝'
                            });
                            layer.close(index);
                    }else{
                        alert(result.msg);
                        }
                },
                error : function() {
                    alert("请求异常！");
                }
            });

      });
    } else if(obj.event === 'tg'){
          layer.confirm('确定通过么', function(index){
                            $.ajax({
                type: "POST",
                dataType: "json",
                url: "updateStatus" ,
                data: {
                    "id":data.id,
                    "status":1
                },
                success: function (result) {
                    if (result.code == 200) {
                        obj.update({
                              status: '通过'
                            });
                            layer.close(index);
                    }else{
                        layer.alert(result.msg);
                        }
                },
                error : function() {
                    layer.alert("请求异常！");
                }
            });
      });
    }
  });