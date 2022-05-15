$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    drawDataTable();
});

$('#shop-table').on('draw.dt', function () {
    $('[data-toggle="tooltip"]').tooltip();
});

function drawDataTable() {
    $('table#shop-table').DataTable().clear().destroy();
    $('table#shop-table').DataTable({
        responsive: true,
        ajax: {
            'contentType': 'application/json',
            'url': '/shops/dataTable',
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
                data: 'name'
            },
            {
                data: 'address'
            },
            {
                data: 'phoneNumber'
            },
            {
                "data": 'image',
                "render": function (data, type, row, meta) {
                    if (data != "" && data != null)
                        return '<img src="' + data + '" class="imgStyle" width="50" height="50"/>';
                    else return "";
                },
                width: "10%"
            },
            {
                data: 'id',
                render: function (data, type, full) {
                    if (full.resolved == false) {
                        return '<span class="d-flex justify-content-center">'
                            + '<i style="cursor: pointer" class="fas fa-eraser icon-color" aria-hidden="true" '
                            + 'data-toggle="modal" data-target="#activateModal" '
                            + 'onclick="callModal(' + full.id + ')"></i></span>';
                    } else {
                        return '<span class="d-flex justify-content-center">'
                            + '<i style="color: green;cursor: default" '
                            + 'class="fas fa-eraser" aria-hidden="true"></i></span>';
                    }
                }
            },
            {
                data: 'id',
                render: function (data, type, row) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a href="/shops/' + data + '/detail"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="View Detail"><i class="fas fa-eye icon-color"></i></a>'
                        + '</span>';
                }
            },
            {
                data: 'id',
                render: function (data, type, row) {
                    return '<span class="d-flex justify-content-center">'
                        + '<a href="/shops/' + data + '/edit"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="Edit Shop"><i class="fas fa-edit icon-color"></i></a>'
                        + '</span>';
                }
            },
            {
                data: 'id',
                render: function (data, type, full) {
                    return '<span class="del d-flex justify-content-center">'
                        + '<a data-toggle="modal" data-id="' + full.id + '"'
                        + '" data-target="#deleteConfirmModal"> ' +
                        '<i class="fa fa-trash icon-color "data-toggle="tooltip" ' +
                        'data-placement="top" title="Delete Shop"></i></a> </span>'
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
                'targets': [1, 5, 6, 7, 8, 9],
                'searchable': false,
                'orderable': false
            }
        ]
    });
}

$("#deleteConfirmModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var id = triggerLink.data("id");

    $(document).on('click', '#delRef', function (e) {
        window.location = '/shops/' + id + '/delete';
    });
});

function callModal(prop) {
    $('#shopId').val(prop);
}

$("#activateModal").on('show.bs.modal', function (e) {
    const id = $('#shopId').val();
    $(this).find(".modal-body").html("<p>Are you sure this shop is popular ?</p>");

    $(document).on('click', '#activateRef', function (e) {
        window.location = '/shops/' + id + '/makePopular';
    });
});