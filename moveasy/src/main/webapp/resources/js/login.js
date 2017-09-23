$( document ).ready(function() {
	
	// Funções 
	function escondeAlertas(){
		$("#alertaErro").hide();

	}
	
	function exibeAlertas(alerta){
		
		switch (alerta) {
			case "erro" :
				$("#alertaErro").show();
				break;		
		}
			
	}
	
	escondeAlertas();	
	
	$("#entrar").click(function() {

		var usuario = $("#usuario").val();
		var senha = $("#senha").val();
		
		if ( senha == "123") {
			
			switch (usuario){
				case "minhaempresa@email.com" :
					 window.location.href = "empresa/PainelEmpresa.html";
					 break;
				case "entregador@email.com" :
					 window.location.href = "entregador/PainelEntregador.html";
					 break;
				default:
					alert("Usuário não encontrado!");
				
			}
			
		} else {
			exibeAlertas("erro");
		}
		
	
	});


});
