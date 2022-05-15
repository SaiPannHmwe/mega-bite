$(document).ready(function () {

    getSideBarCount();

    if (document.location.pathname.indexOf("/dashboard") === 0 || window.location.pathname == "/") {
        $('.sidebar').find('[href="/dashboard"]').parent().addClass('submenu-active');
    }

    if (location.pathname.includes('shops')) {
        console.log(location.pathname);
        $('.sidebar').find('[href="/shops"]').parent().addClass('submenu-active');
    }

    if (location.pathname.includes('menu')) {
        console.log(location.pathname);
        $('.sidebar').find('[href="/menu"]').parent().addClass('submenu-active');
    }

    if (location.pathname.includes('orders')) {
        console.log(location.pathname);
        $('.sidebar').find('[href="/orders"]').parent().addClass('submenu-active');
    }

    if (location.pathname.includes('change-password')) {
        console.log(location.pathname);
        $('.sidebar').find('[href="/change-password"]').parent().addClass('submenu-active');
    }

});

function getSideBarCount() {
    const url = '/sidebar/sidebarCount';
    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {
            $("#paymentCount").text(data[0]);
            $("#transportationCount").text(data[1]);
        }
    });
    setTimeout(getSideBarCount, 30000);
}