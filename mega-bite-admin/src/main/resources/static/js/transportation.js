$(document).ready(function () {
    drawDataTable();
    $('[data-toggle="tooltip"]').tooltip();
});

$('#transportation-table').on('draw.dt', function () {
    $('[data-toggle="tooltip"]').tooltip();
});

function drawDataTable() {
    $('table#transportation-table').DataTable().clear().destroy();
    $('table#transportation-table').DataTable({
        responsive: true,
        ajax: {
            'contentType': 'application/json',
            'url': '/transportation/dataTable',
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
                },
                width: '5%'
            },
            {
                data: 'order',
                width: '7%'
            }/*,
            {
                data: 'customer',
                width: '7%'
            }*/,
            {
                data: 'staff',
                width: '7%'
            },
            {
                data: 'fromGate',
                width: '7%'
            },
            {
                data: 'toGate',
                width: '7%'
            },
            {
                data: 'transportationFee',
                width: '5%'
            },
            {
                "data": 'image.path',
                "render": function (data, type, row, meta) {
                    if (data != "" && data != null)
                        return '<span class="d-flex justify-content-center">'
                            + '<img src="' + data + '" class="imgStyle" width="50" height="50"/>'
                            + '</span>';
                    else return "";
                },
                width: "7%"
            },
            {
                data: 'isConfirmed',
                "render": function (data, type, full) {
                    if (full.isConfirmed == false) {
                        return '<span class="d-flex justify-content-center">'
                            + '<i style="color:grey;cursor: pointer" class="fa fa-check-circle" aria-hidden="true" data-toggle="modal" data-target="#transportationModal" onclick="callModal(' + full.id + ')"></i>'
                            + '</span>';
                    } else {
                        return '<span class="d-flex justify-content-center">'
                            + '<i style="color: green;cursor: pointer" class="fa fa-check-circle" aria-hidden="true"></i>'
                            + '</span>';
                    }
                },
                width: '5%'
            },
            {
                data: 'id',
                render: function (data, type, row) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a href="/transportation/' + data + '/detail"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="View Detail"><i class="fas fa-eye icon-color"></i></a>'
                        + '</span>';
                },
                width: '5%'
            }
        ],
        columnDefs: [
            {
                'targets': [0],
                'visible': false,
                'searchable': false
            },
            {
                'targets': [1, 2, 3, 4, 5, 6, 7, 8, 9],
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

function cashClearing() {
    const transportationId = $('#transportationId').val();
    url = '/transportation/cashClearing?transportationId=' + transportationId;
    $.ajax({
        type: 'PATCH',
        url: url,
        success: function (data) {
            drawDataTable();
        }
    });

}
