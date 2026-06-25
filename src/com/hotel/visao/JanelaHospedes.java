package src.com.hotel.visao;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class JanelaHospedes extends JFrame {

    public JanelaHospedes(){
    setTitle("Cadastro de Hóspedes");
    setSize(800, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    add(new JLabel("Tela de hóspedes"));
    setLocationRelativeTo(null);
    }
}
