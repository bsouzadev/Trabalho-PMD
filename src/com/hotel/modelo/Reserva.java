package src.com.hotel.modelo;

import java.util.ArrayList;
import java.util.List;
import src.com.hotel.visao.BancoDeDados;

public class Reserva extends Entidade { //classe de transação
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

    // ------------------------------- SOBRESCRITAS ------------------------------ //
    @Override
    public boolean salvar() {
        if (this.persistido) {
            return false;
        }
        if (this.info_reserva.size() < 1) {
            System.out.println ("Para fazer uma reserva, você deve antes adicionar as informações da reserva!");
            return false;
        }
        BancoDeDados.reservas.add(this);
        this.persistido = true;
        return true;
    }

    @Override
    public boolean atualizar() {
        if (!this.persistido) {
            return false;
        }
        for (int i=0; i< BancoDeDados.reservas.size(); i++) {
            if (BancoDeDados.reservas.get(i).id == this.id) {
                BancoDeDados.reservas.set(i, this);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean carregar(int id) {
        for (int i=0; i<BancoDeDados.reservas.size(); i++) {
            Reserva reserva = BancoDeDados.reservas.get(i);
            if (reserva.id == id) {
                this.id = id;
                this.hospede = reserva.hospede;
                this.info_reserva = new ArrayList<>(reserva.info_reserva);
                this.persistido = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean apagar(int id) {
        for (int i=0; i<BancoDeDados.reservas.size(); i++) {
            if (BancoDeDados.reservas.get(i).id == id) {
                BancoDeDados.reservas.get(i).persistido = false;
                BancoDeDados.reservas.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Reserva> carregarTodos() {
        return new ArrayList<>(BancoDeDados.reservas);
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
