$(function () {
    $("#current_password_toggle a").on('click', function (event) {
        event.preventDefault();
        var $pwd = $("#current_password_toggle input");
        var $icon = $('#current_password_toggle svg');
        if ($pwd.attr("type") === "text") {
            $pwd.attr('type', 'password');
            $icon.addClass("fa-eye-slash");
            $icon.removeClass("fa-eye");
        } else if ($pwd.attr("type") === "password") {
            $pwd.attr('type', 'text');
            $icon.removeClass("fa-eye-slash");
            $icon.addClass("fa-eye");
        }
    });

    $("#new_password_toggle a").on('click', function (event) {
        event.preventDefault();
        var $pwd = $("#new_password_toggle input");
        var $icon = $('#new_password_toggle svg');
        if ($pwd.attr("type") === "text") {
            $pwd.attr('type', 'password');
            $icon.addClass("fa-eye-slash");
            $icon.removeClass("fa-eye");
        } else if ($pwd.attr("type") === "password") {
            $pwd.attr('type', 'text');
            $icon.removeClass("fa-eye-slash");
            $icon.addClass("fa-eye");
        }
    });

    $("#confirm_password_toggle a").on('click', function (event) {
        event.preventDefault();
        var $pwd = $("#confirm_password_toggle input");
        var $icon = $('#confirm_password_toggle svg');
        if ($pwd.attr("type") === "text") {
            $pwd.attr('type', 'password');
            $icon.addClass("fa-eye-slash");
            $icon.removeClass("fa-eye");
        } else if ($pwd.attr("type") === "password") {
            $pwd.attr('type', 'text');
            $icon.removeClass("fa-eye-slash");
            $icon.addClass("fa-eye");
        }
    });
});