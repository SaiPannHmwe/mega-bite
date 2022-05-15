$("#resetPasswordModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var name = triggerLink.data("name");
    var id = triggerLink.data("id");
    $(this).find(".modal-body").html("<p>Are you sure you want to reset password <b> " + name + "</b>?</p>");

    $(document).on('click', '#resetPasswordAgent', function (e) {
        window.location = '/delivery-men/' + id + '/reset';
    });
});


$("#deactivateModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var name = triggerLink.data("name");
    var id = triggerLink.data("id");
    $(this).find(".modal-body").html("<p>Are you sure you want to deactivate <b>" + name + "</b>?</p>");

    $(document).on('click', '#banAgent', function (e) {
        window.location = '/delivery-men/' + id + '/ban';
    });
});