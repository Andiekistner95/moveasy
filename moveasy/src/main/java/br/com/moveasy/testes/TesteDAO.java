package br.com.moveasy.testes;

import java.util.List;
import java.sql.SQLException;

import br.com.moveasy.model.Avaliacao_Ent;
import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Tipo_servico;
import br.com.moveasy.model.Usuarios;
import br.com.moveasy.service.AvaliacaoService;
import br.com.moveasy.service.CidadesService;
import br.com.moveasy.service.EnderecoService;
import br.com.moveasy.service.EstadosService;
import br.com.moveasy.service.Tipo_servicoService;

public class TesteDAO {

	public static void main(String[] args) throws SQLException {
		
		//CidadesService cidadesService = new CidadesService();
		//List<Cidades> lCidades = cidadesService.listar();
		
		//EstadosService estadosService = new EstadosService();
		
		
		//List<Estados> lEstados = estadosService.listar();
		
		//Estados estado = estadosService.listar(24);
		//System.out.println(estadosService.imprimirDados(estado));
		
		//Cidades cidade = cidadesService.listar(5445);
		//System.out.println(cidadesService.imprimirDados(cidade));
		
		//cidadesService.cadastrar("CIDADE_MOVEASY", estado);
		//cidadesService.cadastrar("TESTE_CIDADE", estado);
		//cidadesService.deletar(5446);
		
		//Tipo_servicoService tipo_servicoService = new Tipo_servicoService();
		
		//Inserindo
		//tipo_servicoService.cadastrar("Teste", 30d);
		
		//Listar
		//Tipo_servico tipo_servico = tipo_servicoService.listar(1);
	   // System.out.println(tipo_servicoService.imprimirDados(tipo_servico));
	
		//Deletar
	    //tipo_servicoService.deletar(5567);
	   		
		//Teste Avaliação
		AvaliacaoService avaliacaoService = new AvaliacaoService();
	 	//avaliacaoService.cadastrar(1, 5);		
		
	 		 	
		//Listar
		//AvaliacaoService avaliacao_Ent = (AvaliacaoService) avaliacaoService.listar();
		//System.out.println(avaliacaoService.imprimirDados(23));
		
		System.out.println(avaliacaoService.imprimirDados(23));
	
	
	
	}
		


	}
	

