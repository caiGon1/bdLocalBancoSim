package bdbancosimulacao;
import java.beans.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class conexaoBD {
    
    ArrayList<bdDTO> lista = new ArrayList<>();
    PreparedStatement pstm;
    
    public static Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:banco.db"; // O banco será salvo como "banco.db"
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão bem-sucedida!");
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
        return conn;
    }
    public static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "nome varchar(50) NOT NULL, "
                + "senha varchar(50) NOT NULL, "
                + "cpf integer unique primary key not null, "
                + "saldo REAL DEFAULT 0.0"
                + ");";

        try (Connection conn = conexaoBD.conectar();
             java.sql.Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
    
    public void adicionarPessoa(bdDTO o){
        String sql = "insert into usuarios (nome,senha,cpf,saldo) values (?,?,?,?)";
        
        try (Connection conn = conexaoBD.conectar();
             java.sql.Statement stmt = conn.createStatement()) {
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, o.getNome());
            pstm.setString(2, o.getSenha());
            pstm.setString(3, o.getCpf());
            pstm.setDouble(4, o.getSaldo());
            pstm.executeUpdate();
            
            System.out.println("Adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar: " + e.getMessage());
        }
        
    }
    
    public void removerPessoa(bdDTO o){
        String sql = "delete from usuarios where cpf=?;";
        
        try(Connection conn = conexaoBD.conectar();
                java.sql.Statement stmt = conn.createStatement()){
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, o.getCpf());
        } catch(Exception e){
            System.out.println("Erro ao remover: "+e.getMessage());
    }
}
    
    public ArrayList<bdDTO> listarUsers(){
        Connection conn = new conexaoBD().conectar();
        String sql = "select * from usuarios";
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                bdDTO dto = new bdDTO();
                dto.setNome(rs.getString("nome"));
                dto.setSenha(rs.getString("senha"));
                dto.setCpf(rs.getString("cpf"));
                dto.setSaldo(rs.getDouble("Saldo"));
                lista.add(dto);
            }
            pstm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado... CLASSE LISTAR FICHAS: " + ex);
        }
        return lista;
    }
}
