$(document).ready(function () {

    $('#shopForm').validate({
        rules: {
            name: {
                required: true
            },
            address: {
                required: true
            },
            phoneNumber: {
                required: true
            }
        },
        messages: {
            name: {
                required: $('#shop_not_blank').val()
            },
            address: {
                required: $('#shop_not_blank').val()
            },
            phoneNumber: {
                required: $('#shop_not_blank').val()
            }
        },
        errorPlacement: function (error, element) {
            if (element.attr("name") == "name") {
                error.appendTo("#nameError");
            } else if (element.attr("name") == "address") {
                error.appendTo("#addressError");
            } else {
                error.insertAfter(element);
            }
        },
        error: function (label) {
            $(this).addClass('error');
        }
    });

}