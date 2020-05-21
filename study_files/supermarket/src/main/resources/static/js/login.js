// JavaScript Document
//支持Enter键登录
document.onkeydown = function (e) {
    if ($(".bac").length === 0) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) === 13) {
            var obtnLogin = document.getElementById("submit_btn");
            obtnLogin.click();
            // var registBtn = document.getElementById("regist_btn");
            // registBtn.click();
        }
    }
};

$(function () {
    // 提交表单
    $('#submit_btn').click(function () {
        var userName = $('#userName').val();
        var userPassword = hex_md5($('#userPassword').val());
        show_loading();
        if (userName === '') {
            show_err_msg('请填写用户名');
            $('#userName').focus();
        } else if (userPassword === '') {
            show_err_msg('请填写密码');
            $('#userPassword').focus();
        } else {
            // 验证用户
            var url = "/user/login";
            var obj = {
                userName: userName,
                userPassword: userPassword
            };
            $.ajax({
                url: url,
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(obj),
                success: function (rst) {
                    if (200 === rst.code) {
                        //ajax提交表单，#login_form为表单的ID。 如：$('#login_form').ajaxSubmit(function(data) { ... });
                        show_msg('登录成功！  正在为您跳转...', '/frame.html');
                    } else {
                        show_err_msg(rst.data);
                    }
                },
                error: function () {
                    show_err_msg("登录失败");
                }
            });
        }
    });

    // 注册
    $('#regist_btn').click(function () {
        var userName = $('#userName').val();
        var userPassword = hex_md5($('#userPassword').val());
        var userWeiChat = $('#userWeiChat').val();
        show_loading();
        if (userName === '') {
            show_err_msg('请填写用户名');
            $('#userName').focus();
        } else if (userPassword === '') {
            show_err_msg('请填写密码');
            $('#userPassword').focus();
        } else if (userWeiChat === '') {
            show_err_msg('请填写微信号');
            $('#userWeiChat').focus();
        } else {
            // 注册用户
            var url = "/user/register";
            var obj = {
                userName: userName,
                userPassword: userPassword,
                userWeiChat: userWeiChat
            };
            $.ajax({
                url: url,
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(obj),
                success: function (rst) {
                    if (200 === rst.code) {
                        //ajax提交表单，#login_form为表单的ID。 如：$('#login_form').ajaxSubmit(function(data) { ... });
                        show_msg('注册成功！  正在为您跳转...', '/frame.html');
                    } else {
                        show_err_msg(rst.data);
                    }
                },
                error: function () {
                    show_err_msg("注册失败");
                }
            });
        }
    });

    // 注册跳转
    $('#register_btn').click(function () {
        window.location.href = '/register.html'
    });

    // 返回跳转
    $('#reset_btn').click(function () {
        window.location.href = '/login.html'
    })


});