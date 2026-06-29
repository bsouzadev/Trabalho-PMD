package src.com.hotel.visao;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import src.com.hotel.modelo.HospedeNormal;


public class JPHospedeComum extends JPanel implements PainelCadastro <HospedeNormal> {

    private JTextField tfNome = new JTextField(20);
    private JTextField tfCpf = new JTextField(10);
    private JTextField tfIdade = new JTextField(10);
    private JTextField tfId = new JTextField(10);

    private boolean isAcompanhante;
    private int idPagante;

    private List <ActionListener> salvarListeners = new ArrayList<>();
    private ActionListener cancelarListener;
    
    private JButton btnSalvar, btnCancelar;
    
    public JPHospedeComum () {
        setBackground(Color.black);
        setLayout(new BorderLayout());
        btnSalvar = new JButton ("Salvar");
        btnCancelar = new JButton ("Cancelar");
        add (cabecalho(), BorderLayout.NORTH);
        add (corpo (), BorderLayout.CENTER);
        add (barraInferior(), BorderLayout.SOUTH);
        btnSalvar.addActionListener(ev -> { 
            salvarListeners.forEach(l -> l.actionPerformed(ev));
        });

        btnCancelar.addActionListener(ev -> {
            cancelarListener.actionPerformed(ev);
        });
    }

    public JPanel cabecalho () {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JLabel headerText = new JLabel("CADASTRO COMUM");
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

        JPanel campoID = new JPanel();
        campoID.setLayout(new BoxLayout(campoID, BoxLayout.Y_AXIS));
        campoID.setOpaque(false);
        campoID.add(new JLabel("ID"));
        campoID.add (tfId);

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
        gridbc.gridwidth = 3;
        gridbc.weightx = 1;
        dadosPessoais.add(campoNome, gridbc);

        gridbc.gridx=0;
        gridbc.gridy=2;
        gridbc.gridwidth = 1;
        gridbc.weightx = 1;
        dadosPessoais.add(campoCpf, gridbc);

        gridbc.gridx=0;
        gridbc.gridy=3;
        gridbc.gridwidth = 1;
        gridbc.weightx = 1;
        dadosPessoais.add(campoIdade, gridbc);

        gridbc.gridx=0;
        gridbc.gridy=4;
        gridbc.gridwidth = 1;
        gridbc.weightx = 1;
        dadosPessoais.add(campoID, gridbc);

        body.add(dadosPessoais, gbc);

        return body;
    }

    public JPanel barraInferior () {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        barra.add(btnCancelar);
        barra.add(btnSalvar);
        return barra;
    }

    //GETTERS
    public String getNome () {return tfNome.getText();}
    public String getCpf () {return tfCpf.getText();}
    public String getIdade () {return tfIdade.getText();}
    public int getId () {return Integer.parseInt(tfId.getText());}

    //Sobreescrita dos métodos da interface
    @Override
    public void preencherCampos(HospedeNormal h) {
        tfId.setText(String.valueOf(h.getId()));
        tfNome.setText(h.getNome());
        tfCpf.setText(h.getCpf());
        tfIdade.setText(String.valueOf(h.getIdade()));
    }

    @Override
    public HospedeNormal construirEntidade() {
        return new HospedeNormal (getNome(), Integer.parseInt(getIdade()), getCpf(), getId());
    }

    @Override
    public void addSalvarListener(ActionListener l) {
        salvarListeners.add(l);
    }

    @Override
    public void addCancelarListener (ActionListener al) {
        cancelarListener = al;
    }

    // void preencherCampos (T entidade);
    // T construirEntidade();
    // void addSalvarListener(ActionListener al);
    // void addCancelarListener(ActionListener al);

}