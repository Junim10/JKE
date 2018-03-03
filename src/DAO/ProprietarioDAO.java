
package DAO;

import Modelo.proprietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO extends ExecuteSQL{
        public ProprietarioDAO(Connection con){
                super(con);
        }
        public boolean LogarProprietario(String login, String senha, String nome){
        boolean finalResult = false;
        try {
            String consulta = "SELECT * FROM proprietario WHERE login = '"+ login +"'"
                    + ", senha = '"+ senha +"', nome = '"+ nome +"', level = '0'";
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {                    
                    proprietario a = new proprietario();
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
        public List<proprietario> ConsultaLevelProprietario(String nome, String login, String senha){
        String sql = "SELECT level FROM proprietario WHERE nome = '" + nome + "', login = '" + login + "', senha = '" + senha + "'";
        List<proprietario> lista = new ArrayList<>();
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null){
                while (rs.next()){
                    proprietario a = new proprietario();
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
        public boolean TestarLevelProprietario(String nome, String login, String senha){
        boolean teste = false;
        try {
            String sql = "SELECT * FROM proprietario WHERE nome ='" + nome + "', login ='" + login + "',"
                    + " senha ='" + senha + "', level = '0'";
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
