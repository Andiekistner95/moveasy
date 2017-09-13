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

import br.com.moveasy.dto.PedidosDTO;
import br.com.moveasy.model.Pedidos;
import br.com.moveasy.service.PedidosService;


@Path("pedidos")
public class pedidosController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Pedidos> listPedidos() {
		PedidosService pedidosService = new PedidosService();
		try {
			return pedidosService.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(PedidosDTO pedido) {
		PedidosService pedidosService = new PedidosService();
		try {
			pedidosService.cadastrar(pedido.toPedidos());
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(PedidosDTO pedidos) {
		PedidosService pedidosService = new PedidosService();
		try {
			pedidosService.editar(codPedido);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("{codigo}/")
	public Response delete(@PathParam("codigo") int codigo) {
		PedidosService pedidosService = new PedidosService();
		try {
			pedidosService.deletar(pedidos);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
