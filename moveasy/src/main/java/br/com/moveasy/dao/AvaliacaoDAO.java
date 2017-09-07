package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.moveasy.model.Avaliacao_Ent;
import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Tipo_servico;
import br.com.moveasy.model.Usuarios;

public class AvaliacaoDAO {

	private final Connection conexao;

	public AvaliacaoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public String cadastrar(int codEntregador, int estrelas) throws SQLException {

		String sql = "INSERT INTO AVALIACAO_ENT ( COD_AVALIACAO, COD_ENT, ESTRELAS ) VALUES ( SEQ_AVALIACAO.NEXTVAL, ?, ? )";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, codEntregador);
		statement.setInt(2, (int) estrelas);

		boolean inserido = statement.executeUpdate() > 0;
		String info;
		if (inserido == true) {
			info = "Avaliação inserido com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}

		return info;

	}

	public String imprimirDados(int codAvaliacao) throws SQLException {

		String dados = "";

		String sql;
		sql = " SELECT "
				+ " AVALIACAO.COD_AVALIACAO, "
				+ " AVALIACAO.ESTRELAS, "
				+ " ENTREGADORES.COD_ENT, "
                + " ENTREGADORES.NOME_ENT, "
				+ " ENTREGADORES.SOBRENOME_ENT, "
				+ " ENTREGADORES.CPF_ENT, "
				+ " ENDERECO.COD_ENDERECO,"
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO, "
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF, "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " AVALIACAO_ENT AVALIACAO"
			+ " INNER JOIN ENTREGADORES ON "
					+ " ENTREGADORES.COD_ENT = AVALIACAO.COD_ENT"
			+ " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = ENDERECO_ENT "
			+ " INNER JOIN CIDADES ON "
				+ " COD_CIDADE = ENDERECO.CIDADE "
			+ " INNER JOIN ESTADOS ON "
				+ " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ "INNER JOIN USUARIOS ON "
			+ "USUARIOS.COD_USUARIO = ENTREGADORES.USUARIO" 
		             + " WHERE "
				   + " COD_AVALIACAO = ? ";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codAvaliacao);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
					//Avaliacao
					int cod_avaliacao = rs.getInt(1);
					int estrelas = rs.getInt(2);
					
					//Entregadores
					int codEntregador = rs.getInt(3);
					String nomeEntregador = rs.getString(4);
					String sobrenomeEntregador = rs.getString(5);
					String cpf = rs.getString(6);
					
					//Endereco
					int cod_endereco = rs.getInt(7);
					String rua = rs.getString(8);
					String numero = rs.getString(9);
					String complemento = rs.getString(10);
					String bairro = rs.getString(11);
					
					//Cidades
					int cod_cidade = rs.getInt(12);
					String nome_cidade = rs.getString(13);
					
					//Estados
					int cod_estado = rs.getInt(14);
					String nome_estado = rs.getString(15);
					String uf = rs.getString(16);
					
					//Usuarios
					int cod_usuario = rs.getInt(17);
					String login = rs.getString(18);
					String senha  = rs.getString(19);
					int status = rs.getInt(20);

