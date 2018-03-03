
package DAO;

import Modelo.garçom;
import Modelo.gerente;
import Modelo.proprietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GerenteDAO extends ExecuteSQL{
        public GerenteDAO(Connection con){
                super(con);
        }
        public boolean LogarGerente(String login, String senha, String nome){
        boolean finalResult = false;
        try {
            String consulta = "SELECT * FROM gerente WHERE login = '"+ login +"'"
                    + ", senha = '"+ senha +"', nome = '"+ nome +"', level = '1'";
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {                    
                    gerente a = new gerente();
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
     public List<gerente> ConsultaLevelGerente(String nome, String login, String senha){
        String sql = "SELECT level FROM gerente WHERE nome = '" + nome + "', login = '" + login + "', senha = '" + senha + "'";
        List<gerente> lista = new ArrayList<>();
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null){
                while (rs.next()){
                    gerente a = new gerente();
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
            String sql = "SELECT * FROM gerente WHERE nome ='" + nome + "', login ='" + login + "',"
                    + " senha ='" + senha + "', level = '1'";
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
}
