package src.com.hotel.visao;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import src.com.hotel.modelo.Hospede;
import src.com.hotel.modelo.HospedeNormal;
import src.com.hotel.modelo.HospedePagante;
import src.com.hotel.modelo.InformacoesReserva;
import src.com.hotel.modelo.Quarto;
import src.com.hotel.modelo.Reserva;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;


public class JPCadastroReservas extends JPanel implements PainelCadastro<Reserva>{

    private JTextField tfDataEntrada = new JTextField(10);
    private JTextField tfDataSaida = new JTextField(10);
    private JTextField tfId = new JTextField(10);

    private EntidadeDAO <HospedePagante> daoPagante;
    private EntidadeDAO <HospedeNormal> daoNormal;
    private EntidadeDAO <Quarto> daoQuarto;
    private EntidadeDAO <Reserva> daoReserva;

    private List <ActionListener> salvarListeners = new ArrayList<>();
    private ActionListener cancelarListener;
    private int idAntigo = -1;
    
    private JButton btnSalvar, btnCancelar, btnAdicionarQuarto, btnRemoverQuarto;
    private JComboBox <Hospede> cbHospede;
    private JComboBox <Quarto> cbQuarto;
    private Font fonte = new Font("ARIAL", Font.BOLD, 20);

    private List<InformacoesReserva> infoAcumulada = new ArrayList<>();
    private DefaultTableModel modeloQuartos;
    private JTable tabelaQuartos;

    public JPCadastroReservas () {
        setLayout(new BorderLayout());
        btnSalvar = new JButton ("Salvar");
        btnCancelar = new JButton ("Cancelar");
        btnAdicionarQuarto = new JButton("Adicionar Quarto");
        btnRemoverQuarto = new JButton ("Remover Quarto");
        daoPagante = GaranteDAO.getDAO(HospedePagante.class);
        daoNormal = GaranteDAO.getDAO(HospedeNormal.class);
        daoQuarto = GaranteDAO.getDAO(Quarto.class);
        daoReserva = GaranteDAO.getDAO(Reserva.class);
        try {
            daoNormal.recuperar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            daoPagante.recuperar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            daoQuarto.recuperar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        add (cabecalho(), BorderLayout.NORTH);
        add (corpo (), BorderLayout.CENTER);
        add (barraInferior(), BorderLayout.SOUTH);

        btnAdicionarQuarto.addActionListener(ev -> adicionarQuarto());
        btnRemoverQuarto.addActionListener(ev -> removerQuarto());

        btnSalvar.addActionListener(ev -> {
            String erro = trataCamposReserva(idAntigo);
            if (erro != null) {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            salvarListeners.forEach(l -> l.actionPerformed(ev));
        });

        btnCancelar.addActionListener(ev -> {
            cancelarListener.actionPerformed(ev);
        });
    }

    private void adicionarQuarto() {
        Quarto quarto = (Quarto) cbQuarto.getSelectedItem();
        String entrada = tfDataEntrada.getText();
        String saida = tfDataSaida.getText();

        // valida datas antes de adicionar
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEntrada=null, dataSaida=null;
        String erro = trataCamposQuarto(entrada, saida);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro);
            return;
        }

        dataEntrada = LocalDate.parse(entrada, fmt);
        dataSaida = LocalDate.parse(saida, fmt);
        InformacoesReserva info = new InformacoesReserva(quarto, dataEntrada, dataSaida);
        infoAcumulada.add(info);
        modeloQuartos.addRow(new Object[]{
            quarto.getNumero_quarto(),
            entrada,
            saida,
            info.valorTotal()
        });

        // limpa campos para próxima entrada
        tfDataEntrada.setText("");
        tfDataSaida.setText("");
        cbQuarto.setSelectedIndex(-1);
    }

