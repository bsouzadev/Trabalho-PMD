package src.com.hotel.visao;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import src.com.hotel.modelo.Quarto;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;



public class JanelaQuartos extends JFrame implements ActionListener {

    private JTextField tfnm, tfvl, tfql, tfid;
    private JButton btsv, btal, btrm, btcn;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private EntidadeDAO<Quarto> dao;


    public JanelaQuartos(){
    
    setTitle("Cadastro de Quartos");
    setSize(800, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new GridBagLayout());

    dao = GaranteDAO.getDAO(Quarto.class);

    try{
        dao.recuperar();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
    var fonte = new Font("ARIAL", Font.BOLD, 20);

    var gbc = new GridBagConstraints();       //define posição e tamanho dos componentes
    gbc.fill = GridBagConstraints.HORIZONTAL; //os componentes devem ocupar toda a largura da célula

    // linha 0
    var lbid = new JLabel("ID:");
    lbid.setFont(fonte);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 1;
    add(lbid, gbc);

    tfid = new JTextField();
    tfid.setFont(fonte);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 7;
    add(tfid, gbc);

    // linha 1
    var lbnm = new JLabel("Número do Quarto:");
    lbnm.setFont(fonte);
    gbc.gridx = 0;      //coluna inicial
    gbc.gridy = 1;      //linha inicial
    gbc.weightx = 0;    //o quanto a coluna expande - só precisa ajustar um componente em cada coluna, os demais ficam com 0
    gbc.weighty = 0;    //o quanto a linha expande - só precisa ajustar um componente em cada linha, os demais ficam com 0
    gbc.gridwidth = 1;  //quantas colunas vai ocupar (use gridheight se quiser ocupar mais de uma linha). A soma de todos os componentes na mesma linha/coluna define o total de colunas/linhas.
    add(lbnm, gbc);

    tfnm = new JTextField(); 
    tfnm.setFont(fonte);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 0.0;
    gbc.weighty = 0;
    gbc.gridwidth = 7;
    add(tfnm, gbc);

    // linha 2
    var lbvl = new JLabel("Valor:");
    lbvl.setFont(fonte);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0.0;
    gbc.weighty = 0;
    gbc.gridwidth = 1;
    add(lbvl, gbc);

    tfvl = new JTextField();
    tfvl.setFont(fonte);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 7;
    add(tfvl, gbc);

    // linha 3
    var lbql = new JLabel("Qualidade:");
    lbql.setFont(fonte);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 1;
    add(lbql, gbc);

    tfql = new JTextField();
    tfql.setFont(fonte);

    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 7;
    add(tfql, gbc);


    // linha 4
    btsv = new JButton("Cadastrar");
    btsv.setFont(fonte);
    btsv.addActionListener(this);
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(btsv, gbc);

    btal = new JButton("Editar");
    btal.setFont(fonte);
    btal.addActionListener(this);
    gbc.gridx = 2;
    gbc.gridy = 4;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(btal, gbc);

    btrm = new JButton("Apagar");
    btrm.setFont(fonte);
    btrm.addActionListener(this);
    gbc.gridx = 4;
    gbc.gridy = 4;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(btrm, gbc);

    btcn = new JButton("Cancelar");
    btcn.setFont(fonte);
    btcn.addActionListener(this);
    gbc.gridx = 6;
    gbc.gridy = 4;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(btcn, gbc);

    // linha 5
    modeloTabela = new DefaultTableModel();
    modeloTabela.addColumn("ID");
    modeloTabela.addColumn("Número do Quarto");
    modeloTabela.addColumn("Valor");
    modeloTabela.addColumn("Qualidade");

    tabela = new JTable(modeloTabela);
    tabela.setFont(fonte);
    tabela.getColumnModel().getColumn(0).setMaxWidth(200);   
    tabela.getColumnModel().getColumn(1).setMaxWidth(200);  
    tabela.getColumnModel().getColumn(2).setMaxWidth(200);  
    tabela.getColumnModel().getColumn(3).setMaxWidth(200);
    
    var scroll = new JScrollPane();
    scroll.setViewportView(tabela);
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weightx = 0.0;
    gbc.weighty = 1;
    gbc.gridwidth = 8;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(10, 0, 10, 0);//margem acima direita abaixo esquerda
    add(scroll, gbc);
  

    try {

      for (Quarto q : dao.carregarTodos()) {

          modeloTabela.addRow(new Object[]{
              q.getId(),
              q.getNumero_quarto(),
              q.getPreco(),
              q.getQualidade()
          });
      }
    }catch (PersistenceException e) {
      //vazio
  }
      }

    @Override
    public void actionPerformed(ActionEvent e) {

      if (e.getSource() == btsv){

        try {
          Quarto quarto = new Quarto(
            Integer.parseInt(tfnm.getText()),
            tfql.getText(),
            Double.parseDouble(tfvl.getText()),
            Integer.parseInt(tfid.getText())
          );
          dao.salvar(quarto);
          dao.persistir();

          modeloTabela.addRow(new Object[]{
            quarto.getId(),
            quarto.getNumero_quarto(),
            quarto.getPreco(),
            quarto.getQualidade()
          });
        }catch (Exception ex) {
          ex.printStackTrace();
        }
    }else if (e.getSource() == btal) {
      int linha = tabela.getSelectedRow();

      if (linha != -1) {
        try {
          int id = (Integer) modeloTabela.getValueAt(linha, 0);

          Quarto quarto = new Quarto(
            Integer.parseInt(tfnm.getText()),
            tfql.getText(),
            Double.parseDouble(tfvl.getText()),
            id
          );

          dao.atualizar(quarto);
          dao.persistir();

          modeloTabela.setValueAt(quarto.getNumero_quarto(), linha, 1);
          modeloTabela.setValueAt(quarto.getPreco(), linha, 2);
          modeloTabela.setValueAt(quarto.getQualidade(), linha, 3);

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }else if (e.getSource() == btrm) {
      int linha = tabela.getSelectedRow();

      if (linha != -1){

          try {

              int id = (Integer) modeloTabela.getValueAt(linha, 0);
              dao.apagar(id); // primeiro ele apaga do setobjetos
              dao.persistir();  // a partir do que esta dentro de setobjetos, ele salva no .dat
              modeloTabela.removeRow(linha);

          }catch (Exception ex) {
            ex.printStackTrace();
          }
      }
    } else if (e.getSource() == btcn) {
      tfid.setText("");
      tfnm.setText("");
      tfvl.setText("");
      tfql.setText("");
      tabela.clearSelection();
    }
  }


}
