
package DAO;

import Modelo.categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO extends ExecuteSQL{
        public CategoriaDAO(Connection con){
                super(con);
        }
         public boolean Testar_Categoria(int cod){
        boolean Resultado = false;
        try{
            String sql = "SELECT * FROM categoria WHERE idcategoria = "+ cod +"";
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs != null){
                while(rs.next()){
                    Resultado = true;
                }
            }
        }catch(SQLException ex){
            ex.getMessage();
        }return Resultado;
    }
         public List<categoria> CapturarCategoria(int cod){
        String sql = "SELECT * FROM categoria WHERE idcategoria ="+ cod +" ";
        List<categoria> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()) {
                    categoria c = new categoria();
                    c.setCod(rs.getInt(1));
                    c.setNome(rs.getString(2));
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
          public String Alterar_Categoria(categoria a){
        String sql = "UPDATE categoria SET nome = ? WHERE idcategoria = ?";
        
            try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getCod());
            
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
