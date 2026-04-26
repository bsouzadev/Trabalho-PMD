package src.com.hotel.modelo;

import java.util.Scanner;

import src.com.hotel.visao.BancoDeDados;

public class Menu {
    private static Scanner sc = new Scanner(System.in);

    // ---------------- MENU PRINCIPAL ---------------- //
    public static void MenuPrincipal() {
        int opcao;

        while (opcao != 0) {
            System.out.println("\n----- SISTEMA HOTEL -----");
            System.out.println("0 - Sair");
            System.out.println("1 - Hospede");
            System.out.println("2 - Quarto");
            System.out.println("3 - Reserva");
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
            System.out.println("0 - Voltar");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");

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
        String nome = sc.next();

        System.out.print("Idade: ");
        int idade = sc.nextInt();

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
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("0 - Voltar");

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






}