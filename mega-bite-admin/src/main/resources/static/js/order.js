$(document).ready(function () {
    $('#transferTable').DataTable({

        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        aaSorting: [],
        "columnDefs": [
            {
                'targets': [0, 6, 7, 8],
                'searchable': false,
                'orderable': false
            }]
    });
});

$("#deactivateModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var name = triggerLink.data("name");
    var id = triggerLink.data("id");
    $(this).find(".modal-body").html("<p>Are you sure you want to delete order <b>" + name + "</b> ?</p>");

    $(document).on('click', '#banAgent', function (e) {
        window.location = '/orders/' + id + '/delete';
    });
});
