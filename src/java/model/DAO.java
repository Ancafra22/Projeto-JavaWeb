// Data Access Object - DAO
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DAO {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/dbproduto?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "";

    private Connection conectar(){
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }    
    }
    //crude criate
    public void inserirContato(JavaBeans produto){
        String create = "insert into produtos (nome,quantidade,valor,valorVenda) values(?,?,?,?)";
        try{
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(create);
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getQuantidade());
            pst.setDouble(3, produto.getValor());
            pst.setDouble(4, produto.getValorVenda());
            
            pst.executeUpdate();
            //encerrar a conexão
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //CRUD LISTAR
    public ArrayList<JavaBeans> listarProdutos(){
        ArrayList<JavaBeans> produtos = new ArrayList<>();
        String read = "select * from produtos order by id";
        try{
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                //variaveis de apoio
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                int quantidade = rs.getInt(3);
                double valor = rs.getDouble(4);
                double valorVenda = rs.getDouble(5);
                produtos.add(new JavaBeans(id,nome,quantidade,valor,valorVenda));
            }
            con.close();
                return produtos;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    //cru update
    public void selecionarProdutos(JavaBeans produto){
        String read2 = "select * from produtos where id = ?";
        try{
          Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read2);
            pst.setInt(1, produto.getId());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                //setar as variaveis
                produto.setId(rs.getInt(1));
                produto.setNome(rs.getString(2));
                produto.setQuantidade(rs.getInt(3));
                produto.setValor(rs.getDouble(4));
                produto.setValorVenda(rs.getDouble(5));
            }
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    //editar produto
    public void alterarProduto(JavaBeans produto){
        String create = "update produtos set nome=?, quantidade=?,valor=?,valorVenda=? where id=?";
        try{
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(create);
            pst.setString   (1, produto.getNome());
            pst.setInt      (2, produto.getQuantidade());
            pst.setDouble   (3, produto.getValor());
            pst.setDouble   (4, produto.getValorVenda());
            pst.setInt      (5, produto.getId());
            pst.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.print(e);
        }
    }
    //crud delete
    public void deletarContato(JavaBeans produto){
        String delete = "delete from produtos where id=?";
        try{
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setInt(1, produto.getId());
            pst.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.print(e);
        }
    }
}