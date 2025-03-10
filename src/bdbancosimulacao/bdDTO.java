package bdbancosimulacao;

public class bdDTO {
    private String nome, senha, cpf;
    private double saldo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public bdDTO(){
        
    }
    public bdDTO(String nome, String senha, String cpf, double saldo){
        this.nome=nome;
        this.senha=senha;
        this.cpf=cpf;
        this.saldo=saldo;
    }
    
}
