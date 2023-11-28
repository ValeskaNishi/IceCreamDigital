package produto.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import produto.Produto;


public class ProdutoDAO {


	public List<Produto> listarProdutosComPoucoEstoque() {
        List<Produto> produtos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = BancoDeDados.abrirConexao();

            String sql = "SELECT * FROM produto WHERE quantidadeEstoque < 10";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	int codigoProduto = resultSet.getInt("codigoProduto");
                String nome = resultSet.getString("nomeProduto");
                int qtdeEstoque = resultSet.getInt("quantidadeEstoque");
                String modeloProduto = resultSet.getString("modeloProduto");
                double precoProduto = resultSet.getDouble("precoProduto");
                String descricaoProduto = resultSet.getString("descricaoProduto");

                Produto produto = new Produto(codigoProduto, nome, qtdeEstoque, modeloProduto, precoProduto, descricaoProduto);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return produtos;
    }


    public List<Produto> listarProdutos() {

        List<Produto> produtos = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = BancoDeDados.abrirConexao();

            String sql = "SELECT * FROM produto";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	int codigoProduto = resultSet.getInt("codigoProduto");
                String nome = resultSet.getString("nomeProduto");
                int qtdeEstoque = resultSet.getInt("quantidadeEstoque");
                double precoProduto = resultSet.getDouble("precoProduto");
                String modeloProduto = resultSet.getString("modeloProduto");
                String descricaoProduto = resultSet.getString("descricaoProduto");

                Produto produto = new Produto(codigoProduto, nome, qtdeEstoque, modeloProduto, precoProduto, descricaoProduto);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BancoDeDados.abrirConexao();

            String sql = "INSERT INTO produto (nomeProduto,modeloProduto, precoProduto, quantidadeEstoque, descricaoProduto) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, produto.getNomeProduto());
            preparedStatement.setString(2, produto.getModeloProduto());
            preparedStatement.setDouble(3, produto.getPrecoProduto());
            preparedStatement.setInt(4, produto.getQuantidadeEstoque());
            preparedStatement.setString(5, produto.getDescricaoProduto());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void insereImagem(FileInputStream fis, long l) {
    	Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BancoDeDados.abrirConexao();

            String sql = "INSERT INTO produto (imagemProduto) VALUES (?)";
            
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBinaryStream(1, fis, (int) l);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void atualizarProduto(Produto produto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BancoDeDados.abrirConexao();

            String sql = "UPDATE produto SET nomeProduto = ?, modeloProduto = ?, precoProduto = ?, quantidadeEstoque = ?, descricaoProduto = ? WHERE codigoProduto = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, produto.getNomeProduto());
            preparedStatement.setString(2, produto.getModeloProduto());
            preparedStatement.setDouble(3, produto.getPrecoProduto());
            preparedStatement.setInt(4, produto.getQuantidadeEstoque());
            preparedStatement.setString(5, produto.getDescricaoProduto());
            preparedStatement.setInt(6, produto.getCodigoProduto());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removerProduto(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BancoDeDados.abrirConexao();

            String sql = "DELETE FROM produto WHERE codigoProduto = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }





}