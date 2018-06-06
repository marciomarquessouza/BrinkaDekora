package br.com.brinkaedekora.servlet;

import br.com.brinkaedekora.models.Produto;
import br.com.brinkaedekora.services.ProdutoService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import br.com.brinkaedekora.util.JAXBUtil;
import br.com.brinkaedekora.util.ServletUtil;
import br.com.brinkaedekora.domain.ListaProdutos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/produtos/*")
public class ProdutoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProdutoService produtoService = new ProdutoService();

    protected void doGet (HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        List<Produto> produtos = produtoService.getProdutos();
        ListaProdutos listaProdutos = new ListaProdutos();
        listaProdutos.setProdutos(produtos);
        /* String xml = JAXBUtil.toXml(listaProdutos);
        ServletUtil.writeXml(response, xml);
        */
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaProdutos);
        ServletUtil.writeJson(response, json);
    }
}
