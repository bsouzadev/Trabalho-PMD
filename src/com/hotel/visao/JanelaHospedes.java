package src.com.hotel.visao;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import src.com.hotel.modelo.Hospede;
import src.com.hotel.persistencia.EntidadeDAO;


public class JanelaHospedes extends JFrame implements ActionListener{

    private EntidadeDAO <Hospede> dao;
    private JTextField tfNome, tfCpf, tfIdade;
    private JButton btCadastrar, btEditar, btApagar, btCancelar;
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JanelaHospedes(){
    setTitle("Cadastro de Hóspedes");
    setSize(800, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new GridBagLayout());
    modeloTabela = new DefaultTableModel();
    modeloTabela.addColumn("Nome");
    modeloTabela.addColumn("CPF");
    modeloTabela.addColumn("Idade");
    modeloTabela.addColumn("Telefone");
    modeloTabela.addColumn("E-mail");
    modeloTabela.addColumn("Núm. Cartão");
    modeloTabela.addColumn("Vencimento");
    modeloTabela.addColumn("Cvv");
    montaLista();
  }

  public void montaLista() {
    var fonte = new Font("ARIAL", Font.BOLD, 20);
    var gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;

    //linha 0
    btCadastrar = new JButton("Cadastrar");
    btCadastrar.setFont(fonte);
    btCadastrar.addActionListener(this);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(btCadastrar, gbc);

    btEditar = new JButton("Editar");
    btEditar.setFont(fonte);
    btEditar.addActionListener(this);
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(btEditar, gbc);

    btApagar = new JButton("Apagar");
    btApagar.setFont(fonte);
    btApagar.addActionListener(this);
    gbc.gridx = 4;
    gbc.gridy = 2;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(btApagar, gbc);

    btCancelar = new JButton("Cancelar");
    btCancelar.setFont(fonte);
    btCancelar.addActionListener(this);
    gbc.gridx = 6;
    gbc.gridy = 2;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(btCancelar, gbc);

    // linha 1
    tabela = new JTable(modeloTabela);
    tabela.setFont(fonte);
    tabela.getColumnModel().getColumn(0).setMaxWidth(300);
    tabela.getColumnModel().getColumn(1).setMaxWidth(200);
    tabela.getColumnModel().getColumn(2).setMaxWidth(50);
    tabela.getColumnModel().getColumn(3).setMaxWidth(200);
    tabela.getColumnModel().getColumn(4).setMaxWidth(200);
    tabela.getColumnModel().getColumn(5).setMaxWidth(50);
    tabela.getColumnModel().getColumn(6).setMaxWidth(100);
    tabela.getColumnModel().getColumn(7).setMaxWidth(50);

    var scroll = new JScrollPane();
    scroll.setViewportView(tabela);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 0.0;
    gbc.weighty = 1;
    gbc.gridwidth = 8;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(10, 0, 10, 0);
    add(scroll, gbc);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String erro = "";

    if (e.getSource() == btCadastrar) {
        getContentPane().removeAll();
        //getContentPane().setLayout(new BorderLayout());
        JPCadastroHospedes painel = new JPCadastroHospedes();

        painel.addSalvarListener(ev -> {
            modeloTabela.addRow (new Object[] {
                painel.getNome(),
                painel.getCpf(),
                painel.getIdade(),
                painel.getTelefone(),
                painel.getEmail(),
                painel.getNumCartao(),
                painel.getVencimento(),
                painel.getCvv()
            });
            getContentPane().removeAll();
            getContentPane().setLayout(new GridBagLayout());
            montaLista();
            revalidate();
            repaint();
        });

        painel.addCancelarListener(ev -> {
            getContentPane().removeAll();
            getContentPane().setLayout(new GridBagLayout());
            montaLista();
            revalidate();
            repaint();
        });
        add (painel);
        revalidate();
        repaint();

    } else if (e.getSource() == btEditar) {
      for (int i = 0; i < tabela.getRowCount(); i++)
        if (tabela.isRowSelected(i)) {
                modeloTabela.setValueAt(tfNome.getText(), i, 0);
                modeloTabela.setValueAt(tfCpf.getText().replaceAll("\\D", ""), i, 1);
                modeloTabela.setValueAt(tfIdade.getText(), i, 2);
                break;
        }

    } else if (e.getSource() == btApagar) {
      for (int i = 0; i < tabela.getRowCount(); i++)
        if (tabela.isRowSelected(i))
          modeloTabela.removeRow(i);

    } else if (e.getSource() == btCancelar) {
    //   tfNome.setText("");
    //   tfCpf.setText("");
    //   tfIdade.setText("");
    //   tabela.clearSelection();
    }
  }

}
