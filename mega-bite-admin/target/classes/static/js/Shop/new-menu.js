$(document).ready(function () {
    $('.select2').select2();

    alert("Ok");

    const elementId = $('#shopId');
    const url = '/menu/menuMap';
    /*menuId.prepend('<option selected=""></option>');
    menuId.select2({
        placeholder: 'အားလုံး'
    });*/
    bindDataToSelect(elementId, url);

}

/*function bindDataToSelect(elementId, url) {
    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {
            elementId.empty();
            elementId.prepend('<option selected=""></option>');

            $.each(data, function (index) {
                elementId.append($('<option>', {
                    value: (data[index]).id,
                    text: (data[index]).name
                }));
            });
        },
        complete: function (data) {
            /!*if (elementId.get(0).id == 'divisionId' && selectedDivisionId != '') {
                elementId.val(selectedDivisionId).trigger('change');
            }
            if (elementId.get(0).id == 'townshipId' && selectedTownshipId != '') {
                elementId.val(selectedTownshipId).trigger('change');
            }
            if (elementId.get(0).id == 'wardId' && selectedWardId != '') {
                $('#wardId option:contains("' + selectedWardId + '")').attr('selected', 'selected');
            }
            if (elementId.get(0).id == 'modalPaymentChannelId' && selectedPaymentChannelId != '') {
                elementId.val(selectedPaymentChannelId).trigger('change');
            }

            if (elementId.get(0).id == 'collectorId' && selectedCollectorId != '') {
                elementId.val(selectedCollectorId).trigger('change');
            }*!/
        }
    });
}*/

/*function bindMenuDataToSelect(elementId, url) {
    elementId.select2().empty();
    elementId.select2({
        ajax: {
            url: url,
            dataType: 'json',
            delay: 250,
            data: function (params) {
                let query = params.term === undefined ? '' : params.term;
                return {
                    q: query,
                    page: params.page || 0,
                    size: 10
                }
            },
            processResults: function (data, params) {
                params.page = params.page || 0;
                if (data.first) {
                    const obj = {
                        id: " ",
                        name: 'အားလုံး'
                    };
                    data.content.splice(0, 0, obj);
                }
                return {
                    results: data.content,
                    pagination: {
                        more: (params.page * 10) < data.totalElements
                    }
                };
            },
            cache: true
        },
        placeholder: "အားလုံး",
        //minimumInputLength : 0,
        templateResult: formatTemplateResult,
        templateSelection: dataTemplateSelection,
        escapeMarkup: function (markup) {
            return markup;
        }
    });

    /!*const customerId = getCookie(ORDERS_ALL_SELECTED_CUSTOMER_ID) ? getCookie(ORDERS_ALL_SELECTED_CUSTOMER_ID): null;
    const customerName = getCookie(ORDERS_ALL_SELECTED_CUSTOMER_NAME) ? getCookie(ORDERS_ALL_SELECTED_CUSTOMER_NAME): null;
    if (customerId) {
        var newOption = new Option(customerName, customerId, false, false);
        elementId.append(newOption).trigger('change');
    }*!/
}

function formatTemplateResult(data) {
    if (data.loading) {
        return data.name;
    }
    let markup = '<div>' + data.name + '</div>';
    return markup;
}

function dataTemplateSelection(data) {
    return data.name || data.text;
}*/



function bindDataToSelect(elementId, url) {
    $.ajax({
            type: 'GET',
            url: url,
            success: function(data) {
                elementId.empty();
                if (elementId.get(0).id == 'shopId') {
                    elementId.prepend('<option selected=""></option>');

                    for (const [key, value] of Object.entries(data)) {
                        elementId.append($('<option>', {
                            value: key,
                            text: value
                        }));
                    }
                } /*else if (elementId.get(0).id == 'addressId') {
                    showShopAddresses(data);
                }*/
            },
            complete: function(data) {
                if (elementId.get(0).id == 'shopId') {
                    const shopId = $('#selectedShopId').val();
                    if (shopId != '') elementId.val(shopId).trigger('change');
                }
            }
        }
    );
}
