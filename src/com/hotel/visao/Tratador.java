package src.com.hotel.visao;

import java.time.LocalDate;

public class Tratador {

    JPCadastroHospedes jp;

    public Tratador (JPCadastroHospedes jp) {
        this.jp = jp;
    }

    //Métodos de validação
    public String validarCampos () {

        //Trata nome
        if (jp.getNome().trim().isEmpty()) {
            return "O nome é obrigatório.";
        }

        //Trata CPF
        if (jp.getCpf().trim().isEmpty()) {
            return "O CPF é obrigatório.";
        }
        if (!validaCPF()) {
            return "CPF inválido!";
        }
        
        //Trata Idade
        if (jp.getIdade().trim().isEmpty()) {
            return "A idade é obrigatória.";
        }
        try {
            int idade = Integer.parseInt(jp.getIdade());
            if (idade < 0) return "Idade inválida";
        } catch (NumberFormatException e) {
            return "A idade deve ser um número.";
        }
        
        //Trata ID
        try {
            int id = jp.getId();
            if (id < 0) return "O ID deve ser um número natural.";
        } catch (NumberFormatException e) {
            return "O ID deve ser um número natural.";
        }

        //Trata número do cartão
        if (!jp.getNumCartao().matches("[0-9 ]+")) {
            return "Número do cartão inválido. Use apenas espaço para separar os dígitos.";
}

        //Trata número de telefone
        String telefone = jp.getTelefone();
        int quantidade = telefone.replaceAll("\\D", "").length(); // o \\D pega todos os que não são dígitos da string
        if (quantidade < 10 || quantidade > 11) return "O número deve possuir DDD e ter 10 ou 11 dígitos.";

        //Trata E-mail
        boolean emailCorreto = trataEmail();
        if (!emailCorreto) return "E-mail inválido!";

        //Trata Vencimento do cartão
        String erro = trataVencimento();
        if (erro != null) return erro;

        //Trata CVV
        try {
            int cvv = Integer.parseInt(jp.getCvv());
            if (jp.getCvv().length() != 3 && jp.getCvv().length() != 4) return "CVV inválido";
        } catch (Exception e){
            return "CVV inválido.";
        }

        return null;

    }

    private String trataVencimento() {
        String data = jp.getVencimento();
        int tam = data.length();
        int mes, ano, contaBarra=0;
        LocalDate dataAtual = LocalDate.now();
        int anoAtual = dataAtual.getYear();
        int mesAtual = dataAtual.getMonthValue();

        for (int i=0; i<tam; i++) {
            char c = data.charAt(i);
            if (c == '/') contaBarra++;
        }
        if (contaBarra != 1) return "Vencimento inválido. Deve ser MM/AAAA";
        String[] partes = data.split("/");

        mes = Integer.parseInt(partes[0]);
        ano = Integer.parseInt(partes[1]);
        
        if (mes < 1 || mes > 12) return "Mês inexistente na data de vencimento";
        if (ano < anoAtual || (ano == anoAtual && mes < mesAtual)) return "Cartão vencido.";
        return null;

    }

    private boolean trataEmail () {
        String email = jp.getEmail();
        int tam = email.length();
        boolean arroba=false;
        for (int i=0; i<tam; i++) {
            char c = email.charAt(i);
            if (c == '@') arroba = true;
            if (c == '.' && arroba == true) return true; 
        }
        return false;
    }

    private boolean validaCPF () {
        String texto = jp.getCpf().trim(), cpf = "";
        boolean digitosIguais = true;
        int calculo10=0, calculo11=0;

        int tamCpf, tamTexto = texto.length();
        for (int i=0; i<tamTexto; i++) {
            char c = texto.charAt(i);
            if (c >= '0' && c <= '9') cpf += c;
        }
        
        tamCpf = cpf.length();
        if (tamCpf != 11) return false;
        calculo10 += 10*(cpf.charAt(0) - '0');
        calculo11 += 11*(cpf.charAt(0) - '0');
        for (int i=1; i<tamCpf; i++) {
            char c = cpf.charAt(i);
            if (c != cpf.charAt(i-1)) digitosIguais = false;
            if (i < tamCpf-2) {
                calculo10 += (10-i) * (c - '0');
                calculo11 += (11-i) * (c - '0');
            }
        }
        
        if (digitosIguais) return false;
        calculo10%=11;
        calculo10 = (calculo10 == 0 || calculo10 == 1) ? 0 : 11-calculo10;
        calculo11 += calculo10*2;
        calculo11%=11;
        calculo11 = (calculo11 == 0 || calculo11 == 1) ? 0 : 11-calculo11;

        return (calculo10 == (cpf.charAt(9) - '0') && calculo11 == (cpf.charAt(10) - '0'));

    }
}
