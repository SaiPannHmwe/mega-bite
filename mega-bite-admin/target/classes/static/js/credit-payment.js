let table;
$(document).ready(function () {
    drawDataTable();
    $('[data-toggle="tooltip"]').tooltip();
});

$('#credit-payment-table').on('draw.dt', function () {
    $('[data-toggle="tooltip"]').tooltip();
});

function drawDataTable() {
    $('table#credit-payment-table').DataTable().clear().destroy();
    table = $('table#credit-payment-table').DataTable({
        responsive: true,
        ajax: {
            'contentType': 'application/json',
            'url': '/payment/dataTable?type=' + 'credit',
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
                            + '<i style="color:grey;cursor: pointer" class="fa fa-check-circle" aria-hidden="true" data-toggle="modal" data-target="#transportationModal" onclick="callModal(' + full.id + ')"></i>'
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
                        + '<a href="/credit-payments/' + data + '/detail"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="View Detail"><i class="fas fa-eye icon-color"></i></a>'
                        + '</span>';
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
                'targets': [1, 2, 3, 4, 5, 6, 7, 8],
                'searchable': false,
                'orderable': false
            },
            {
                'targets': [1, 2, 3, 4, 5, 6, 7, 8],
                className: "text-center"
            }
        ],
        select: {
            'style': 'multi'
        }
    });
}

$("#deactivateModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var name = triggerLink.data("name");
    var id = triggerLink.data("id");
    $(this).find(".modal-body").html("<p>Are you sure you want to deactivate <b>" + name + "</b> ?</p>");

    $(document).on('click', '#banAgent', function (e) {
        window.location = '/payment-channels/' + id + '/ban';
    });
});

function callModal(prop) {
    $('#transportationId').val(prop);
}

function makePaymentCompleted() {
    let selectedPaymentIds = [];
    const rows_selected = table.column(0).checkboxes.selected();
    $.each(rows_selected, function (index, rowId) {
        selectedPaymentIds.push(rowId);
    });

    if (selectedPaymentIds.length == 0) {
        $('#showMessageModal').modal();
    } else {
        url = '/payments/makePaymentCompleted?paymentIds=' + selectedPaymentIds;
        $.ajax({
            type: 'PATCH',
            url: url,
            success: function (data) {
                drawDataTable();
            }
        });
    }

}