    private void removerQuarto() {
        int linha = tabelaQuartos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um quarto para remover.");
            return;
        }
        infoAcumulada.remove(linha);
        modeloQuartos.removeRow(linha);
    }

    public JPanel cabecalho () {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JLabel headerText = new JLabel("CADASTRO DE RESERVA");
        header.setBorder (BorderFactory.createEmptyBorder(30, 0, 15, 0));
        headerText.setFont(new Font("ARIAL", Font.BOLD, 20));
        headerText.setForeground(Color.black);
        
        header.add (headerText);
        return header;
    }

    public JPanel corpo() {
        JPanel body = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Campo ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        body.add(new JLabel("ID da Reserva:"), gbc);
        gbc.gridx = 1;
        body.add(tfId, gbc);

        // Campo Hóspede
        cbHospede = new JComboBox<>();
        cbHospede.setFont(fonte);
        try {
            for (HospedePagante hp : daoPagante.carregarTodos()) {
                cbHospede.addItem(hp);
            }
            for (HospedeNormal hn : daoNormal.carregarTodos()) {
                cbHospede.addItem(hn);
            }
        } catch (PersistenceException e) {}
        cbHospede.setSelectedIndex(-1); //isso é o que vai possibilitar que verifiquemos se o usuário selecionou alguma opção ou não, posteriormente

        gbc.gridx = 0;
        gbc.gridy = 1;
        body.add(new JLabel("Hóspede:"), gbc);
        gbc.gridx = 1;
        body.add(cbHospede, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        body.add(new JLabel("Adicionar quarto à reserva"), gbc);

        // Campo Quarto
        cbQuarto = new JComboBox<>();
        cbQuarto.setFont(fonte);
        try {
            for (Quarto q : daoQuarto.carregarTodos()) {
                cbQuarto.addItem(q);
            }
        } catch (PersistenceException e) {}
        cbQuarto.setSelectedIndex(-1);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        body.add(new JLabel("Quarto:"), gbc);
        gbc.gridx = 1;
        body.add(cbQuarto, gbc);

        // Datas
        gbc.gridx = 0;
        gbc.gridy = 5;
        body.add(new JLabel("Data Entrada (dd/mm/aaaa):"), gbc);
        gbc.gridx = 1;
        body.add(tfDataEntrada, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        body.add(new JLabel("Data Saída (dd/mm/aaaa):"), gbc);
        gbc.gridx = 1;
        body.add(tfDataSaida, gbc);

        // Botões adicionar/remover
        JPanel botoesQuarto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botoesQuarto.add(btnAdicionarQuarto);
        botoesQuarto.add(btnRemoverQuarto);
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        body.add(botoesQuarto, gbc);

        // Tabela de quartos acumulados
        String[] colunas = {"Quarto", "Entrada", "Saída", "Valor"};
        modeloQuartos = new DefaultTableModel(colunas, 0);
        tabelaQuartos = new JTable(modeloQuartos);
        tabelaQuartos.setFont(fonte);
        JScrollPane scroll = new JScrollPane(tabelaQuartos);
        scroll.setPreferredSize(new Dimension(0, 150));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        body.add(scroll, gbc);

        return body;
    }
    

    public JPanel barraInferior () {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        barra.add(btnCancelar);
        barra.add(btnSalvar);
        return barra;
    }

    //CHECKPOINT

    public String trataCamposQuarto (String entrada, String saida) {
        // valida datas antes de adicionar
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEntrada=null, dataSaida=null;
        try {
            dataEntrada = LocalDate.parse(entrada, fmt);
            dataSaida = LocalDate.parse(saida, fmt);
            if (!dataSaida.isAfter(dataEntrada)) return "Data de saída deve ser após a entrada.";
            if (dataEntrada.isBefore(LocalDate.now())) return "Data de entrada deve ser posterior à data atual.";
        } catch (Exception e) {
            return "Data em formato inválido. Use dd/MM/yyyy.";
        }

        if (cbQuarto.getSelectedItem() == null) {
            return "Selecione um quarto.";
        }
        if (cbHospede.getSelectedItem() == null ) {
            return "Selecione um hóspede.";
        }
        return null;
    }

    public String trataCamposReserva (int idAtual) {
        //Trata id
        int id = getId();
        if (id == idAtual) return null;
        if (id < 0) return "O ID deve ser um número natural.";

        try {
            daoReserva.carregar(id);
            return "O ID já existe no conjunto!";
        } catch (PersistenceException e) {
        }

        return null;
    }

    //GETTERS
    public Hospede getHospede () {return (Hospede) cbHospede.getSelectedItem();}
    public String getDataEntrada () {return tfDataEntrada.getText();}
    public String getDataSaida () {return tfDataSaida.getText();}
    public Quarto getQuarto () {return (Quarto)cbQuarto.getSelectedItem();}
    public int getId () {return Integer.parseInt(tfId.getText());}

    //Sobreescrita dos métodos da interface
    @Override
    public void preencherCampos(Reserva r) {
        tfId.setText(String.valueOf(r.getId()));
        idAntigo = r.getId();
        cbHospede.setSelectedItem(r.getHospede());

        //Carrega os quartos já existentes na tabela
        infoAcumulada.clear();
        modeloQuartos.setRowCount(0);
        for (InformacoesReserva info : r.getInfoReserva()) {
            infoAcumulada.add(info);
            modeloQuartos.addRow(new Object [] {
                info.getQuarto().getNumero_quarto(),
                info.getData_entrada(),
                info.getData_saida(),
                info.valorTotal()
            });
        }
    }

    @Override
    public Reserva construirEntidade() {
        Reserva reserva = new Reserva (getHospede(), getId());
        for (InformacoesReserva ir : infoAcumulada) {
            reserva.adicionarInfoReserva(ir);
        }
        return reserva;
    }

    @Override
    public void addSalvarListener(ActionListener l) { salvarListeners.add(l); }

    @Override
    public void addCancelarListener(ActionListener al) { cancelarListener = al; }

}