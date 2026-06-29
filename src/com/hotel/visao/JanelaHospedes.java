package src.com.hotel.visao;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import src.com.hotel.modelo.Entidade_1;
import src.com.hotel.modelo.HospedeNormal;
import src.com.hotel.modelo.HospedePagante;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;


public class JanelaHospedes extends JFrame implements ActionListener {

    private EntidadeDAO <HospedePagante> daoPagante;
    private EntidadeDAO <HospedeNormal> daoNormal;
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
    daoPagante = GaranteDAO.getDAO(HospedePagante.class);
    daoNormal = GaranteDAO.getDAO(HospedeNormal.class);

    try {
      daoPagante.recuperar();
      daoNormal.recuperar();
    } catch (Exception e) {
      e.printStackTrace();
    }

    modeloTabela = new DefaultTableModel();
    modeloTabela.addColumn("ID");
    modeloTabela.addColumn("Tipo");
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
    configuraCoresTabela(tabela);
    tabela.setFont(fonte);
    tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(100); //
    //tabela.getColumnModel().getColumn(1).setMinWidth(0); //Oculta a informação do tipo de hóspede na tabela visual
    //tabela.getColumnModel().getColumn(1).setMaxWidth(0); //
    tabela.getColumnModel().getColumn(2).setPreferredWidth(300);
    tabela.getColumnModel().getColumn(3).setPreferredWidth(200);
    tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
    tabela.getColumnModel().getColumn(5).setPreferredWidth(200);
    tabela.getColumnModel().getColumn(6).setPreferredWidth(300);
    tabela.getColumnModel().getColumn(7).setPreferredWidth(250);
    tabela.getColumnModel().getColumn(8).setPreferredWidth(150);
    tabela.getColumnModel().getColumn(9).setPreferredWidth(100);

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
      for (HospedePagante hp : daoPagante.carregarTodos()) {

          modeloTabela.addRow(new Object[]{
              hp.getId(),
              "Pagante",
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
      //e.printStackTrace();
    }

    try {
        for (HospedeNormal hn : daoNormal.carregarTodos()) {
        modeloTabela.addRow(new Object[]{
            hn.getId(),
            "Normal",
            hn.getNome(),
            hn.getCpf(),
            hn.getIdade()
        });

      }
    } catch (PersistenceException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String erro = "";

    if (e.getSource() == btCadastrar) {
        //getContentPane().setLayout(new BorderLayout());
        JPCadastroHospedes painel = new JPCadastroHospedes();
        JPHospedeComum phc = new JPHospedeComum();
        
        addListenerCadastrar(painel, daoPagante);
        addListenerCadastrar(phc, daoNormal);

        String[] opcoes = {"Hóspede Pagante", "Hóspede Normal"};
        String escolha = (String) JOptionPane.showInputDialog(
          this,
          "Selecione o tipo de hóspede que deseja cadastrar:",
          "Cadastro",
          JOptionPane.QUESTION_MESSAGE,
          null,
          opcoes,
          opcoes[0]
        );

        if (escolha.equals("Hóspede Pagante")) {
          abrirPainelCadastro(painel);
        } else if (escolha.equals ("Hóspede Normal")) {
          abrirJPHospedeComum(phc);
        } else {
          //...
        }


    } else if (e.getSource() == btEditar) {
      JPCadastroHospedes painel = new JPCadastroHospedes();
      JPHospedeComum phc = new JPHospedeComum();

        int linha = tabela.getSelectedRow();
        if (linha == -1) return;
        final int idAntigo = (int) modeloTabela.getValueAt(linha, 0);
        int id = (int) modeloTabela.getValueAt(linha, 0);
        try {
            String tipo = (String) modeloTabela.getValueAt(linha, 1);
            if (tipo.equals("Pagante")) {
              HospedePagante h = daoPagante.carregar(id);
              painel.preencherCampos(h);
              abrirPainelCadastro(painel);
            } else {
              HospedeNormal hn = daoNormal.carregar(id);
              phc.preencherCampos(hn);
              abrirJPHospedeComum(phc);
            }
        } catch (PersistenceException pe) {
          pe.printStackTrace();
        }

        addOutrosListeners(painel, idAntigo, daoPagante);
        addOutrosListeners(phc, idAntigo, daoNormal);

    } else if (e.getSource() == btApagar) {
        int linha = tabela.getSelectedRow();
        if (linha == -1) return;

        int id = (int) modeloTabela.getValueAt(linha, 0);
      
        try {
            String tipo = (String) modeloTabela.getValueAt(linha, 1);
            modeloTabela.removeRow(linha);
            if (tipo.equals("Pagante")) {
              daoPagante.apagar(id);
              daoPagante.persistir();
            } else {
              daoNormal.apagar(id);
              daoNormal.persistir();
            }
        } catch (PersistenceException f) {
          f.printStackTrace();
        } catch (IOException f1) {
          f1.printStackTrace();
        }

    } else if (e.getSource() == btBuscar) {
      JPCadastroHospedes painel = new JPCadastroHospedes();
      JPHospedeComum phc = new JPHospedeComum();

      String entrada = JOptionPane.showInputDialog(this, "Digite o ID do hóspede"); 

      if (entrada != null) {
        try {
          int id = Integer.parseInt(entrada);
          final int idAntigo = id;
          int numLinhasTabela = modeloTabela.getRowCount();
          String tipo = "";
          //Percorre a tabela buscando o ID. Quando acha, descobre o tipo de Hóspede (Pagante ou Normal)
          for (int i=0; i<numLinhasTabela; i++) {
            if ((int)modeloTabela.getValueAt(i, 0) == id) {
              tipo = (String) modeloTabela.getValueAt(i, 1);
              break;
            }
          }
          if (tipo.equals("Pagante")) {
            HospedePagante hp = daoPagante.carregar(id);
            painel.preencherCampos(hp);
            abrirPainelCadastro(painel);
          } else {
            HospedeNormal hn = daoNormal.carregar(id);
            phc.preencherCampos(hn);
            abrirJPHospedeComum(phc);
          }

          addOutrosListeners(painel, idAntigo, daoPagante);
          addOutrosListeners(phc, idAntigo, daoNormal);

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

  private void abrirJPHospedeComum (JPHospedeComum phc) {
    getContentPane().removeAll();
    add(phc);
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

  private <T extends Entidade_1<?>, P extends PainelCadastro<T>> void addListenerCadastrar (P painel, EntidadeDAO<T> dao) {
    painel.addSalvarListener(ev -> {
          //Esse bloco só salva as informações de hospede na persistencia
          try {
              T hospede = painel.construirEntidade();
              if (idJaExiste(hospede.getId(), -1)) {
                JOptionPane.showMessageDialog(this, "Já existe um hóspede com esse ID.");
                return;
              }
              dao.salvar(hospede);
              dao.persistir();
          } catch (Exception e2) {
              e2.printStackTrace();
          }
          voltarParaLista();
        });

        painel.addCancelarListener(ev -> {
            voltarParaLista();
        });
  }

  private <T extends Entidade_1<?>, P extends PainelCadastro<T>> void addOutrosListeners (P painel, int idAntigo, EntidadeDAO<T> dao) {
    painel.addSalvarListener(ev -> {
      T entidade = null; 
      try {
          entidade = painel.construirEntidade();
          if (idJaExiste(entidade.getId(), idAntigo)) {
            JOptionPane.showMessageDialog(this, "Já existe um hóspede com esse ID.");
            return;
          }
          dao.atualizar(entidade);
          dao.persistir();
      } catch (PersistenceException e2) {
          if ("atualizar".equals(e2.getOperacao())) {
            try {
              dao.apagar(idAntigo);
              dao.salvar(entidade);
              dao.persistir();
            } catch (PersistenceException | IOException e3) {
              e3.printStackTrace();
            }
          }
      } catch (IOException e4) {
        e4.printStackTrace();
      }
      voltarParaLista();
    });
    painel.addCancelarListener (ev -> {
      voltarParaLista();
    });
  }

  public void configuraCoresTabela (JTable tabela) {
    tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent (
        JTable tb, Object valor, boolean selecionado, boolean hasFocus, int linha, int coluna) {
          Component c = super.getTableCellRendererComponent(tb, valor, selecionado, hasFocus, linha, coluna);
          if (!selecionado) {
            Object tipo = tb.getValueAt(linha, 1); //Pega o tipo da coluna 1 (oculta)
            if ("Normal".equals(tipo)) {
              c.setBackground(new Color(220, 240, 255));
            } else {
              c.setBackground(Color.yellow);
            }
          } 
          return c;
        }
      });
  }

  private boolean idJaExiste (int id, int idAtualIgnorar) {
    if (id == idAtualIgnorar) return false;
    try {
        daoPagante.carregar(id);
        return true;
    } catch (PersistenceException e) {}
    try {
        daoNormal.carregar(id);
        return true;
    } catch (PersistenceException e) {}
    return false;
  }
}
