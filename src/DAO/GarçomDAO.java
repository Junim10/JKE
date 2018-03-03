package DAO;

import Modelo.categoria;
import Modelo.garçom;
import Modelo.proprietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GarçomDAO extends ExecuteSQL{
        public GarçomDAO(Connection con){
                super(con);
        }
        public boolean LogarGarçom(String login, String senha, String nome){
        boolean finalResult = false;
        try {
            String consulta = "SELECT * FROM garçom WHERE login = '"+ login +"'"
                    + ", senha = '"+ senha +"', nome = '"+ nome +"', level = '2'";
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {                    
                    garçom a = new garçom();
                    a.setLogin(rs.getString(1));
                    a.setSenha(rs.getString(2));
                    a.setNome(rs.getString(3));
                    a.setLevel(rs.getInt(4));
                    finalResult = true;
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return finalResult;
    }
    public List<garçom> ConsultaLevelGarçom(String nome, String login, String senha){
        String sql = "SELECT level FROM garçom WHERE nome = '" + nome + "', login = '" + login + "', senha = '" + senha + "'";
        List<garçom> lista = new ArrayList<>();
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null){
                while (rs.next()){
                    garçom a = new garçom();
                    a.setLevel(rs.getInt(1));
                    lista.add(a);
                }
                return lista;
            }else{
                return null;
            }
        }catch(Exception e){
            return null;
        }
    
    }

      public boolean TestarLevel(String nome, String login, String senha){
        boolean teste = false;
        try {
            String sql = "SELECT * FROM garçom WHERE nome ='" + nome + "', login ='" + login + "',"
                    + " senha ='" + senha + "', level = '2'";
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while(rs.next()){
                    teste = true;
                }
            }
        } catch (Exception e) {
        }return teste;
    }
      public List<garçom> CapturarGarçom(int cod){
        String sql = "SELECT * FROM garçom WHERE idgarçom ="+ cod +" ";
        List<garçom> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()) {
                    garçom c = new garçom();
                    c.setCod(rs.getInt(1));
                    c.setNome(rs.getString(2));
                    c.setTelefone(rs.getString(4));
                    c.setEndereco(rs.getString(5));
                    lista.add(c);
                }
                return lista;
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
          public String Alterar_Garçom(garçom a){
        String sql = "UPDATE garçom SET nome = ?, telefone = ?, endereco = ? WHERE idcategoria = ?";
        
            try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, a.getNome());
            ps.setString(2, a.getTelefone());
            ps.setString(3, a.getEndereco());
            ps.setInt(4, a.getCod());
            
            if(ps.executeUpdate() > 0){
                return "Alterado com sucesso...";
            }else{
                return "Erro ao alterar!";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
