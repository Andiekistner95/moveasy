$( document ).ready(function() {


	$("#divTmbEmpresas").show();
	$("#divTmbEntregadores").hide();

	$("#divTmbServicosDiversos").show();
	$("#divTmbServicosBancarios").hide();


	$( "#btnEntregadores" ).click(function() {
		$("#btnEmpresas").removeClass("active");
		$("#btnEntregadores").addClass("active");
		$("#divTmbEmpresas").hide();
		$("#divTmbEntregadores").show();
	});

	$( "#btnEmpresas" ).click(function() {
		$("#btnEntregadores").removeClass("active");
		$("#btnEmpresas").addClass("active");
		$("#divTmbEmpresas").show();
		$("#divTmbEntregadores").hide();
	});

	$( "#btnServicosDiversos" ).click(function() {
		$("#btnServicosAlimenticios").removeClass("active");
		$("#btnServicosBancarios").removeClass("active");
		$("#btnServicosDiversos").addClass("active");
		$("#divTmbServicosBancarios").hide();
		$("#divTmbServicosDiversos").show();
	});

	$( "#btnServicosBancarios" ).click(function() {
		$("#btnServicosAlimenticios").removeClass("active");
		$("#btnServicosDiversos").removeClass("active");
		$("#btnServicosBancarios").addClass("active");
		$("#divTmbServicosDiversos").hide();
		$("#divTmbServicosBancarios").show();
	});

	$( "#btnServicosAlimenticios" ).click(function() {
		$("#btnServicosBancarios").removeClass("active");
		$("#btnServicosDiversos").removeClass("active");
		$("#btnServicosAlimenticios").addClass("active");
		$("#divTmbServicosDiversos").hide();
		$("#divTmbServicosBancarios").show();
	});



});