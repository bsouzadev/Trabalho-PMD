package src.com.hotel.visao;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class JPCadastroHospedes extends JPanel {

    private JTextField tfNome = new JTextField(20);
    private JTextField tfCpf = new JTextField(10);
    private JTextField tfIdade = new JTextField(10);
    private JTextField tfTelefone = new JTextField(10);
    private JTextField tfEmail = new JTextField(10);
    private JTextField tfNumCartao = new JTextField(10);
    private JTextField tfVencimento = new JTextField(10);
    private JTextField tfCvv = new JTextField(10);

    private List <ActionListener> salvarListeners = new ArrayList<>();
    private ActionListener cancelarListener;
    
    private JButton btnSalvar, btnCancelar;
    
    public JPCadastroHospedes () {
        setBackground(Color.black);
        setLayout(new BorderLayout());
        btnSalvar = new JButton ("Salvar");
        btnCancelar = new JButton ("Cancelar");
        add (cabecalho(), BorderLayout.NORTH);
        add (corpo (), BorderLayout.CENTER);
        add (barraInferior(), BorderLayout.SOUTH);
        btnSalvar.addActionListener(ev -> { 
            String erro = validarCampos();
            if (erro != null) {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            salvarListeners.forEach(l -> l.actionPerformed(ev)); //Para cada instrução recebida de JanelaHospedes, 
        });

        btnCancelar.addActionListener(ev -> {
            cancelarListener.actionPerformed(ev);
        });
    }

    public void addSalvarListener(ActionListener l) {
        salvarListeners.add(l);
    }

    public void addCancelarListener (ActionListener al) {
        cancelarListener = al;
    }

    public JPanel cabecalho () {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JLabel headerText = new JLabel("CADASTRO");
        header.setBorder (BorderFactory.createEmptyBorder(30, 0, 15, 0));
        headerText.setFont(new Font("ARIAL", Font.BOLD, 20));
        headerText.setForeground(Color.black);
        
        header.add (headerText);
        return header;
    }

    public JPanel corpo () {
        JPanel body = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        //CAMPOS
        JPanel campoNome = new JPanel();
        campoNome.setLayout(new BoxLayout(campoNome, BoxLayout.Y_AXIS));
        campoNome.setOpaque(false);
        campoNome.add(new JLabel ("Nome completo"));
        campoNome.add(tfNome);

        JPanel campoCpf = new JPanel ();
        campoCpf.setLayout(new BoxLayout(campoCpf, BoxLayout.Y_AXIS));
        campoCpf.setOpaque(false);
        campoCpf.add (new JLabel("CPF"));
        campoCpf.add (tfCpf);

        JPanel campoIdade = new JPanel ();
        campoIdade.setLayout(new BoxLayout(campoIdade, BoxLayout.Y_AXIS));
        campoIdade.setOpaque(false);
        campoIdade.add (new JLabel("Idade"));
        campoIdade.add (tfIdade);

        JPanel campoTelefone = new JPanel ();
        campoTelefone.setLayout(new BoxLayout(campoTelefone, BoxLayout.Y_AXIS));
        campoTelefone.setOpaque(false);
        campoTelefone.add (new JLabel("Telefone"));
        campoTelefone.add (tfTelefone);

        JPanel campoEmail = new JPanel ();
        campoEmail.setLayout(new BoxLayout(campoEmail, BoxLayout.Y_AXIS));
        campoEmail.setOpaque(false);
        campoEmail.add (new JLabel("E-mail"));
        campoEmail.add (tfEmail);

        JPanel campoNumCartao = new JPanel ();
        campoNumCartao.setLayout(new BoxLayout(campoNumCartao, BoxLayout.Y_AXIS));
        campoNumCartao.setOpaque(false);
        campoNumCartao.add (new JLabel("Número do cartão"));
        campoNumCartao.add (tfNumCartao);

        JPanel campoVencimento = new JPanel ();
        campoVencimento.setLayout(new BoxLayout(campoVencimento, BoxLayout.Y_AXIS));
        campoVencimento.setOpaque(false);
        campoVencimento.add (new JLabel("Vencimento"));
        campoVencimento.add (tfVencimento);

        JPanel campoCVV = new JPanel();
        campoCVV.setLayout(new BoxLayout(campoCVV, BoxLayout.Y_AXIS));
        campoCVV.setOpaque(false);
        campoCVV.add (new JLabel("CVV"));
        campoCVV.add (tfCvv);

        //SEÇÕES
        
        //Dados Pessoais
        JPanel dadosPessoais = new JPanel(new GridBagLayout());
        GridBagConstraints gridbc = new GridBagConstraints();
        gridbc.insets = new Insets (4, 4, 4, 4);
        gridbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel ("Dados pessoais");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 15f));
        titulo.setForeground(Color.GRAY);

        gridbc.gridx=0;
        gridbc.gridy=0;
        gridbc.gridwidth = 2;
        gridbc.weightx = 1;
        dadosPessoais.add(titulo, gridbc);

        gridbc.gridx=0;
        gridbc.gridy=1;
        gridbc.gridwidth = 2;
        gridbc.weightx = 1;
        dadosPessoais.add(campoNome, gridbc);

        gridbc.gridx=0;
        gridbc.gridy=2;
        gridbc.gridwidth = 1;
        gridbc.weightx = 0.5;
        dadosPessoais.add(campoCpf, gridbc);

        gridbc.gridx=1;
        gridbc.gridy=2;
        gridbc.gridwidth = 1;
        gridbc.weightx = 0.5;
        dadosPessoais.add(campoIdade, gridbc);

        //Contato
        JPanel contato = new JPanel(new GridBagLayout());
        gridbc.insets = new Insets (4, 4, 4, 4);
        gridbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo2 = new JLabel ("Contato");
        titulo2.setFont(titulo.getFont().deriveFont(Font.BOLD, 15f));
        titulo2.setForeground(Color.GRAY);

        gridbc.gridx=0;
        gridbc.gridy=0;
        gridbc.gridwidth = 2;
        gridbc.weightx = 1;
        contato.add(titulo2, gridbc);

        gridbc.gridx=0;
        gridbc.gridy=1;
        gridbc.gridwidth = 1;
        gridbc.weightx = 0.5;
        contato.add(campoTelefone, gridbc);

        gridbc.gridx=1;
        gridbc.gridy=1;
        gridbc.gridwidth = 1;
        gridbc.weightx = 0.5;
        contato.add(campoEmail, gridbc);

        //Dados do Cartão

        JPanel dadosCartao = new JPanel(new GridBagLayout());
        gridbc.insets = new Insets (4, 4, 4, 4);
        gridbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo3 = new JLabel ("Dados do cartão");
        titulo3.setFont(titulo.getFont().deriveFont(Font.BOLD, 15f));
        titulo3.setForeground(Color.GRAY);

        gridbc.gridx=0;
        gridbc.gridy=0;
        gridbc.gridwidth = 2;
        gridbc.weightx = 1;
        dadosCartao.add(titulo3, gridbc);

        gridbc.gridx=0;
        gridbc.gridy=1;
        gridbc.gridwidth = 2;
        gridbc.weightx = 1;
        dadosCartao.add(campoNumCartao, gridbc);

        gridbc.gridx=0;
        gridbc.gridy=2;
        gridbc.gridwidth = 1;
        gridbc.weightx = 0.5;
        dadosCartao.add(campoVencimento, gridbc);

        gridbc.gridx=1;
        gridbc.gridy=2;
        gridbc.gridwidth = 1;
        gridbc.weightx = 0.5;
        dadosCartao.add(campoCVV, gridbc);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        body.add(dadosPessoais, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        body.add(contato, gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        body.add(dadosCartao, gbc);

        return body;
    }

    public JPanel barraInferior () {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        barra.add(btnCancelar);
        barra.add(btnSalvar);
        return barra;
    }

    //Métodos de validação
    private String validarCampos () {
    
        if (tfNome.getText().trim().isEmpty()) {
            return "O nome é obrigatório.";
        }
        if (tfCpf.getText().trim().isEmpty()) {
            return "O CPF é obrigatório.";
        }
        if (!validaCPF()) {
            return "CPF inválido!";
        }
        if (tfIdade.getText().trim().isEmpty()) {
            return "A idade é obrigatória.";
        }
        try {
            int idade = Integer.parseInt(tfIdade.getText());
            if (idade < 0) return "Idade inválida";
        } catch (NumberFormatException e) {
            return "A idade deve ser um número.";
        }
        try {
            int numCartao = Integer.parseInt(tfNumCartao.getText());
        } catch (NumberFormatException e) {
            return "O número do cartão deve ser um número.";
        }
        return null;

    }

    private boolean validaCPF () {
        String texto = tfCpf.getText().trim(), cpf = "";
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

    //GETTERS
    public String getNome () {return tfNome.getText();}
    public String getCpf () {return tfCpf.getText();}
    public String getIdade () {return tfIdade.getText();}
    public String getTelefone () {return tfTelefone.getText();}
    public String getEmail () {return tfEmail.getText();}
    public String getNumCartao () {return tfNumCartao.getText();}
    public String getVencimento () {return tfVencimento.getText();}
    public String getCvv () {return tfCvv.getText();}


}