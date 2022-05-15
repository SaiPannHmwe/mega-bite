$(document).ready(function () {
    drawDataTable();
    $('[data-toggle="tooltip"]').tooltip();
});

$('#payment-table').on('draw.dt', function () {
    $('[data-toggle="tooltip"]').tooltip();
});

function drawDataTable() {
    $('table#payment-table').DataTable().clear().destroy();
    $('table#payment-table').DataTable({
        responsive: true,
        ajax: {
            'contentType': 'application/json',
            'url': '/payment/dataTable?type=' + 'pending',
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
                data: 'orderNumber'
            },
            {
                data: 'amount'
            },
            {
                data: 'payableReceivableAmount'
            },
            {
                data: 'paymentChannel'
            },
            {
                data: 'date'
            },
            {
                data: 'isApproved',
                "render": function (data, type, full) {
                    if (full.isApproved == false) {
                        return '<span class="d-flex justify-content-center">'
                            + '<i style="color:grey;cursor: pointer" class="fa fa-check-circle" aria-hidden="true" ></i>'
                            + '</span>';
                    } else {
                        return '<span class="d-flex justify-content-center">'
                            + '<i style="color: green;cursor: pointer" class="fa fa-check-circle" aria-hidden="true"></i>'
                            + '</span>';
                    }
                }
            },
            {
                data: 'id',
                render: function (data, type, row) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a href="/pending-payment/' + data + '/detail"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="View Detail"><i class="fas fa-eye icon-color"></i></a>'
                        + '</span>';
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
                'targets': [1, 2, 3, 4, 5, 6, 7, 8],
                'searchable': false,
                'orderable': false
            },
            {
                'targets': [1, 2, 3, 4, 5, 6, 7, 8],
                className: "text-center"
            }
        ]
    });
}


