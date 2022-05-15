$(document).ready(function () {
    //bindSelectedDataToDataTable();
    $('[data-toggle="tooltip"]').tooltip();

    drawDataTable();
});

$('#version-table').on('draw.dt', function () {
    $('[data-toggle="tooltip"]').tooltip();
});

/*$('#type').on('change', function () {
    bindSelectedDataToDataTable();
});

$('#platform').on('change', function () {
    bindSelectedDataToDataTable();
});

function bindSelectedDataToDataTable() {
    const type = $('#type option:selected').val();
    const platform = $('#platform option:selected').val();
    drawDataTable(platform, type);
}*/

function drawDataTable() {
    $('table#version-table').DataTable().clear().destroy();
    $('table#version-table').DataTable({
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
                        + '<a href="/versions/' + data + '/edit"'
                        + 'data-toggle="tooltip" data-placement="top"'
                        + 'title="Edit Shop"><i class="fas fa-edit icon-color"></i></a>'
                        + '</span>';
                }
            },
            {
                data: 'id',
                render: function (data, type, full) {
                    return '<span class="del d-flex justify-content-center">' +
                        '<button class="btn text-primary p-0" id="btnRemove" name="btnRemove"title="Delete Article"'
                        + 'data-toggle="modal" data-id="' + full.id + '"'
                        + 'data-title="' + full.name + '" data-target="#deleteConfirmModal">' +
                        '<i class="fa fa-trash icon-color"></i></button></span>'
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
                'targets': [1, 6, 7],
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
        window.location = '/versions/' + id + '/delete';
    });
});
