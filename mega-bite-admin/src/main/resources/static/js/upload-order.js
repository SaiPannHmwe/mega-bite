let table;
$(document).ready(function () {
    drawDataTable();
    $('[data-toggle="tooltip"]').tooltip();
});

$('#upload-order-table').on('draw.dt', function () {
    $('[data-toggle="tooltip"]').tooltip();
});

function drawDataTable() {
    $('table#upload-order-table').DataTable().clear().destroy();
    table = $('table#upload-order-table').DataTable({
        responsive: true,
        ajax: {
            'contentType': 'application/json',
            'url': '/upload-order/dataTable?status=' + 'pending',
            'type': 'POST',
            'data': function (d) {
                return JSON.stringify(d);
            }
        },
        lengthMenu: [
            [10, 25, 50, 100, 500, -1],
            [10, 25, 50, 100, 500, "All"]
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
                data: 'id',
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
                        + '<a href="/orders/' + data + '/detail"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="View Detail"><i class="fas fa-eye icon-color"></i></a>'
                        + '</span>';
                }
            },
            {
                data: 'id',
                render: function (data) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a href="/orders/' + data + '/edit"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="Edit Order"><i class="fas fa-edit icon-color"></i></a>'
                        + '</span>';
                }
            },
            {
                data: 'id',
                render: function (data, type, full) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a data-toggle="modal" data-id="' + full.id + '"'
                        + '" data-target="#deactivateModal"> ' +
                        '<i class="fa fa-trash icon-color "data-toggle="tooltip" ' +
                        'data-placement="top" title="Delete Order"></i></a> </span>'
                }
            }
        ],
        columnDefs: [
            {
                'targets': [0],
                'checkboxes': {
                    'selectRow': true
                }
            },
            {
                'targets': [3, 7, 8, 9],
                'searchable': false,
                'orderable': false
            },
            {
                'targets': [1, 2, 3, 4, 5, 6],
                className: "text-center"
            }
        ],
        select: {
            'style': 'multi'
        }
    });
}

$('#deactivateModal').on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var id = triggerLink.data("id");
    $(this).find(".modal-body").html("<p>Are you sure you want to delete?</p>");

    $(document).on('click', '#delOrder', function (e) {
        window.location = '/orders/' + id + '/delete';
    });
});

function deleteSelectedOrders() {
    const type = 'upload';
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

function downloadExcelSelectedOrders() {
    const type = 'upload';
    let selectedOrderIds = [];
    const rows_selected = table.column(0).checkboxes.selected();
    $.each(rows_selected, function (index, rowId) {
        selectedOrderIds.push(rowId);
    });

    if (selectedOrderIds.length == 0) {
        $('#showMessageModal').modal();
    } else {
        window.location = '/orders/downloadExcelSelectedOrders?orderIds=' + selectedOrderIds + '&type=' + type;
    }
}

