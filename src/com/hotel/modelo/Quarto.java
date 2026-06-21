package src.com.hotel.modelo;

import java.io.Serializable;

//import java.util.ArrayList;
//import java.util.List;
//import src.com.hotel.visao.BancoDeDados;

public class Quarto extends Entidade_1<Quarto> implements Serializable {
    protected int numero_quarto;
    protected String qualidade;
    protected double preco;

    public Quarto (int numero_quarto, String qualidade, double preco, int id) {
        super(id);
        this.numero_quarto = numero_quarto;
        this.qualidade = qualidade;
        this.preco = preco;
    }
    
    // --------------------------------- GETTERS --------------------------------- //

    public int getNumero_quarto () {
        return numero_quarto;
    }
    public String getQualidade () {
        return qualidade;
    }
    public double getPreco () {
        return preco;
    }

    // --------------------------------- SETTERS --------------------------------- //

    public void setNumero_quarto (int numero_quarto) {
        this.numero_quarto = numero_quarto;
    }
    public void setQualidade (String qualidade) {
        this.qualidade = qualidade;
    }
    public void setPreco (double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Quarto: " + numero_quarto + ", Qualidade: " + qualidade + ", Valor: R$" + preco;
    }
}
