package br.com.moveasy.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.moveasy.dto.CadastroEmpresaDTO;
import br.com.moveasy.dto.PedidosDTO;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.service.EmpresasService;
import br.com.moveasy.service.PedidosService;
import br.com.moveasy.service.UsuariosService;

@Path("empresa")
public class CadastroEmpresaController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Empresas> listCadastroEmpresaDTO() {
		EmpresasService empresasService = new EmpresasService();
		UsuariosService usuariosService = new UsuariosService();
		try { 
			usuariosService.listar();
			return empresasService.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/")
		public Response create(CadastroEmpresaDTO empresa) {
		UsuariosService usuariosService = new UsuariosService();	
		EmpresasService empresasService = new EmpresasService();
			try {
				usuariosService.cadastrar(empresa.getCod_usuario(), empresa.getLogin(), empresa.getSenha(), empresa.getStatus());
				empresasService.cadastrar(empresa.getCod_empresa(), empresa.getNome_fantasia(), empresa.getRazao_social(), empresa.getCnpj(), empresa.getCod_empresa(), empresa.getEmail(), empresa.getTelefone(), empresa.getCod_usuario());
				return Response.status(Response.Status.OK).build();
			} catch (SQLException e) {
				e.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(CadastroEmpresaDTO empresa) {
		UsuariosService usuariosService = new UsuariosService();	
		EmpresasService empresasService = new EmpresasService();
		
		try {
			usuariosService.editar(empresa.getCod_usuario());
			empresasService.editar(empresa.getCod_empresa());
			
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("{codigo}/")
	public Response delete(@PathParam("codigo") int codEmpresa) {
		UsuariosService usuariosService = new UsuariosService();	
		EmpresasService empresasService = new EmpresasService();
		try {
			usuariosService.editar(codEmpresa);
			empresasService.editar(codEmpresa);
			
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
