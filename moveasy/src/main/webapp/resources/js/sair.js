$( document ).ready(function() {
	
	$(".sair").click(function(){
		if(confirm('Você tem tem certeza que deseja sair?')) {
			window.location.href = "/moveasy";
        }

        return false;
	});
});