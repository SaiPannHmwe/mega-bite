$(document).ready(function() {
	$('.select2').select2();

	const elementId = $('#shopId');
	const url = '/menu/menuMap';

	bindDataToSelect(elementId, url);

	$('#form').validate({
		rules: {
			shopId: {
				required: true
			}
		},
		messages: {
			shopId: {
				required: $('#shop_not_blank').val()
			}
		},
		errorPlacement: function(error, element) {
			if (element.attr("name") == "shopId") {
				error.appendTo("#shopError");
			} else if (element.attr("name") == "addressId") {
				error.appendTo("#addressError");
			} else {
				error.insertAfter(element);
			}
		},
		error: function(label) {
			$(this).addClass('error');
		}
	});

});

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
			} else if (elementId.get(0).id == 'addressId') {
				showShopAddresses(data);
			}
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
