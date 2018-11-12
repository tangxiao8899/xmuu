layui.define(['element'],function(exports){

    var $ = layui.$;
    $('.input-field').on('change',function(){
        var $this = $(this),
            value = $.trim($this.val()),
            $parent = $this.parent();

        if(value !== '' && !$parent.hasClass('field-focus')){
            $parent.addClass('field-focus');
        }else{
            $parent.removeClass('field-focus');
        }
    })
    exports('login');


});

        function login() {
        var $ = layui.$;
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "txLogin" ,
                data: {
                    "phone":$("#phone").val(),
                    "password":$("#password").val()
                },
                success: function (result) {
                    if (result.code == 200) {
                        location.href="index"
                    }else{
                        alert(result.msg);
                        }
                },
                error : function() {
                    alert("异常！");
                }
            });
        }