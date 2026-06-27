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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class JanelaHospedes extends JFrame implements ActionListener{

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
    var fonte = new Font("ARIAL", Font.BOLD, 20);
    var gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;

    //linha 0
    var labelNome = new JLabel("Nome:");
    labelNome.setFont(fonte);
    gbc.gridx = 0;      //coluna inicial
    gbc.gridy = 0;      //linha inicial
    gbc.weightx = 0;    //o quanto a coluna expande - só precisa ajustar um componente em cada coluna, os demais ficam com 0
    gbc.weighty = 0;    //o quanto a linha expande - só precisa ajustar um componente em cada linha, os demais ficam com 0
    gbc.gridwidth = 1;  //quantas colunas vai ocupar (use gridheight se quiser ocupar mais de uma linha). A soma de todos os componentes na mesma linha/coluna define o total de colunas/linhas.
    add(labelNome, gbc);

    tfNome = new JTextField();
    tfNome.setFont(fonte);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0.0;
    gbc.weighty = 0;
    gbc.gridwidth = 7;
    add(tfNome, gbc);

    //linha 1
    //CPF
    var labelCpf = new JLabel("CPF:");
    labelCpf.setFont(fonte);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 1;
    add(labelCpf, gbc);

    tfCpf = new JTextField();
    tfCpf.setFont(fonte);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 0.0;
    gbc.weighty = 0;
    gbc.gridwidth = 4;
    add(tfCpf, gbc);

    //IDADE
    var labelIdade = new JLabel("Idade:");
    labelIdade.setFont(fonte);
    gbc.gridx = 5;
    gbc.gridy = 1;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridwidth = 1;
    add (labelIdade, gbc);
    
    tfIdade = new JTextField();
    tfIdade.setFont(fonte);
    gbc.gridx = 6;
    gbc.gridy = 1;
    gbc.weightx = 0.0;
    gbc.weighty = 0;
    gbc.gridwidth = 2;
    add(tfIdade, gbc);

    //linha 2
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

    // linha 3
    modeloTabela = new DefaultTableModel();
    modeloTabela.addColumn("Nome");
    modeloTabela.addColumn("CPF");
    modeloTabela.addColumn("Idade");

    tabela = new JTable(modeloTabela);
    tabela.setFont(fonte);
    tabela.getColumnModel().getColumn(0).setMaxWidth(600);
    tabela.getColumnModel().getColumn(1).setMaxWidth(200);
    tabela.getColumnModel().getColumn(2).setMaxWidth(200);

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

  @Override
  public void actionPerformed(ActionEvent e) {
    String erro = "";

    if (e.getSource() == btCadastrar) {
          erro = validarCampos();
          if (erro == null) {
            modeloTabela.addRow(new Object[] {
            tfNome.getText(),
            tfCpf.getText(),
            tfIdade.getText()
            });
          } else {
            JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
          }

    } else if (e.getSource() == btEditar) {
        erro = validarCampos();
      for (int i = 0; i < tabela.getRowCount(); i++)
        if (tabela.isRowSelected(i)) {
            if (erro == null) {
                modeloTabela.setValueAt(tfNome.getText(), i, 0);
                modeloTabela.setValueAt(tfCpf.getText().replaceAll("\\D", ""), i, 1);
                modeloTabela.setValueAt(tfIdade.getText(), i, 2);
                break;
            } else {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

    } else if (e.getSource() == btApagar) {
      for (int i = 0; i < tabela.getRowCount(); i++)
        if (tabela.isRowSelected(i))
          modeloTabela.removeRow(i);

    } else if (e.getSource() == btCancelar) {
      tfNome.setText("");
      tfCpf.setText("");
      tfIdade.setText("");
      tabela.clearSelection();
    }
  }


}
