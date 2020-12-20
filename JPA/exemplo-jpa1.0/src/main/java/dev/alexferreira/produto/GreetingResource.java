package dev.alexferreira.produto;

import dev.alexferreira.produto.model.Item;
import dev.alexferreira.produto.model.Produto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Path("/produto")
public class GreetingResource {

	@Inject
	ProdutoService produtoService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		Produto produto = produtoService.saveProduto();
		Produto produto1 = produtoService.getProduto(produto.getId());
		String descricao;
		if (produto1.getItemList() == null) {
			List<Item> itemList = produtoService.getItemPorProduto(produto.getId());
			descricao = itemList.stream().map(new Function<Item, String>() {
				@Override
				public String apply(Item item) {
					return item.getDescricao() + ":fromGet";
				}
			}).collect(Collectors.joining(","));
		} else {
			descricao = produto1.getItemList().get(0).getDescricao();
		}
		return "Hello Quarkus com Elder Moraes: " + produto1.getId() + ":" + produto.getDescricao() + " desc Item: " + descricao;
//		return "Hello Quarkus com Elder Moraes: " + produto1.getDescricao() + " desc Item: ";
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String get2(@PathParam("id") Integer id) {
		Produto produto = produtoService.getOtherSession(id);
		List<Item> itemList = produto.getItemList();
		if (itemList == null) return "item null";
		Item item = itemList.get(0);
		return "produto de outra sessao: " + produto.getId() + ":" + produto.getDescricao() + ":" + item.getId();
	}
}