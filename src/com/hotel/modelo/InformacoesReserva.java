package src.com.hotel.modelo;

public class InformacoesReserva {
    protected Quarto quarto;
    protected int dias;

    public InformacoesReserva (Quarto quarto, int dias) {
        this.quarto = quarto;
        this.dias = dias;
    }

    public Quarto getQuarto () {
        return this.quarto;
    }

    public int getDias () {
        return this.dias;
    }

    public double valorTotal () {
        return this.dias * quarto.getPreco();
    }

    @Override
    public String toString() {
        return "Quarto: " + quarto.getNumero_quarto() + ", Dias: " + dias + ", Total: R$" + valorTotal(); 
    }
}