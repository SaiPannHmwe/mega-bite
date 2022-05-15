var start = 0;
var working = false;
let filter;
$(document).ready(function () {

    filter = $('#inputFilter').val();

    //alert(filter);

    $('#hit').click(function () {
        if ($('#inputFilter').val()) {
            filter = $('#inputFilter').val();
            $('#inputFilter').val(filter);
        }
    });

    $(window).on('scroll', function () {
        if ($(window).scrollTop() >=
            $('.scroll').offset().top + $('.scroll').outerHeight() - window.innerHeight) {
            if (working == false) {
                working = true;
                $.ajax({
                    type: "GET",
                    url: "/menuData?start=" + start + "&query=" + filter,
                    contentType: "application/json",
                    data: '',
                    success: function (r) {
                        console.log(r.length);
                        console.log(r)

                        if (r.length > 0) {
                            appendContent(r);
                            setTimeout(function () {
                                working = false;
                                start += 1;
                            }, 1000)
                        }
                    },
                    error: function (r) {
                        console.log("Something went wrong!");
                    }
                });
            }
        }
    });
});

var appendContent = function (data) {
    for (var i = 0; i < data.length; i++) {
        if (data[i].url == null) {
            $('#content').append(
                '<div class="col-lg-3 col-md-6 col-sm-12 mb-3 content">'
                + '<a href=/shops/' + data[i].id + '/menu class="thumb-menu">'
                + '<img class="img-fluid" src="../../images/logo/logo.png"/>'
                + '<h6>' + data[i].name + '</h6>'
                + '<h6>' + data[i].phoneNumber + '</h6>'
                + '<h6>' + data[i].address + '</h6>'
                + '</a>'
                + '</div>').find('div.content').fadeIn('slow');
        } else {
            $('#content').append(
                '<div class="col-lg-3 col-md-6 col-sm-12 mb-3 content">'
                + '<a href=/shops/' + data[i].id + '/menu class="thumb-menu">'
                + '<img class="img-fluid img-cover" src="' + data[i].url + '"/>'
                + '<h6>' + data[i].name + '</h6>'
                + '<h6>' + data[i].phoneNumber + '</h6>'
                + '<h6>' + data[i].address + '</h6>'
                + '</a>'
                + '</div>').find('div.content').fadeIn('slow');
        }
    }
};