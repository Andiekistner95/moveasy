package br.com.moveasy.testes;

import java.util.List;
import java.sql.SQLException;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Estados;
import br.com.moveasy.service.CidadesService;
import br.com.moveasy.service.EstadosService;

public class TesteDAO {

	public static void main(String[] args) throws SQLException {
		
		CidadesService cidadesService = new CidadesService();
		List<Cidades> lCidades = cidadesService.listar();
		
		EstadosService estadosService = new EstadosService();
		
		
		List<Estados> lEstados = estadosService.listar();
		
		Estados estado = estadosService.listar(24);
		System.out.println(estadosService.imprimirDados(estado));
		
		//Cidades cidade = cidadesService.listar(5445);
		//System.out.println(cidadesService.imprimirDados(cidade));
		
		//cidadesService.cadastrar("CIDADE_MOVEASY", estado);
		//cidadesService.cadastrar("TESTE_CIDADE", estado);
		cidadesService.deletar(5446);
		
		
		

		


	}
	
}