					dados += "Código da avaliação: <" + cod_avaliacao +"Código do entregador: <" + codEntregador + "> Estrelas: <" + estrelas + ">";

				}

			}
		}

		return dados;
	}

	public List<Avaliacao_Ent> listar() throws SQLException {
		List<Avaliacao_Ent> lAvaliacao_Ent = new ArrayList<>();
		
		String sql;
		sql = " SELECT "
				+ " AVALIACAO.COD_AVALIACAO, "
				+ " AVALIACAO.ESTRELAS, "
				+ " ENTREGADORES.COD_ENT, "
                + " ENTREGADORES.NOME_ENT, "
				+ " ENTREGADORES.SOBRENOME_ENT, "
				+ " ENTREGADORES.CPF_ENT, "
				+ " ENDERECO.COD_ENDERECO,"
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO, "
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF, "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " AVALIACAO_ENT AVALIACAO"
			+ " INNER JOIN ENTREGADORES ON "
					+ " ENTREGADORES.COD_ENT = AVALIACAO.COD_ENT"
			+ " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = ENDERECO_ENT "
			+ " INNER JOIN CIDADES ON "
				+ " COD_CIDADE = ENDERECO.CIDADE "
			+ " INNER JOIN ESTADOS ON "
				+ " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ "INNER JOIN USUARIOS ON "
			+ "USUARIOS.COD_USUARIO = ENTREGADORES.USUARIO" ;
											
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet())
			{
				while (rs.next()) {
					
					//Avaliacao
					int cod_avaliacao = rs.getInt(1);
					int estrelas = rs.getInt(2);
					
					//Entregadores
					int codEntregador = rs.getInt(3);
					String nomeEntregador = rs.getString(4);
					String sobrenomeEntregador = rs.getString(5);
					String cpf = rs.getString(6);
					
					//Endereco
					int cod_endereco = rs.getInt(7);
					String rua = rs.getString(8);
					String numero = rs.getString(9);
					String complemento = rs.getString(10);
					String bairro = rs.getString(11);
					
					//Cidades
					int cod_cidade = rs.getInt(12);
					String nome_cidade = rs.getString(13);
					
					//Estados
					int cod_estado = rs.getInt(14);
					String nome_estado = rs.getString(15);
					String uf = rs.getString(16);
					
					//Usuarios
					int cod_usuario = rs.getInt(17);
					String login = rs.getString(18);
					String senha  = rs.getString(19);
					int status = rs.getInt(20);
										
					
					Estados estado = new Estados(cod_estado, nome_estado, uf);
					Cidades cidade = new Cidades(cod_cidade, nome_cidade, estado);
					Endereco endereco = new Endereco(cod_endereco, rua, numero, complemento, bairro, cidade);
					Usuarios usuarios = new Usuarios(cod_usuario, login, senha, status);
					Entregadores entregador = new Entregadores(codEntregador, nomeEntregador, sobrenomeEntregador, cpf, endereco, usuarios );
					
					Avaliacao_Ent avaliacao_Ent = new Avaliacao_Ent(cod_avaliacao, entregador, estrelas);
					
					lAvaliacao_Ent.add(avaliacao_Ent);
										
				}
			}
		}

		return lAvaliacao_Ent;

	}
	
	
		public Avaliacao_Ent listar(int codigo) throws SQLException {
			Avaliacao_Ent avaliacao_Ent = null;

		String sql;
			sql = " SELECT "
				+ " AVALIACAO.COD_AVALIACAO, "
				+ " AVALIACAO.ESTRELAS, "
				+ " ENTREGADORES.COD_ENT, "
                + " ENTREGADORES.NOME_ENT, "
				+ " ENTREGADORES.SOBRENOME_ENT, "
				+ " ENTREGADORES.CPF_ENT, "
				+ " ENDERECO.COD_ENDERECO,"
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO, "
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF, "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " AVALIACAO_ENT AVALIACAO"
			+ " INNER JOIN ENTREGADORES ON "
					+ " ENTREGADORES.COD_ENT = AVALIACAO.COD_ENT"
			+ " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = ENDERECO_ENT "
			+ " INNER JOIN CIDADES ON "
				+ " COD_CIDADE = ENDERECO.CIDADE "
			+ " INNER JOIN ESTADOS ON "
				+ " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ "INNER JOIN USUARIOS ON "
			+ "USUARIOS.COD_USUARIO = ENTREGADORES.USUARIO" 
		             + " WHERE "
				   + " COD_AVALIACAO = ? ";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
					//Avaliacao
					int cod_avaliacao = rs.getInt(1);
					int estrelas = rs.getInt(2);
					
					//Entregadores
					int codEntregador = rs.getInt(3);
					String nomeEntregador = rs.getString(4);
					String sobrenomeEntregador = rs.getString(5);
					String cpf = rs.getString(6);
					
					//Endereco
					int cod_endereco = rs.getInt(7);
					String rua = rs.getString(8);
					String numero = rs.getString(9);
					String complemento = rs.getString(10);
					String bairro = rs.getString(11);
					
					//Cidades
					int cod_cidade = rs.getInt(12);
					String nome_cidade = rs.getString(13);
					
					//Estados
					int cod_estado = rs.getInt(14);
					String nome_estado = rs.getString(15);
					String uf = rs.getString(16);
					
					//Usuarios
					int cod_usuario = rs.getInt(17);
					String login = rs.getString(18);
					String senha  = rs.getString(19);
					int status = rs.getInt(20);
										
					
					Estados estado = new Estados(cod_estado, nome_estado, uf);
					Cidades cidade = new Cidades(cod_cidade, nome_cidade, estado);
					Endereco endereco = new Endereco(cod_endereco, rua, numero, complemento, bairro, cidade);
					Usuarios usuarios = new Usuarios(cod_usuario, login, senha, status);
					Entregadores entregador = new Entregadores(codEntregador, nomeEntregador, sobrenomeEntregador, cpf, endereco, usuarios );
					avaliacao_Ent = new Avaliacao_Ent(cod_avaliacao, entregador, estrelas);
					
				}
			}
		}
		return avaliacao_Ent;


	}

}


