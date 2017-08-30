package br.com.moveasy.testes;

import java.util.List;
import java.sql.SQLException;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.service.CidadesService;

public class TesteDAO {

	public static void main(String[] args) throws SQLException {
		
		CidadesService cidadesService = new CidadesService();
		List<Cidades> lCidades = cidadesService.listar();

		for (Cidades cidade : lCidades) {

			System.out.println(cidadesService.imprimirDados(cidade));
			
		}
		
	}
	
}
