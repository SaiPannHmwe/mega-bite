$(document).ready(function() {
	$('.select2').select2();

	const elementId = $('#menuId');
	const url = '/menu/menuMap';

	bindDataToSelect(elementId, url);

	$('#form').validate({
		rules: {
			menuId: {
				required: true
			}
		},
		messages: {
			menuId: {
				required: $('#shop_not_blank').val()
			}
		},
		errorPlacement: function(error, element) {
			if (element.attr("name") == "menuId") {
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
			if (elementId.get(0).id == 'menuId') {
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
			if (elementId.get(0).id == 'menuId') {
				const menuId = $('#selectedMenuId').val();
				if (menuId != '') elementId.val(menuId).trigger('change');
			}
		}
	}
	);
}
