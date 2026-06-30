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
import src.com.hotel.modelo.HospedeNormal;
import src.com.hotel.modelo.HospedePagante;
import src.com.hotel.modelo.InformacoesReserva;
import src.com.hotel.modelo.Reserva;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;


public class JanelaReservas extends JFrame implements ActionListener {

    private JTextField tfNome, tfCpf, tfIdade, tfId; //Inutil?
    private JButton btCadastrar, btEditar, btApagar, btBuscar;
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    private EntidadeDAO <Reserva> daoReserva;
    private EntidadeDAO <HospedePagante> daoPagante;
    private EntidadeDAO <HospedeNormal> daoNormal;

    public JanelaReservas(){
        setTitle("Cadastro de Reservas");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        daoReserva = GaranteDAO.getDAO(Reserva.class);
        daoPagante = GaranteDAO.getDAO(HospedePagante.class);
        daoNormal = GaranteDAO.getDAO(HospedeNormal.class);

        try {
        daoReserva.recuperar();
        } catch (Exception e) {
        e.printStackTrace();
        }

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Hóspede");
        modeloTabela.addColumn("Nº Quartos");
        modeloTabela.addColumn("ValorTotal");
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
    // tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    // tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
    // tabela.getColumnModel().getColumn(1).setPreferredWidth(200); 
    // tabela.getColumnModel().getColumn(2).setPreferredWidth(60);
    // tabela.getColumnModel().getColumn(3).setPreferredWidth(100);

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
      for (Reserva r : daoReserva.carregarTodos()) {

        String quartos = "";
        double precoTotal = 0;
        for (InformacoesReserva info : r.getInfoReserva()) {
            if (!quartos.isEmpty()) quartos += ", ";
            quartos += info.getQuarto().getNumero_quarto();
            precoTotal += info.valorTotal();
        }

          modeloTabela.addRow(new Object[]{
              r.getId(),
              r.getHospede().getNome(),
              quartos,
              precoTotal
          });
      }
    }catch (PersistenceException e) {
      //e.printStackTrace();
    }
  }

  //CHECKPOINT

  @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btCadastrar) {
        JPCadastroReservas painel = new JPCadastroReservas();
        painel.addSalvarListener(ev -> {
            try {
                Reserva r = painel.construirEntidade();
                daoReserva.salvar(r);
                daoReserva.persistir();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            voltarParaLista();
        });
        painel.addCancelarListener(ev -> voltarParaLista());
        abrirPainelCadastro(painel);

    } else if (e.getSource() == btEditar) {
        int linha = tabela.getSelectedRow();
        if (linha == -1) return;
        int id = (int) modeloTabela.getValueAt(linha, 0);
        final int idAntigo = id;

        JPCadastroReservas painel = new JPCadastroReservas();
        try {
            Reserva r = daoReserva.carregar(id);
            painel.preencherCampos(r);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }

        painel.addSalvarListener(ev -> {
            try {
                Reserva r = painel.construirEntidade();
                try {
                    daoReserva.atualizar(r);
                } catch (PersistenceException pe) {
                    if ("atualizar".equals(pe.getOperacao())) {
                        daoReserva.apagar(idAntigo);
                        daoReserva.salvar(r);
                    }
                }
                daoReserva.persistir();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            voltarParaLista();
        });
        painel.addCancelarListener(ev -> voltarParaLista());
        abrirPainelCadastro(painel);

    } else if (e.getSource() == btApagar) {
        int linha = tabela.getSelectedRow();
        if (linha == -1) return;
        int id = (int) modeloTabela.getValueAt(linha, 0);
        modeloTabela.removeRow(linha);
        try {
            daoReserva.apagar(id);
            daoReserva.persistir();
        } catch (PersistenceException | IOException ex) {
            ex.printStackTrace();
        }

    } else if (e.getSource() == btBuscar) {
        String entrada = JOptionPane.showInputDialog(this, "Digite o ID da reserva:");
        if (entrada == null) return;
        try {
            int id = Integer.parseInt(entrada);
            Reserva r = daoReserva.carregar(id);
            JPCadastroReservas painel = new JPCadastroReservas();
            painel.preencherCampos(r);
            painel.addCancelarListener(ev -> voltarParaLista());
            abrirPainelCadastro(painel);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "O ID deve ser um número.");
        } catch (PersistenceException pe) {
            JOptionPane.showMessageDialog(this, "Não existe reserva com esse ID.");
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

  private void abrirPainelCadastro (JPCadastroReservas painel) {
    getContentPane().removeAll();
    add(painel);
    revalidate();
    repaint();
  }

  // private <T extends Entidade_1<?>, P extends PainelCadastro<T>> void addListenerCadastrar (P painel, EntidadeDAO<T> dao) {
  //   painel.addSalvarListener(ev -> {
  //         try {
  //             T hospede = painel.construirEntidade();
  //             if (idJaExiste(hospede.getId(), -1)) {
  //               JOptionPane.showMessageDialog(this, "Já existe um hóspede com esse ID.");
  //               return;
  //             }
  //             dao.salvar(hospede);
  //             dao.persistir();
  //         } catch (Exception e2) {
  //             e2.printStackTrace();
  //         }
  //         voltarParaLista();
  //       });

  //       painel.addCancelarListener(ev -> {
  //           voltarParaLista();
  //       });
  // }

  // private <T extends Entidade_1<?>, P extends PainelCadastro<T>> void addOutrosListeners (P painel, int idAntigo, EntidadeDAO<T> dao) {
  //   painel.addSalvarListener(ev -> {
  //     T entidade = null; 
  //     try {
  //         entidade = painel.construirEntidade();
  //         if (idJaExiste(entidade.getId(), idAntigo)) {
  //           JOptionPane.showMessageDialog(this, "Já existe um hóspede com esse ID.");
  //           return;
  //         }
  //         dao.atualizar(entidade);
  //         dao.persistir();
  //     } catch (PersistenceException e2) {
  //         if ("atualizar".equals(e2.getOperacao())) {
  //           try {
  //             dao.apagar(idAntigo);
  //             dao.salvar(entidade);
  //             dao.persistir();
  //           } catch (PersistenceException | IOException e3) {
  //             e3.printStackTrace();
  //           }
  //         }
  //     } catch (IOException e4) {
  //       e4.printStackTrace();
  //     }
  //     voltarParaLista();
  //   });
  //   painel.addCancelarListener (ev -> {
  //     voltarParaLista();
  //   });
  // }
  
}
