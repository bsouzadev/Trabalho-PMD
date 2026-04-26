package src.com.hotel.modelo;

import java.util.ArrayList;
import java.util.List;
import src.com.hotel.visao.BancoDeDados;

public class Reserva extends Entidade { //classe de transação
    protected Hospede hospede;
    protected String data_entrada;
    protected String data_saida;
    protected ArrayList<InformacoesReserva> info_reserva = new ArrayList<>();

    // ------------------------------- CONSTRUTOR ------------------------------ //

    public Reserva (Hospede hospede, String data_entrada, String data_saida, int id) {
        super(id);
        this.hospede = hospede;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
    }

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

    // ------------------------------- SOBRESCRITAS ------------------------------ //
    @Override
    public boolean salvar() {
        if (this.persistido) {
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
                this.data_entrada = reserva.data_entrada;
                this.data_saida = reserva.data_saida;
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

    public String getData_entrada() {
        return this.data_entrada;
    }

    public String getData_saida() {
        return this.data_saida;
    }

    public List<InformacoesReserva> getInfoReserva () {
        return new ArrayList<>(info_reserva);
    }

    // -------------------------------- SETTERS -------------------------------- //

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }
    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }
    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }
    public void setInfoReserva (ArrayList<InformacoesReserva> info_reserva) {
        this.info_reserva = info_reserva;
    }
    // -------------------------------- ToString -------------------------------- //

    @Override
    public String toString () {
        String str = "ID da reserva: " + id + "\nHospede: " + hospede.getNome() + "\nData de entrada: " + data_entrada;
        str += "\nData de saída: " + data_saida + "\nItens:\n";
        for (InformacoesReserva info : info_reserva) {
            str+=info.toString() + "\n";
        }

        return str;
    }
}
