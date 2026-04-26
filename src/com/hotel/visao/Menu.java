package src.com.hotel.visao;

import java.util.List;
import java.util.Scanner;
import src.com.hotel.modelo.*;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private int opMenu;

    // ---------------- MENU PRINCIPAL ---------------- //
    public static void MenuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n----- SISTEMA HOTEL -----");
            System.out.println("Digite 0 para: Sair");
            System.out.println("Digite 1 para: Se Hospedar");
            System.out.println("Digite 2 para: Quarto"); //O que seria quarto !?
            System.out.println("Digite 3 para: Fazer uma reserva");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    MenuHospede();
                    break;
                case 2:
                    MenuQuarto();
                    break;
                case 3:
                    menuReserva();
                    break;
            }
        }
    }


    // ---------------- MENU HOSPEDE ---------------- //

    public static void MenuHospede() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- HOSPEDE ---");
            System.out.println("Digite 0 para: Voltar");
            System.out.println("Digite 1 para: Inserir");
            System.out.println("Digite 2 para: Listar");
            System.out.print ("Escolha: ");

            opcao = sc.nextInt();

            if (opcao == 1) {
                inserirHospede();
            } else if (opcao == 2) {
                listarHospedes();
            }
        }
    }
    private static void inserirHospede() {
        System.out.print("Nome: ");
        sc.nextLine();
        String nome = sc.nextLine();

        System.out.print("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.next();

        System.out.print("ID: ");
        int id = sc.nextInt();

        Hospede h = new HospedeNormal(nome, idade, cpf, id);

        if (h.salvar()) {
            System.out.println("Hospede salvo!");
        } else {
            System.out.println("Erro ao salvar (X).");
        }
    }

    /*private static void listarHospedes() {
        Hospede h = new HospedeNormal("", 0, "", 0);

        for (Hospede hospede : h.carregarTodos()) {
            System.out.println(hospede);
        }
    }*/
    
    private static void listarHospedes() {
        for (Hospede hospede : BancoDeDados.hospedes) {
            System.out.println(hospede);
        }
}


    // ---------------- MENU QUARTO ---------------- //

    public static void MenuQuarto() {
        int opcao;

        do {
            System.out.println("\n=== QUARTO ===");
            System.out.println("Digite 1 para: - Inserir");
            System.out.println("Digite 2 para: - Listar");
            System.out.println("Digite 0 para: - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    inserirQuarto();
                    break;
                case 2:
                    listarQuartos();
                    break;
            }

        } while (opcao != 0);
    }

    private static void inserirQuarto() {
        System.out.print("Numero: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.print("Qualidade: ");
        String qualidade = sc.next();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        System.out.print("ID: ");
        int id = sc.nextInt();

        Quarto q = new Quarto(numero, qualidade, preco, id);

        if (q.salvar()) {
            System.out.println("Quarto salvo!");
        } else {
            System.out.println("Erro ao salvar.");
        }
    }

    private static void listarQuartos() {
        Quarto q = new Quarto(0, "", 0, 0);

        for (Quarto quarto : q.carregarTodos()) {
            System.out.println(quarto);
        }
    }


    // ---------------- MENU RESERVA ---------------- //

    public static void menuReserva(){
        int opMenu;

        while (true){
            System.out.println("==== RESERVA ====");
            System.out.println("Digite 1 para: Criar reserva");
            System.out.println("Digite 2 para: Ver todas as reservas");
            System.out.println("Digite 3 para: Buscar por uma reserva");
            System.out.println("Digite 4 para: Apagar uma reserva");
            System.out.println("Digite 0 para: Sair");

            opMenu = sc.nextInt();

            if(opMenu == 1){
                reserva();
            } else if(opMenu == 2){
                listarReservas();
            } else if(opMenu == 3){
                buscarReserva(sc);
            } else if(opMenu == 4){
                apagaReserva(sc);
            } else {
                break;
            }

        }
    }

    public static void reserva(){
        System.out.println("Digite o seu nome:");
        sc.nextLine();
        String nome = sc.nextLine();
        System.out.println("Digite sua idade:");
        int idade = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite seu CPF:");
        String cpf = sc.nextLine();
        System.out.println("Digite o id:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o numero do cartao:");
        String numCartao = sc.nextLine();
        System.out.println("Digite o Cvv");
        int cvv = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite a Data de vencimento:");
        String vencimento = sc.nextLine();
        System.out.println("Digite o seu Telefone:");
        String telefone = sc.nextLine();
        System.out.println("Digite o seu Email:");
        String email = sc.nextLine();
        HospedePagante hospede = new HospedePagante(numCartao, cvv, vencimento, telefone, email, nome, idade,cpf,id);
        System.out.println("Por favor, informe a data de chegada e a data de saída desejadas");
        Reserva reserva = new Reserva(hospede, sc.nextLine(), sc.nextLine(), id);
        reserva.salvar();
    }

    public static void listarReservas() {
        Reserva r = new Reserva(null, "", "", 0);

        List<Reserva> lista = r.carregarTodos();

        for (Reserva reserva : lista) {
            System.out.println(reserva);
        }
    }

    public static void buscarReserva(Scanner sc) {
        System.out.print("ID: ");
        int id = sc.nextInt();

        Reserva r = new Reserva(null, "", "", 0);

        if (r.carregar(id)) {
            System.out.println(r);
        } else {
            System.out.println("Não encontrado.");
        }
    }

    public static void apagaReserva(Scanner sc){
        System.out.println("Id: ");
        int id = sc.nextInt();

        Reserva r = new Reserva(null, "", "", 0);

        if(r.apagar(id)){
            System.out.println(r);
        } else {
            System.out.println("Não encontrado.");
        }
    }

}