import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner e = new Scanner (System.in);
        String nome = "", perfil = "", consulta;
        long cpf = 0;
        double quantia;

        System.out.print("Digite o nome do usuário: ");
        nome = e.next();
        System.out.print("Digite o cpf do usuário: ");
        cpf = e.nextLong();
        System.out.print("Digite o tipo de usuário (normal, estudante, professor):");
        perfil = e.next();


        BilheteUnico b = new BilheteUnico(nome, cpf, perfil);

        System.out.print("Digite a quantia que deseja carregar: ");
        quantia = e.nextDouble();

        System.out.print("Deseja consultar o saldo do bilhete (S/N): ");
        consulta = e.next();
        if (consulta.equalsIgnoreCase("S")) {
            System.out.println(b.consultarSaldo());
        }
    }
}
