import java.text.DecimalFormat;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;
import static java.lang.Double.*;


public class Util {
    // banco de dados
    private BilheteUnico[] bilhete = new BilheteUnico[5];
    private int index = 0;

    // menu principal
    public void menuPrincipal() {
        int opcao = 0, opcaoAdm = 0;
        String menu = "1. Administrador\n2. Usuário\n3. Finalizar";

        do {
             opcao = parseInt(showInputDialog(menu));
             switch(opcao) {
                 case 1:
                     menuAdm();
                     break;
             }
        } while (opcao != 3);
    }

    // menu do administrador
    private void menuAdm() {
        int opcao;

        // mensagem com opções
        String menuAdm = "MENU ADMINISTRADOR\n";
        menuAdm += "1. Emitir bilhete\n";
        menuAdm += "2. Listar bilhetes\n";
        menuAdm += "3. Excluir bilhete\n";
        menuAdm += "4. Sair";

        do {
            opcao = parseInt(showInputDialog(menuAdm));
            switch(opcao) {
                case 1:
                    emitirBilhete();
                    break;
                case 2:
                    listarBilhetes();
                    break;
            }
        } while(opcao != 4);
    }

    private void emitirBilhete() {
        String nome, perfil;
        long cpf;
        int opcao;

        if (index < bilhete.length) {
            showMessageDialog(null, "INSIRA OS DADOS DO USUÁRIO\n");
            nome = showInputDialog("Nome:\n");
//            nome += "1. Voltar\n";

            cpf = parseLong(showInputDialog("CPF:\n"));
//            cpf += parseLong(showInputDialog("1. Voltar"));

            perfil = showInputDialog("Estudante, Professor ou Comum");
            while (!perfil.equalsIgnoreCase("estudante") && !perfil.equalsIgnoreCase("professor") && !perfil.equalsIgnoreCase("comum")) {
                showMessageDialog(null, "Perfil inválido.");
                perfil = showInputDialog("Estudante, Professor ou Comum");
            }

            bilhete[index] = new BilheteUnico(nome, cpf, perfil);
            index++;
        } else {
            showMessageDialog(null, "Procure um posto de atendimento");
        }
    }

    private void listarBilhetes() {
        DecimalFormat fM = new DecimalFormat("R$##0.00");
        String aux = "";
        for (int i = 0; i < index; i++) {
            aux += "Nome do usuário: " + bilhete[i].usuario.nome + "\n";
            aux += "CPF: " + bilhete[i].usuario.cpf + "\n";
            aux += "Perfil: " + bilhete[i].usuario.perfil + "\n";
            aux += "Número do bilhete: " + bilhete[i].numero + "\n";
            aux += "Saldo do bilhete: " + fM.format(bilhete[i].saldo) + "\n\n";
        }
        showMessageDialog(null, aux);
    }
}
