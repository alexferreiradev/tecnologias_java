package dev.alexferreira.produto;

import dev.alexferreira.produto.ProdutoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/produto")
public class GreetingResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Produto produto = produtoService.saveProduto();
        Produto produto1 = produtoService.getProduto(produto.getId());
        return "Hello Quarkus com Elder Moraes: " + produto.getDescricao();
    }
}