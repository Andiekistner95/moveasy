$( document ).ready(function() {

	// Funções 
	function escondeAlertas(){
		$("#alertaSucesso").hide();
		$("#alertaErro").hide();
		$("#novoPedido").hide();
	}
	
	function exibeAlertas(alerta){
		
		switch (alerta) {
			case "erro" :
				$("#alertaErro").show();
				break;
			case "sucesso" :
				$("#alertaSucesso").show();
				break;			
		}
			
	}
	
	escondeAlertas();	
	
	$("#confirmarPedido").click(function() {
		
		escondeAlertas();
		
		var descricaoPedido = $("#descricaoPedido").val();
		var tipoServico = $("#slTipo").val();
		var rua = $("#rua").val();
		var numero = $("#numero").val();
		var complemento = $("#complemento").val();
		var bairro = $("#bairro").val();
		var cidade = $("#slCidade").val();
		var estado = $("#slEstado").val();
		
		if ( 
				( !descricaoPedido.trim() ) ||
				( !tipoServico.trim() ) ||
				( !rua.trim() ) ||
				( !numero.trim() ) ||
				( !complemento.trim() ) ||
				( !bairro.trim() ) ||
				( !cidade.trim() ) ||
				( !estado.trim() ) 
								
			){
			
			alert("Exitem campos que não foram preenchidos, por favor, verifique os dados.");
		} else if (bairro == "erro") {
			
			exibeAlertas("erro");

		}		else {
					
			$("#formIncluirPedido").hide();			
			exibeAlertas("sucesso");
			$("#novoPedido").show();
		}
		
	});
	
	$("#confirmarNovoPedido").click(function() {
		 window.location.href = "InclusaoPedido.html";
		
	});
	
	$("#cancelarNovoPedido").click(function() {
		 window.location.href = "PainelEmpresa.html";
		
	});
	

});
