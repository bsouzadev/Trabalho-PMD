package src.com.hotel.visao;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JanelaReservas extends JFrame {
    
    public JanelaReservas(){

    setTitle("Cadastro de Reservas");
    setSize(800, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    add(new JLabel("Tela de hóspedes"));
    setLocationRelativeTo(null);

    }
}
