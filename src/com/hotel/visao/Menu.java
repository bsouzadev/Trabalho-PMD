package src.com.hotel.visao;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import src.com.hotel.modelo.*;
import src.com.hotel.modelo.Reserva;

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
            System.out.println("Digite 2 para: Apagar");
            System.out.println("Digite 3 para: Atualizar");
            System.out.println("Digite 4 para: Visualizar por ID");
            System.out.println("Digite 5 para: Listar");
            System.out.print ("Escolha: ");

            opcao = sc.nextInt();

            if (opcao == 1) {
                inserirHospede();
            } else if (opcao == 2) {
                apagarHospedes();
            } else if (opcao == 3){
                atualizarHospedes();
            } else if (opcao == 4){
                carregarHospedes();
            } else if (opcao == 5){
                listarHospedes();
            }
        }
    }
   private static void inserirHospede() {
        
        System.out.println("Insira o tipo de Hóspede:");
        System.out.println("Digite 1 para: Hóspede Pagante");
        System.out.println("Digite 2 para: Hóspede Não Pagante");
        
        int escolha = sc.nextInt();

        if(escolha == 1){
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

        System.out.print("Cartão: ");
        int cartao = sc.nextInt();

        System.out.print("Código de Segurança: ");
        int cvv = sc.nextInt();
        sc.nextLine();

        System.out.print("Data de vencimento (YYYY-MM): ");
        int entrada = sc.nextInt();

        System.out.print("Telefone: ");
        int tel = sc.nextInt();
        sc.nextLine();

        System.out.print("Email: ");
        String email = sc.next();

        Hospede h = new HospedePagante(cartao, cvv, entrada, tel, email, nome, idade, cpf, id);

        if (h.salvar()) {
            System.out.println("Hospede salvo!");
        } else {
            System.out.println("Erro ao salvar (X).");
        }

        } else if(escolha == 2){
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
        
    }

    private static void apagarHospedes(){
        
        System.out.println("Digite o ID: ");
        int id = sc.nextInt();

        Hospede h = new HospedeNormal("", 0, "", 0); 

        if (h.apagar(id)) {
            System.out.println("Hóspede Removido com sucesso!");
        } else {
            System.out.println("Hóspede não encontrado.");
        }
    
    }

    private static void atualizarHospedes(){
        
        System.out.print("Digite o ID: ");
        int id = sc.nextInt();

        Hospede h = null;

        for (Hospede hospede : BancoDeDados.hospedes) {
            if (hospede.getId() == id) {
                h = hospede;
                break;
            }
        }

            System.out.print("Nome: ");
            String nome = sc.next();

            System.out.print("Idade: ");
            int idade = sc.nextInt();

            System.out.print("CPF: ");
            String cpf = sc.next();

    if (h instanceof HospedePagante) {
            HospedePagante hp = (HospedePagante) h;

            System.out.print("Cartão: ");
            int cartao = sc.nextInt();

            System.out.print("Código de Segurança: ");
            int cvv = sc.nextInt();

            System.out.print("Data de vencimento (YYYY-MM): ");
            int entrada = sc.nextInt();

            System.out.print("Telefone: ");
            int tel = sc.nextInt();

            System.out.print("Email: ");
            String email = sc.next();

            hp.setNome(nome); hp.setIdade(idade); hp.setCpf(cpf);
            hp.setNumeroCartao(cartao); hp.setCvv(cvv);
            hp.setDataVencimento(entrada); hp.setTelefone(tel);
            hp.setEmail(email);
        

        if (h.atualizar()) {
            System.out.println("Hóspede atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar.");
        }

    } else if (h instanceof HospedeNormal){
        
        h.setNome(nome); h.setIdade(idade); h.setCpf(cpf);

        if (h.atualizar()) {
            System.out.println("Hóspede atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar.");
        }
        
    }
}
    
    private static void carregarHospedes(){
        
        System.out.print("Digite o ID: ");
        int id = sc.nextInt();

        Hospede h;

        h = new HospedePagante(0, 0, 0, 0, "", "", 0, "", 0);

        if (!h.carregar(id)) {
            // se não achou, tenta como Normal
            h = new HospedeNormal("", 0, "", 0);

        if (!h.carregar(id)) {
            System.out.println("Não encontrado.");
            return;
        }
    }

        System.out.println("Hóspede carregado com sucesso!");
        System.out.println(h);
}

    
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
            System.out.println("Digite 0 para: Voltar");
            System.out.println("Digite 1 para: Inserir");
            System.out.println("Digite 2 para: Apagar");
            System.out.println("Digite 3 para: Atualizar");
            System.out.println("Digite 4 para: Visualizar por ID");
            System.out.println("Digite 5 para: Listar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    inserirQuarto();
                    break;
                case 2:
                    apagarQuartos();
                    break;
                case 3:
                    atualizarQuartos();
                    break;
                case 4:
                    carregarQuartos();
                    break;
                case 5:
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

    private static void apagarQuartos(){
        System.out.println("Digite o ID: ");
        int id = sc.nextInt();

        Quarto q = new Quarto(0, "", 0.0, 0); 

        if (q.apagar(id)) {
            System.out.println("Quarto removido com sucesso!");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    
    }
    private static void atualizarQuartos(){
        System.out.println("Digite o ID: ");
        int id = sc.nextInt();

        Quarto q = new Quarto(0, "", 0.0, 0);

        System.out.print("Numero: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.print("Qualidade: ");
        String qualidade = sc.next();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

    if (q.carregar(id)) {

        q.setNumero_quarto(numero);
        q.setQualidade(qualidade);
        q.setPreco(preco);

         if(q.atualizar()) {
            System.out.println("Quarto atualizado com sucesso!");
        } else {
            System.out.println("Quarto não encontrado.");
        }
        }
    }
    private static void carregarQuartos(){
        System.out.println("Digite o ID: ");
        int id = sc.nextInt();
       
        Quarto q = new Quarto(0, "", 0, 0);

        if (q.carregar(id)) {
            System.out.println(q);
        }else{
            System.out.println("Erro ao carregar Quarto");
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
            System.out.println("Digite 5 para: Alterar uma reserva");
            System.out.println("Digite 0 para: Voltar");

            opMenu = sc.nextInt();
            sc.nextLine();

            if(opMenu == 1){
                reserva();
            } else if(opMenu == 2){
                listarReservas();
            } else if(opMenu == 3){
                buscarReserva(sc);
            } else if(opMenu == 4){
                apagaReserva(sc);
            } else if (opMenu == 5) {
                atualizaReserva();
            } else {
                break;
            }

        }
    }

    public static void reserva(){
        
        System.out.println("Digite o seu nome:");
        String nome = sc.nextLine();
        System.out.println("Digite sua idade:");
        int idade = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite seu CPF:");
        String cpf = sc.nextLine();
        System.out.println("Digite o id:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o numero do cartão:");
        int numCartao = sc.nextInt();
        System.out.println("Digite o Cvv");
        int cvv = sc.nextInt();
        sc.nextLine();
        System.out.print("Data de vencimento (YYYY-MM): ");
        int vencimento = sc.nextInt();
        System.out.println("Digite o seu Telefone:");
        int telefone = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o seu Email:");
        String email = sc.nextLine();
        HospedePagante hospede = new HospedePagante(numCartao, cvv, vencimento, telefone, email, nome, idade,cpf,id);
        System.out.println("Por favor, informe a data de chegada e a data de saída desejadas, no formato DD/MM/YYYY");
        LocalDate dtEntrada = formata_localdate(sc.nextLine());
        LocalDate dtSaida = formata_localdate(sc.nextLine());
        Reserva reserva = new Reserva(hospede, dtEntrada, dtSaida, id);
        while (true) {
            System.out.println("-----------------------------");
            System.out.println ("QUARTOS DISPONÍVEIS PARA HOSPEDAGEM:");
            ArrayList<Quarto> disponibilidadeQuartos = quartosDisponiveis(dtEntrada, dtSaida);
            for (Quarto q : disponibilidadeQuartos) {
                System.out.println (q);
            }
            System.out.print("Informe aqui o ID do quarto que deseja se hospedar: ([0] para sair) ");
            int id_quarto = sc.nextInt();
            if (id_quarto == 0) break;
            System.out.print ("Qual sua previsão de estadia em dias? ");
            int dias_estadia = sc.nextInt();
            Quarto quartoEscolhido = new Quarto (0, "", 0, 0);
            if (quartoEscolhido.carregar(id_quarto)) {
                InformacoesReserva info = new InformacoesReserva(quartoEscolhido, dias_estadia);
                reserva.adicionarInfoReserva(info);
                System.out.println ("Reserva ao quarto " + id_quarto + " feita com sucesso!");
                if(!reserva.salvar()) reserva.atualizar();
                System.out.println("""
                Deseja reservar algum outro quarto?\n
                [0] SIM
                [1] NÃO
                """);
                int escolhaFinalizada = sc.nextInt();
                if (escolhaFinalizada==1) break;
            } else {
                System.out.println ("O quarto escolhido não existe! Escolha outro. ([0] para sair)");
            }
    
        }
        reserva.salvar();
    }

    public static void listarReservas() {
        Reserva r = new Reserva(null, null, null, 0);

        List<Reserva> lista = r.carregarTodos();

        for (Reserva reserva : lista) {
            System.out.println(reserva);
        }
    }

    public static void buscarReserva(Scanner sc) {
        System.out.print("Informe o ID para buscar a reserva: ");
        int id = sc.nextInt();

        Reserva r = new Reserva(null, null, null, 0);

        if (r.carregar(id)) {
            System.out.println(r);
        } else {
            System.out.println("Não encontrado.");
        }
    }

    public static void apagaReserva(Scanner sc){
        System.out.println("Id: ");
        int id = sc.nextInt();

        Reserva r = new Reserva(null, null, null, 0);

        if(r.apagar(id)){
            System.out.println(r);
        } else {
            System.out.println("Não encontrado.");
        }
    }

    public static void atualizaReserva(){
        System.out.println("Digite o ID da reserva: ");
        int id = sc.nextInt();
        sc.nextLine();

        Reserva r = new Reserva(null, null, null, 0);

        if (r.carregar(id)) {
            System.out.print("Nova data de entrada: ");
            LocalDate dtEntrada = formata_localdate(sc.nextLine());

            System.out.print("Nova data de saída: ");
            LocalDate dtSaida = formata_localdate(sc.nextLine());

            r.setData_entrada(dtEntrada);
            r.setData_saida(dtSaida);

            if (r.atualizar()) {
                System.out.println("Reserva atualizada com sucesso!");
            } else {
                System.out.println("Erro ao atualizar.");
            }
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }
    public static LocalDate formata_localdate (String data) {
        String partes[] = data.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        return LocalDate.of(ano, mes, dia);
    }

    public static ArrayList<Quarto> quartosDisponiveis (LocalDate dtEntrada, LocalDate dtSaida) {
        ArrayList <Quarto> quartosDisponiveis = new ArrayList<>();

        for (Quarto quarto : BancoDeDados.quartos) {
            boolean disponivel = true;
            for (Reserva r : BancoDeDados.reservas) {
                List <InformacoesReserva> info_tmp = r.getInfoReserva(); 
                for (InformacoesReserva info_r : info_tmp) {
                    if (info_r.getQuarto().getId() == quarto.getId()) {
                        if (dtEntrada.isBefore(r.getData_saida()) && r.getData_entrada().isBefore(dtSaida)) {
                            disponivel = false;
                            break;
                        } 
                    }
                }
                if (!disponivel) break;
            }
            if (disponivel) {
                quartosDisponiveis.add(quarto);
            }
        }
        return quartosDisponiveis;
    }

}