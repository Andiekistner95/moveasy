package br.com.moveasy.testes;

import java.util.Date;
import java.util.List;
import java.sql.SQLException;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Pedidos;
import br.com.moveasy.model.Tipo_servico;
import br.com.moveasy.service.CidadesService;
import br.com.moveasy.service.EnderecoService;
import br.com.moveasy.service.EntregadoresService;
import br.com.moveasy.service.EstadosService;
import br.com.moveasy.service.PedidosService;
import br.com.moveasy.service.Tipo_servicoService;

public class TesteDAO {

	public static void main(String[] args) throws SQLException {
		
		//CidadesService cidadesService = new CidadesService();
		//List<Cidades> lCidades = cidadesService.listar();
		
		EstadosService estadosService = new EstadosService();
		
		EnderecoService enderecoService = new EnderecoService();
		
		
		//List<Estados> lEstados = estadosService.listar();
		
		//Estados estado = estadosService.listar(24);
		//System.out.println(estadosService.imprimirDados(estado));
		
		//Cidades cidade = cidadesService.listar(5445);
		//System.out.println(cidadesService.imprimirDados(cidade));
		
		//cidadesService.cadastrar("CIDADE_MOVEASY", estado);
		//cidadesService.cadastrar("TESTE_CIDADE", estado);
		//cidadesService.deletar(5446);
		
		Tipo_servicoService tipo_servicoService = new Tipo_servicoService();
		
		//Inserindo
		//tipo_servicoService.cadastrar("Teste", 30d);
		
		//Listar
		//Tipo_servico tipo_servico = tipo_servicoService.listar(1);
	   // System.out.println(tipo_servicoService.imprimirDados(tipo_servico));
	
		//Deletar
	    //tipo_servicoService.deletar(5567);
		
		
		//PedidosService pedidosService = new PedidosService();
		
		//listar
		//List<Pedidos> lPedidos = pedidosService.listar();
		//Pedidos pedido = pedidosService.listar(2);
		
		//System.out.println("Número do pedido: " + pedido.getCod_pedido());
		//System.out.println("\n rua:"+pedido.getDestinatario().getEndereco_dest().getRua());
		
		//for (Pedidos pedidos : lPedidos) {
		//	System.out.println("Número do pedido: " + pedidos.getCod_pedido());
		//	System.out.println("\n rua:"+pedidos.getDestinatario().getEndereco_dest().getRua());
		//}
	    		
	    pedidosService.cadastrar(1, 1, 1, 1, new Date(), 10, 35, "Teste de insert via java");
	   		
		//Teste Avalia��o
		//AvaliacaoService avaliacaoService = new AvaliacaoService();
	 	//avaliacaoService.cadastrar(1, 5);		
		//pedidosService.cadastrar(1, 1, 1, 1, new Date(), 10, 35, "Teste de insert via java");
		
		
	 		 	
		//Listar
		//Avaliacao_Ent avaliacao_Ent =  avaliacaoService.listar(2);
		//System.out.println(avaliacaoService.listar(23).getCod_avaliacao());
		
		//System.out.println(avaliacaoService.imprimirDados(23));
	
	
		//EntregadoresService entregadoresService = new EntregadoresService();
				//System.out.println(entregadoresService.listar().size() );
		
		
		
	
	}
		


	}
	

