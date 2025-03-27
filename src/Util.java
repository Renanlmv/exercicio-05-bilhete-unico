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
            switch (opcao) {
                case 1:
                    menuAdm();
                    break;
                case 2:
                    menuUsuario();
            }
        } while (opcao != 3);
    }


    // ------------------------------MENU ADMINISTRADOR------------------------------

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
            switch (opcao) {
                case 1:
                    emitirBilhete();
                    break;
                case 2:
                    listarBilhetes();
                    break;
                case 3:
                    excluirBilhete();
                    break;
            }
        } while (opcao != 4);
    }

    // método para cadastrar um bilhete
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

    // método para mostrar os bilhetes cadastrados
    private void listarBilhetes() {
        DecimalFormat fM = new DecimalFormat("R$##0.00");
        String aux = "";
        if (index == 0) {
            showMessageDialog(null, "Nenhum bilhete único cadastrado!");
        } else {
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

    private void excluirBilhete() {
        int resposta;
        int indice = pesquisar();
        if (indice != -1) {
            resposta = showConfirmDialog(null, "Tem certeza que deseja excluir?");
            if (resposta == YES_OPTION) {
                showMessageDialog(null, "Bilhete " + bilhete[indice].numero + " removido com sucesso");
                bilhete[indice] = bilhete[index - 1];
                index--;
            }
        }
    }


    // ------------------------------MENU USUÁRIO------------------------------------

    // menu do usuário
    private void menuUsuario() {
        int opcao;

        String menuUsuario = "MENU USUÁRIO\n1. Carregar o bilhete\n2. Consultar saldo\n3. Passar na catraca\n4. Sair";

        do {
            opcao = parseInt(showInputDialog(menuUsuario));
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválida");
            }
            switch (opcao) {
                case 1:
                    carregarBilhete();
                    break;
                case 2:
                    consultarSaldo();
                    break;
                case 3:
                    passarNaCatraca();
                    break;
            }
        } while (opcao != 4);
    }

    // método para carregar o bilhete
    private void carregarBilhete() {
        int indice = pesquisar();
        double valor;
        if (indice != -1) {
            valor = parseDouble(showInputDialog("Digite a quantia:\n"));
            bilhete[indice].carregarBilhete(valor);
        }
    }

    // método para ver quanto tem de saldo
    private void consultarSaldo() {
        DecimalFormat fM = new DecimalFormat("R$##0.00");
        int indice = pesquisar();
        if (indice != -1) {
            showMessageDialog(null, "Saldo: " + fM.format(bilhete[indice].consultarSaldo()));
        }
    }

    // método para validar a passagem na catraca
    private void passarNaCatraca() {
        int indice = pesquisar();
        if (indice != -1) {
            showMessageDialog(null, bilhete[indice].passagemCatraca());
        }
    }

    // método para procurar um bilhete válido
    private int pesquisar() {
        long cpf;
        cpf = parseLong(showInputDialog("Digite o CPF:\n"));
        for (int i = 0; i < index; i++) {
            if (cpf == bilhete[i].usuario.cpf) {
                return i;
            }
        }
        showMessageDialog(null, cpf + " não encontrado");
        return -1;
    }
}
