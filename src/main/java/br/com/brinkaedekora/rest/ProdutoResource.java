package br.com.brinkaedekora.rest;

import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import br.com.brinkaedekora.models.Produto;
import br.com.brinkaedekora.services.ProdutoService;
import br.com.brinkaedekora.domain.Response;


@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON + ";chartset=UTF-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class ProdutoResource {

    ProdutoService produtoService = new ProdutoService();

    @GET
    public List<Produto> get() {
        return produtoService.getProdutos();
    }

    @GET
    @Path("{id}")
    public Produto getById(@PathParam("id") long id) {
        return produtoService.getProdutoById(id);
    }

    @GET
    @Path("/name/{name}")
    public List<Produto> getByName(@PathParam("name") String name) {
        return produtoService.findByName(name);
    }

    @GET
    @Path("/type/{type}")
    public List<Produto> getByType(@PathParam("type") String type) {
        return produtoService.findByType(type);
    }

    @POST
    public Response post(Produto produto) {
        produtoService.save(produto);
        return Response.ok("Carro cadastrado com sucesso");
    }

    @PUT
    public Response put (Produto produto) {
        produtoService.save(produto);
        return Response.ok("Produto atualizado com sucesso");
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        produtoService.delete(id);
        return Response.ok("Produto excluído com sucesso");
    }


}
