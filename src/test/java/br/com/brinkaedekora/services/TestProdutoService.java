package br.com.brinkaedekora.services;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import br.com.brinkaedekora.models.Produto;
import static org.junit.Assert.*;

public class TestProdutoService {

    // Variabels that will be used in the tests
    ProdutoService produtoService = new ProdutoService();
    Produto produto = new Produto();
    String name = "This is one name";
    String description = "one description";
    String url_foto = "one url_foto";
    String url_video = "one url_video";
    String type = "one type";

    @Before
    public void getUp() {
        // Initializing one "Produto" object to be used in the tests
        produto.setName(name);
        produto.setDescription(description);
        produto.setUrl_foto(url_foto);
        produto.setUrl_video(url_video);
        produto.setType(type);
    }

    @Test
    public void testCRUD() {
        // Checking CREATION
        assertTrue(produtoService.save(produto));

        // Checking READ
        // 1. By Id
        Produto productId = produtoService.getProdutoById(produto.getId());
        assertEquals(produto.toString(), productId.toString());
        // 2. By Name
        Produto produtoName = produtoService.findByName(produto.getName()).get(0);
        assertEquals(produtoName.getName(), produto.getName());
        // 3. By Type
        Produto produtoType = produtoService.findByType(produto.getType()).get(0);
        assertEquals(produtoType.getType(), produto.getType());

        // Checking UPDATE
        // Changing variable to be tested
        name = "other name";
        description = "other description";
        url_foto = "other url_foto";
        url_video = "other url_video";
        type = "other type";

        produto.setName(name);
        produto.setDescription(description);
        produto.setUrl_foto(url_foto);
        produto.setUrl_video(url_video);
        produto.setType(type);
        assertTrue(produtoService.save(produto));
        Produto produtoModificado = produtoService.getProdutoById(produto.getId());
        assertEquals(produto.toString(), produtoModificado.toString());

        // Checking Delete
        // Saving the product ID before delete it
        Long id = produto.getId();
        // Deleting the product
        assertTrue(produtoService.delete(produto.getId()));
        // Checking if the product is not found anymore
        assertNull(produtoService.getProdutoById(id));

    }

    @Test
    public void testGetProdutos() {
        //Saving a "Produto" in the Database
        assertTrue(produtoService.save(produto));
        //Checking if the service "getProdutos" return some value
    }

    @Ignore
    @Test
    public void testDeleteAll() {
        // Checking if all data is removed
        assertTrue(produtoService.deleteAllData());

    }

}
