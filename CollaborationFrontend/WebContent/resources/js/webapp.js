// setting the defaults 
jQuery.validator
		.setDefaults({
			errorElement : 'span',
			errorClass : 'help-block',
			highlight : function(element, errorClass) {
				$(element).closest('.form-group').removeClass('has-success')
						.addClass('has-error');
				if ($(element).getType() == "text"
						|| $(element).getType() == "password"
						|| $(element).getType() == "email") {
					var id_attr = "#" + element.id + "1";
					$(id_attr).removeClass('glyphicon-ok').addClass(
							'glyphicon-remove');
				}
			},
			unhighlight : function(element, errorClass) {
				if ($(element).closest('.form-group').hasClass('has-error')) {
					$(element).closest('.form-group').removeClass('has-error')
							.addClass('has-success');
					if ($(element).getType() == "text"
							|| $(element).getType() == "password"
							|| $(element).getType() == "email") {
						var id_attr = "#" + element.id + "1";
						$(id_attr).removeClass('glyphicon-remove').addClass(
								'glyphicon-ok');
					}
				}
			},
			onkeyup : false,
			submitHandler : function(form) {
				$('.validation-summary-errors').remove();
				form.submit();
			}
		});

$(function() {

	$.fn.getType = function() {
		return this[0].tagName == "INPUT" ? this[0].type.toLowerCase()
				: this[0].tagName.toLowerCase();
	}

	// ============================= jQuery Validation for Login Form
	// ===========
	$('#frmLogin').validate({
		rules : {
			username : {
				required : true,
				email : true
			},
			password : {
				required : true
			}
		},
		messages : {
			username : {
				required : 'Please enter your registered email id!',
				email : 'Please enter proper email id!'
			},
			password : {
				required : 'Please provide the password!'
			}
		}
	});
	// ============================= jQuery Validation for Login Form
	// ===========

	// ============================= jQuery Validation for Register Form
	// ===========
	$('#frmRegistration').validate({
		rules : {
			firstName : {
				required : true
			},
			lastName : {
				required : true
			},
			emailAddress : {
				required : true,
				email : true
			},
			contactNumber : {
				required : true,
				minlength : 10,
				digits : true
			},
			registerPassword : {
				required : true
			},
			confirmPassword : {
				equalTo : '#registerPassword'
			}
		},
		messages : {
			firstName : {
				required : 'Please enter first name!'
			},
			lastName : {
				required : 'Please enter last name!'
			},
			emailAddress : {
				required : 'Please enter email address!',
				email : 'Please enter a valid email address'
			},
			contactNumber : {
				required : 'Please enter contact number!',
				minlength : 'Please enter the last 10 digits only',
				digits : 'Please enter digits only'
			},
			registerPassword : {
				required : 'Please enter password!'
			},
			confirmPassword : {
				equalTo : 'Confirm password does not match entered password!'
			}
		}
	});
	// ============================= jQuery Validation for Register Form ===========

})
