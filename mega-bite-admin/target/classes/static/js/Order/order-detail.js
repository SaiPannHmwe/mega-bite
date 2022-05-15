$(document).ready(function () {

    const orderId = $('#orderId').val();
    drawDataTable(orderId);
    $('[data-toggle="tooltip"]').tooltip();
});

$('#order-detail-table').on('draw.dt', function () {
    $('[data-toggle="tooltip"]').tooltip();
});

function drawDataTable(orderId) {
    $('table#order-detail-table').DataTable().clear().destroy();
    $('table#order-detail-table').DataTable({
        responsive: true,
        ajax: {
            'contentType': 'application/json',
            'url': '/orderDetail/dataTable?orderId=' + orderId,
            'type': 'POST',
            'data': function (d) {
                return JSON.stringify(d);
            }
        },
        lengthMenu: [
            [10, 25, 50, 100, -1],
            [10, 25, 50, 100, "All"]
        ],
        deferRender: true,
        serverSide: true,
        processing: true,
        aaSorting: [],
        sDom: 'lrtip',
        columns: [
            {
                data: 'id'
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {
                data: 'shopName'
            },
            {
                data: 'phoneNumber'
            },
            {
                data: 'name'
            },
            {
                data: 'totalCount'
            },
            {
                data: 'totalAmount'
            },
            /*{
                data: 'id',
                render: function (data, type, row) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a href="/advertisements/' + data + '/detail"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="View Detail"><i class="fas fa-eye icon-color"></i></a>'
                        + '</span>';
                }
            },
            {
                data: 'id',
                render: function (data) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a href="/advertisements/' + data + '/edit"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="Edit Advertisement"><i class="fas fa-edit icon-color"></i></a>'
                        + '</span>';
                }
            },*/
            {
                data: 'id',
                render: function (data, type, full) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a data-toggle="modal" data-id="' + full.id + '"'
                        + '" data-target="#deleteConfirmModal"> ' +
                        '<i class="fa fa-trash icon-color "data-toggle="tooltip" ' +
                        'data-placement="top" title="Delete Order"></i></a> </span>'
                }
            }
        ],
        columnDefs: [
            {
                'targets': [0],
                'visible': false,
                'searchable': false
            },
            {
                'targets': [1, 2, 3, 4, 5, 6, 7],
                'searchable': false,
                'orderable': false
            }
        ]
    });
}

$("#deleteConfirmModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var id = triggerLink.data("id");
    $(this).find(".modal-body").html("<p>Are you sure you want to delete?</p>");

    $(document).on('click', '#delRef', function (e) {
        window.location = '/advertisements/' + id + '/delete';
    });
});