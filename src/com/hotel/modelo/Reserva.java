package src.com.hotel.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Reserva extends Entidade_1<Reserva> implements Serializable { //classe de transação
    protected Hospede hospede;
    protected ArrayList<InformacoesReserva> info_reserva = new ArrayList<>();

    // ------------------------------- CONSTRUTOR ---------------------------- //--

    public Reserva (Hospede hospede, int id) {
        super(id);
        this.hospede = hospede;
    }

    // -----------------------MÉTODOS DA INTERMEDIÁRIA------------------------ //--

    public void adicionarInfoReserva (InformacoesReserva info) {
        info_reserva.add(info);
    }

    public boolean removerInfoReserva (int id) {
        for (int i=0; i<info_reserva.size(); i++) {
            if (info_reserva.get(i).getQuarto().getId() == id) {
                info_reserva.remove(i);
                return true;
            }
        }
        return false;
    }

    public void listarInfoReserva () {
        for (InformacoesReserva i : info_reserva) {
            System.out.println(i);
        }
    }

    // -------------------------------- GETTERS -------------------------------- //

    public Hospede getHospede () {
        return this.hospede;
    }

    public ArrayList<InformacoesReserva> getInfoReserva () {
        return new ArrayList<>(info_reserva);
    }

    // -------------------------------- SETTERS -------------------------------- //

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }
    
    public void setInfoReserva (ArrayList<InformacoesReserva> info_reserva) {
        this.info_reserva = info_reserva;
    }
    // -------------------------------- ToString -------------------------------- //

    @Override
    public String toString () {
        String str = "ID da reserva: " + id + "\nHospede: " + hospede.getNome() + "\nReservas feitas:\n";
        for (InformacoesReserva info : info_reserva) {
            str+=info.toString() + "\n";
        }
        str+="\n";
        return str;
    }
}
