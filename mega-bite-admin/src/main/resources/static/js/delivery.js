$(document).ready(function () {
    $('#transferTable').DataTable({

        "lengthMenu": [
            [10, 25, 50, 100, -1],
            [10, 25, 50, 100, "All"]
        ],
        aaSorting: [],
        "columnDefs": [
            {
                'targets': [0, 7, 8, 9],
                'searchable': false,
                'orderable': false
            }]
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

$("#activateModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var name = triggerLink.data("name");
    var id = triggerLink.data("id");
    $(this).find(".modal-body").html("<p>Are you sure you want to activate <b>" + name + "</b>?</p>");

    $(document).on('click', '#activateRef', function (e) {
        window.location = '/delivery-men/' + id + '/activate';
    });
});