package br.com.brinkaedekora.services;

import br.com.brinkaedekora.models.Produto;
import br.com.brinkaedekora.domain.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ProdutoService {

    ProdutoDAO produtoDAO = new ProdutoDAO();

    // Lista todos os produtos
    public List<Produto> getProdutos() {
        try {
            return produtoDAO.getProdutos();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<Produto>();
    }

    // Busca produto pelo Id
    public Produto getProdutoById (Long id) {
        try {
            return produtoDAO.getProdutoById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Busca produto pelo Nome
    public List<Produto> findByName (String name) {
        try {
            return produtoDAO.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Busca produto pelo Tipo
    public List<Produto> findByType (String type) {
        try {
            return produtoDAO.findByType(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Salva ou atualiza o Produto
    public boolean save(Produto produto) {

        try {
            produtoDAO.save(produto);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Deleta o produto
    public boolean delete(Long id) {
        try {
            return produtoDAO.delete(id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAllData() {
        try {
            for (Produto produto : produtoDAO.getProdutos()) {
                produtoDAO.delete(produto.getId());
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
