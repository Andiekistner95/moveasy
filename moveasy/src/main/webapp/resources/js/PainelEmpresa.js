	// avaliação
	var $star_rating = $('.star-rating .fa');

	var SetRatingStar = function() {
	  return $star_rating.each(function() {
	    if (parseInt($star_rating.siblings('input.rating-value').val()) >= parseInt($(this).data('rating'))) {
	      return $(this).removeClass('fa-star-o').addClass('fa-star');
	    } else {
	      return $(this).removeClass('fa-star').addClass('fa-star-o');
	    }
	  });
	};

	$star_rating.on('click', function() {
	  $star_rating.siblings('input.rating-value').val($(this).data('rating'));
	  return SetRatingStar();	
	});

	SetRatingStar();


$( document ).ready(function() {

	$("#divDashboard").show();
	$("#divFaturas").hide();
	$("#divAvaliacaoPendente").hide();

	$( "#dashboard" ).click(function() {
		$("#divDashboard").show();
		$("#divFaturas").hide();
		$("#divAvaliacaoPendente").hide();
	});

	$( "#faturas" ).click(function() {
		$("#divDashboard").hide();
		$("#divFaturas").show();
		$("#divAvaliacaoPendente").hide();
	});

	$( "#avaliacaoPendente" ).click(function() {
		$("#divDashboard").hide();
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
	        text: 'Resumo de pedidos - 09-2017'
	    },
	    subtitle: {
	        text: 'Pedidos Abertos x Finalizados'
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
	            ['Abertos', 12],
	            ['Fechados', 19],
	            ['Não assumidos', 4],
	            ['Avaliação Pendente', 4]
	        ]
	    }]
	});

	// Histórico de pedido por mês
	Highcharts.chart('chartHistoricoPedidosMes', {

	    title: {
	        text: 'Pedidos Emitidos Por Mês'
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
	        name: 'Pedidos Emitidos',
	        data: [14, 19, 27, 32, 21, 25, 29, 18]
	    }]

	});

});
