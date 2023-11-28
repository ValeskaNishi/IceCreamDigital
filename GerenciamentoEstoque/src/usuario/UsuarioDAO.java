package usuario;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import usuario.Usuario;
import produto.model.BancoDeDados;

public class UsuarioDAO {
            
   public void adicionarUsuario(Usuario usuario) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = BancoDeDados.abrirConexao();

        String sql = "INSERT INTO usuario (loginUsuario, senhaUsuario) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, usuario.getLoginUsuario());
        preparedStatement.setString(2, usuario.getSenhaUsuario());

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
   
   
   public boolean checaSeUsuarioExiste(String email, String password) {
       
	   Boolean usuarioExiste = false;
	   
	   Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       
       try {
           connection = BancoDeDados.abrirConexao();

           String sql = "SELECT * FROM usuario where loginUsuario = ? AND senhaUsuario = ?";
                		   
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1, email);
           preparedStatement.setString(2, password);

           resultSet = preparedStatement.executeQuery();

           if(resultSet.next()) {
        	   usuarioExiste = true;
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
               
       return usuarioExiste;
       
   }
   
   public List<Usuario> listarUsuario() {
   	
       List<Usuario> usuarios = new ArrayList<>();

       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;

       try {
           connection = BancoDeDados.abrirConexao();

           String sql = "SELECT loginUsuario FROM usuario";
           preparedStatement = connection.prepareStatement(sql);

           resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
               String email = resultSet.getString("loginUsuario");

               Usuario usuario = new Usuario(email);
               usuarios.add(usuario);
               
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
               
       return usuarios;
   }
}
