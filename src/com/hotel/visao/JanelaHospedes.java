package src.com.hotel.visao;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import src.com.hotel.modelo.HospedePagante;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;


public class JanelaHospedes extends JFrame implements ActionListener {

    private EntidadeDAO <HospedePagante> dao;
    private JTextField tfNome, tfCpf, tfIdade;
    private JButton btCadastrar, btEditar, btApagar, btBuscar;
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JanelaHospedes(){
    setTitle("Cadastro de Hóspedes");
    setSize(800, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new GridBagLayout());
    dao = GaranteDAO.getDAO(HospedePagante.class);

    try {
      dao.recuperar();
    } catch (Exception e) {
      e.printStackTrace();
    }

    modeloTabela = new DefaultTableModel();
    modeloTabela.addColumn("ID");
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

    btBuscar = new JButton("Buscar");
    btBuscar.setFont(fonte);
    btBuscar.addActionListener(this);
    gbc.gridx = 6;
    gbc.gridy = 2;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 1;
    add(btBuscar, gbc);



    // linha 1
    tabela = new JTable(modeloTabela);
    tabela.setAutoCreateRowSorter(true);
    tabela.setFont(fonte);
    tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(10);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
    tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
    tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
    tabela.getColumnModel().getColumn(4).setPreferredWidth(200);
    tabela.getColumnModel().getColumn(5).setPreferredWidth(300);
    tabela.getColumnModel().getColumn(6).setPreferredWidth(250);
    tabela.getColumnModel().getColumn(7).setPreferredWidth(150);
    tabela.getColumnModel().getColumn(8).setPreferredWidth(100);

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

    try {
      modeloTabela.setRowCount(0);
      for (HospedePagante hp : dao.carregarTodos()) {

          modeloTabela.addRow(new Object[]{
              hp.getId(),
              hp.getNome(),
              hp.getCpf(),
              hp.getIdade(),
              hp.getTelefone(),
              hp.getEmail(),
              hp.getNumeroCartao(),
              hp.getDataVencimento(),
              hp.getCvv()
          });
      }
    }catch (PersistenceException e) {
      //vazio
  }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String erro = "";

    if (e.getSource() == btCadastrar) {
        //getContentPane().setLayout(new BorderLayout());
        JPCadastroHospedes painel = new JPCadastroHospedes();
        
        painel.addSalvarListener(ev -> {
          //Esse bloco só salva as informações de hospede na persistencia
          try {
              HospedePagante hospedeP = constroiHospedePagante(painel);
              dao.salvar(hospedeP);
              dao.persistir();
          } catch (Exception e2) {
              e2.printStackTrace();
          }

          voltarParaLista();

        });

        painel.addCancelarListener(ev -> {
            voltarParaLista();
        });

        //Assim que clicamos no "Cadastrar", executa o código abaixo
        abrirPainelCadastro(painel);

    } else if (e.getSource() == btEditar) {
      JPCadastroHospedes painel = new JPCadastroHospedes();

        int linha = tabela.getSelectedRow();
        if (linha == -1) return;
        final int idAntigo = (int) modeloTabela.getValueAt(linha, 0);
        int id = (int) modeloTabela.getValueAt(linha, 0);
        try {
            HospedePagante h = dao.carregar(id);
            painel.preencherCampos(h);
            
            abrirPainelCadastro(painel);

        } catch (PersistenceException pe) {
          pe.printStackTrace();
        }

        AdicionarListeners(painel, idAntigo);

    } else if (e.getSource() == btApagar) {
        int linha = tabela.getSelectedRow();
        if (linha == -1) return;

        int id = (int) modeloTabela.getValueAt(linha, 0);
        modeloTabela.removeRow(linha);

        try {
            dao.apagar(id);
            dao.persistir();
        } catch (PersistenceException f) {
          f.printStackTrace();
        } catch (IOException f1) {
          f1.printStackTrace();
        }

    } else if (e.getSource() == btBuscar) {
      JPCadastroHospedes painel = new JPCadastroHospedes();
      String entrada = JOptionPane.showInputDialog(this, "Digite o ID do hóspede"); 

      if (entrada != null) {
        try {
          int id = Integer.parseInt(entrada);
          final int idAntigo = id;
          HospedePagante hp = dao.carregar(id);
          painel.preencherCampos(hp);
          abrirPainelCadastro(painel);

          AdicionarListeners(painel, idAntigo);

        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(this, "O ID deve ser um número");
        } catch (PersistenceException pe) {
          JOptionPane.showMessageDialog(this, "Não existe hóspede com esse ID");
        }
      }

    }

  }

  //MÉTODOS USADOS NO OVERRIDE ACIMA

  private void voltarParaLista() {
    getContentPane().removeAll();
    getContentPane().setLayout(new GridBagLayout());
    montaLista();
    revalidate();
    repaint();
  }

  private void abrirPainelCadastro (JPCadastroHospedes painel) {
    getContentPane().removeAll();
    add(painel);
    revalidate();
    repaint();
  }

  private HospedePagante constroiHospedePagante (JPCadastroHospedes painel) {
    return new HospedePagante (
      painel.getNumCartao(),
      Integer.parseInt(painel.getCvv()),
      painel.getVencimento(),
      painel.getTelefone(),
      painel.getEmail(),
      painel.getNome(),
      Integer.parseInt(painel.getIdade()),
      painel.getCpf(),
      painel.getId()
    );
  }

  private void AdicionarListeners (JPCadastroHospedes painel, int idAntigo) {
    painel.addSalvarListener(ev -> {
      HospedePagante hospedeP = null;
      try {
          hospedeP = constroiHospedePagante(painel);
          dao.atualizar(hospedeP);
          dao.persistir();
      } catch (PersistenceException e2) {
          if (e2.getOperacao().compareTo("atualizar") == 0) {
            try {
              dao.apagar(idAntigo);
              dao.salvar(hospedeP);
              dao.persistir();
            } catch (PersistenceException e3) {
              e3.printStackTrace();
            } catch (IOException e4) {
              e4.printStackTrace();
            }
          }
      } catch (IOException e5) {
        e5.printStackTrace();
      }
      voltarParaLista();

    });

    painel.addCancelarListener(ev -> {
        voltarParaLista();
    });
  }


}
