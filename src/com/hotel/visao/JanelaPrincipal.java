package src.com.hotel.visao;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class JanelaPrincipal extends JFrame implements ActionListener {

    private JButton btHospedes, btQuartos, btReservas;
    
    public JanelaPrincipal (){
        setTitle("Sistema Hotelaria");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        
        Font fonte = new Font("ARIAL", Font.BOLD, 20);

        
        JPanel centro = new JPanel(new GridBagLayout());

        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.insets = new Insets(0, 0, 30, 0);

        JLabel titulo = new JLabel("MENU PRINCIPAL");
        titulo.setFont(new Font("ARIAL", Font.BOLD, 32));

        centro.add(titulo, gbcTitulo);

        JPanel pBotoes = new JPanel();
        pBotoes.setLayout(new BoxLayout(pBotoes, BoxLayout.Y_AXIS));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(40, 0, 0, 0);

        centro.add(pBotoes, gbc);
        add(centro, BorderLayout.CENTER);

        Dimension tamanho = new Dimension(200, 70);

        btHospedes = new JButton("Hóspedes");
        btHospedes.setPreferredSize(tamanho);
        btHospedes.setMaximumSize(tamanho); //pra conseguir colocar os botões do tamanho que a gente for querer, podem mudar
        btHospedes.setMinimumSize(tamanho);
        btHospedes.setAlignmentX(Component.CENTER_ALIGNMENT); // alinhados no centro
        btHospedes.setFont(fonte);
        pBotoes.add(btHospedes);
        pBotoes.add(Box.createVerticalStrut(20)); // coloca espaço entre os botoes
        btHospedes.addActionListener(this);

        btQuartos = new JButton("Quartos");
        btQuartos.setPreferredSize(tamanho);
        btQuartos.setMaximumSize(tamanho);
        btQuartos.setMinimumSize(tamanho);
        btQuartos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btQuartos.setFont(fonte);
        pBotoes.add(btQuartos);
        pBotoes.add(Box.createVerticalStrut(20));
        btQuartos.addActionListener(this);

        btReservas = new JButton("Reservas");
        btReservas.setPreferredSize(tamanho);
        btReservas.setMaximumSize(tamanho);
        btReservas.setMinimumSize(tamanho);
        btReservas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btReservas.setFont(fonte);
        pBotoes.add(btReservas);
        btReservas.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btHospedes) {
            new JanelaHospedes().setVisible(true);
        }else if (e.getSource() == btQuartos){
            new JanelaQuartos().setVisible(true);
        }else if (e.getSource() == btReservas){
            new JanelaReservas().setVisible(true);
        }
        

       
      
    }
    

    


    public static void main(String[] args) {
        new JanelaPrincipal().setVisible(true);
        
  }


    
}
