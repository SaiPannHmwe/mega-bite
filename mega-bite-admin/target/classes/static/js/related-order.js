$(document).ready(function () {
    $('#related-order-table').DataTable({

        "lengthMenu": [
            [10, 25, 50, 100, -1],
            [10, 25, 50, 100, "All"]
        ],
        aaSorting: [],
        "columnDefs": [
            {
                'targets': [0, 2],
                'searchable': false,
                'orderable': false
            }]
    });
});