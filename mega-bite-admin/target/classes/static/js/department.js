$(document).ready(function () {
    $('#transferTable').DataTable({

        "lengthMenu": [
            [10, 25, 50, 100, -1],
            [10, 25, 50, 100, "All"]
        ],
        aaSorting: [],
        "columnDefs": [
            {
                'targets': [0, 2, 3, 4],
                'searchable': false,
                'orderable': false
            }]
    });
});

$("#deactivateModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var name = triggerLink.data("name");
    var id = triggerLink.data("id");
    $(this).find(".modal-body").html("<p>Are you sure you want to deactivate <b>" + name + "</b> department?</p>");

    $(document).on('click', '#banAgent', function (e) {
        window.location = '/departments/' + id + '/ban';
    });
});
