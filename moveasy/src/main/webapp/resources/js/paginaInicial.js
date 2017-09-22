$( document ).ready(function() {


	$("#divTmbEmpresas").show();
	$("#divTmbEntregadores").hide();

	$("#divTmbServicosDiversos").show();
	$("#divTmbServicosBancarios").hide();


	$( "#btnEntregadores" ).click(function() {
		$("#divTmbEmpresas").hide();
		$("#divTmbEntregadores").show();
	});

	$( "#btnEmpresas" ).click(function() {
		$("#divTmbEmpresas").show();
		$("#divTmbEntregadores").hide();
	});

	$( "#btnServicosDiversos" ).click(function() {
		$("#divTmbServicosBancarios").hide();
		$("#divTmbServicosDiversos").show();
	});

	$( "#btnServicosBancarios" ).click(function() {
		$("#divTmbServicosDiversos").hide();
		$("#divTmbServicosBancarios").show();
	});



});