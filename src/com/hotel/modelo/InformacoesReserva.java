package src.com.hotel.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class InformacoesReserva {
    protected Quarto quarto;
    protected LocalDate data_entrada;
    protected LocalDate data_saida;

    public InformacoesReserva (Quarto quarto, LocalDate data_entrada, LocalDate data_saida) {
        this.quarto = quarto;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
    }

    public Quarto getQuarto () {
        return this.quarto;
    }

    public LocalDate getData_entrada () {
        return data_entrada;
    }
    
    public LocalDate getData_saida() {
        return data_saida;
    }

    public long getDias () {
        return ChronoUnit.DAYS.between(data_entrada, data_saida);
    }

    public void setQuarto (Quarto quarto) {
        this.quarto = quarto;
    }

    public void setData_entrada(LocalDate data_entrada){ 
        this.data_entrada = data_entrada;
    }
    
    public void setData_saida (LocalDate data_saida) {
        this.data_saida = data_saida;
    }

    public double valorTotal () {
        return getDias() * quarto.getPreco();
    }

    @Override
    public String toString() {
        DateTimeFormatter padrao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String str = "Quarto: " + quarto.getNumero_quarto() + "\nPeríodo de estadia: " + data_entrada.format(padrao) + " - " + data_saida.format(padrao) + "\n";
        str += "Tempo de estadia: " + getDias() + "\nValor total: R$" + valorTotal();
        return str; 
    }
}