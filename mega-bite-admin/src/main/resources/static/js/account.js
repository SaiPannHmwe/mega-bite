$(document).ready(function () {

    $('.input-group #entryDate').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    });

    $('#datePicker1').click(function () {
        $('#entryDate').focus();
    });

});

/*$(window).on('load', function () {
    $('#successModal').modal('show');
    setTimeout(function () {
        $('#successModal').dialog("close");
    }, 5000);
});*/

/*function showPage() {
    document.getElementById("successModal").style.display = "none";
    //document.getElementById("myDiv").style.display = "block";
}*/

/*$("#successModal").on('show.bs.modal', function (e) {
    var triggerLink = $(e.relatedTarget);
    var name = triggerLink.data("name");
    $(this).find(".modal-body").html("<p>" + name + "</p>");

    /!*$(document).on('click', '#activateRef', function (e) {
        window.location = '/office-staffs/' + id + '/activate';
    });*!/
});*/

/*$(document).ready(function () {

    $('.input-group #entryDate').datepicker({
        // dateFormat: 'dd-mm-yy',
        format: 'DD/MM/YYYY HH:mm:ss',
        minDate: getFormattedDate(new Date())
    });

    function getFormattedDate(date) {
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear().toString().slice(2);
        return year + '-' + month + '-' + day;
    }

    $('#datePicker1').click(function () {
        $('#entryDate').focus();
    });
}*/

