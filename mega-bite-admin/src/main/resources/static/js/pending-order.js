let table;
$(document).ready(function () {
    drawDataTable();
    $('[data-toggle="tooltip"]').tooltip();
});

$('#creditTable').on('draw.dt', function () {
    $('[data-toggle="tooltip"]').tooltip();
});

function drawDataTable() {
    $('table#creditTable').DataTable().clear().destroy();
    table = $('table#creditTable').DataTable({
        responsive: true,
        ajax: {
            'contentType': 'application/json',
            'url': '/upload-order/dataTable?status=' + 'Delivered',
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
                data: 'totalCharge'
            },
            {
                data: 'customerName'
            },
            {
                data: 'address'
            },
            {
                data: 'phoneNumber'
            },
            {
                data: 'id',
                render: function (data, type, row) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a href="/delivered-orders/' + data + '/detail"'
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
                'targets': [1, 3, 7],
                'searchable': false,
                'orderable': false
            },
            {
                'targets': [1, 2, 3, 4, 5, 6],
                className: "text-center"
            }
        ]
    });
}

function deleteSelectedOrders() {
    const type = 'pending';
    let selectedOrderIds = [];
    const rows_selected = table.column(0).checkboxes.selected();
    $.each(rows_selected, function (index, rowId) {
        selectedOrderIds.push(rowId);
    });

    if (selectedOrderIds.length == 0) {
        $('#showMessageModal').modal();
    } else {
        window.location = '/orders/deleteSelectedOrders?orderIds=' + selectedOrderIds + '&type=' + type;
    }
}
