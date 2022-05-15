$(document).ready(function () {
    $('.popular-shop').hide();

    filter = $('#inputFilter').val();
    if (filter) {
        alert("Filter is here!");
    }else{
        $.ajax({
            type: "GET",
            url: "/popular-shop",
            contentType: "application/json",
            data: '',
            success: function (r) {

                if (r != null) $('.popular-shop').show();

                console.log("Shop Id :", r.id)
                $('#popular-shop').append(
                    '<div class="col-lg-3 col-md-6 col-sm-12 mb-3">'
                    + '<a href=/shops/' + r.id + '/menu class="thumb-menu">'
                    + '<img class="img-fluid img-cover" src="' + r.url + '"/>'
                    + '<h6>' + r.name + '</h6>'
                    + '<h6>' + r.phoneNumber + '</h6>'
                    + '<h6>' + r.address + '</h6>'
                    + '</a>'
                    + '</div>')
            },
            error: function (r) {
                console.log("Something went wrong!");
            }
        });
    }

    /*$('#hit').click(function () {
        let filter = $('#inputFilter').val();
        if (filter) {
            $('#inputFilter').val(filter);
            alert("filter not empty");
            $('.popular-shop').hide();
        } else {
            alert("filter empty");
            $.ajax({
                type: "GET",
                url: "/popular-shop",
                contentType: "application/json",
                data: '',
                success: function (r) {

                    if (r != null) $('.popular-shop').show();

                    console.log("Shop Id :", r.id)
                    $('#popular-shop').append(
                        '<div class="col-lg-3 col-md-6 col-sm-12 mb-3">'
                        + '<a href=/shops/' + r.id + '/menu class="thumb-menu">'
                        + '<img class="img-fluid img-cover" src="' + r.url + '"/>'
                        + '<h6>' + r.name + '</h6>'
                        + '<h6>' + r.phoneNumber + '</h6>'
                        + '<h6>' + r.address + '</h6>'
                        + '</a>'
                        + '</div>')
                },
                error: function (r) {
                    console.log("Something went wrong!");
                }
            });
        }
    });
*/

});