$( document ).ready(function() {

	$("#divDashboard").show();
	$("#divFaturas").hide();
	$("#divAvaliacaoPendente").hide();

	$( "#dashboard" ).click(function() {
		$("#divDashboard").show();
		$("#pedidoAssumido").show();
		$("#divFaturas").hide();
		$("#divAvaliacaoPendente").hide();
	});

	$( "#faturas" ).click(function() {
		$("#divDashboard").hide();
		$("#pedidoAssumido").hide();
		$("#divFaturas").show();
		$("#divAvaliacaoPendente").hide();
	});

	$( "#avaliacaoPendente" ).click(function() {
		$("#divDashboard").hide();
		$("#pedidoAssumido").hide();
		$("#divFaturas").hide();
		$("#divAvaliacaoPendente").show();
	});

	// Pedidos Abertos x Fechados
	Highcharts.chart('chartPedidosAbertosxFechados', {
	    chart: {
	        type: 'pie',
	        options3d: {
	            enabled: true,
	            alpha: 45
	        }
	    },
	    title: {
	        text: 'Resumo de avaliação - 09-2017'
	    },
	    subtitle: {
	        text: 'Pontuação dos pedidos atendidos'
	    },
	    plotOptions: {
	        pie: {
	            innerSize: 100,
	            depth: 45
	        }
	    },
	    series: [{
	        name: 'Quantidade',
	        data: [
	            ['4 estrelas', 37],
	            ['3 estrelas', 2],
	            ['5 estrelas', 42],
	            ['2 estrelas', 3]
	        ]
	    }]
	});

	// Histórico de pedido por mês
	Highcharts.chart('chartHistoricoPedidosMes', {

	    title: {
	        text: 'Pedidos Atendidos Por Mês'
	    },

	    subtitle: {
	        text: 'Mês'
	    },

	    yAxis: {
	        title: {
	            text: 'Quantidade de Pedidos'
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },

	    plotOptions: {
	        series: {
	            pointStart: 01
	        }
	    },

	    series: [{
	        name: 'Atendidos',
	        data: [24, 19, 27, 32, 33, 45, 29, 41]
	    }]

	});
	
	
	$("#confirmarEntrega").click(function() {
		
		$("#divEntregaAndamento").hide();
		
		
	});
		

});
