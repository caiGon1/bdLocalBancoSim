package bdbancosimulacao;
public class BdBancoSimulacao {
    public static void main(String[] args) {
        grafica gf = new grafica();
        gf.setVisible(true);
        
        conexaoBD bd = new conexaoBD();
        bd.conectar();
        bd.criarTabela();
    }
    
}
