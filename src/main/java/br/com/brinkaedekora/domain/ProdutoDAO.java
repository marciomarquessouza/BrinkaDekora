package br.com.brinkaedekora.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import br.com.brinkaedekora.models.Produto;

public class ProdutoDAO extends BaseDAO {

    public Produto getProdutoById (Long id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Produto produto;

        if (id == null) return null;

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from produto where id=?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                produto = create(resultSet);
                resultSet.close();
                return produto;
            }

        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return null;
    }

    public List<Produto> findByName (String name) throws SQLException {

        List<Produto> produtos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from produto where name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                produtos.add(create(resultSet));
            }
            resultSet.close();
        }finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return produtos;
    }

    public List<Produto> getProdutos () throws SQLException {

        List<Produto> produtos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from produto;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                produtos.add(create(resultSet));
            }
            resultSet.close();
        }finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return produtos;
    }

    public List<Produto> findByType(String type) throws SQLException {

        List<Produto> produtos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from produto where produto.type = ?");
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                produtos.add(create(resultSet));
            }
            resultSet.close();
        }finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return produtos;
    }

    private Produto create(ResultSet resultSet) throws SQLException {

        Produto produto = new Produto();

        produto.setId(resultSet.getLong("id"));
        produto.setName(resultSet.getString("name"));
        produto.setDescription(resultSet.getString("description"));
        produto.setUrl_foto(resultSet.getString("url_foto"));
        produto.setUrl_video(resultSet.getString("url_video"));
        produto.setType(resultSet.getString("type"));

        return produto;

    }

    public void save (Produto produto) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            if (produto.getId() == null) {
                preparedStatement = connection.prepareStatement("insert into produto(name, description, url_foto, url_video, type)" +
                        " values(?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            } else {
                preparedStatement = connection.prepareStatement("update produto set name=?, description=?, url_foto=?, url_video=?, type=?" +
                        " where id=?");
            }

            preparedStatement.setString(1, produto.getName());
            preparedStatement.setString(2, produto.getDescription());
            preparedStatement.setString(3, produto.getUrl_foto());
            preparedStatement.setString(4, produto.getUrl_video());
            preparedStatement.setString(5, produto.getType());
            if (produto.getId() != null) preparedStatement.setLong(6, produto.getId());

            int count = preparedStatement.executeUpdate();

            if (count == 0) throw new SQLException("Erro ao inserir/atualizar banco");

            if (produto.getId() == null) {
                Long id = getGeneratedId(preparedStatement);
                produto.setId(id);
            }

        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    private static Long getGeneratedId(Statement statement) throws SQLException {
        ResultSet resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) {
            return  resultSet.getLong(1);
        }

        return 0L;
    }

    public boolean delete(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        if (id == null) return false;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("delete from produto where id =?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

    }

}
