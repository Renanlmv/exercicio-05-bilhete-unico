public class Usuario {
    String nome, perfil;
    long cpf;

    // método construtor para gerar um usuário
    public Usuario(String nome, long cpf, String perfil) {
        this.nome = nome;
        this.cpf = cpf;
        this.perfil = perfil;
    }
}
