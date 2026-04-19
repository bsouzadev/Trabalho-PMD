package src.com.hotel.modelo;

import java.util.ArrayList;
import java.util.List;

public class Quarto extends Entidade_opcional {
    protected int numero_quarto;
    protected String qualidade;
    protected double preco;
    protected static ArrayList<Quarto> banco_quartos = new ArrayList<>();

    public Quarto (int numero_quarto, String qualidade, double preco, int id) {
        super(id);
        this.numero_quarto = numero_quarto;
        this.qualidade = qualidade;
        this.preco = preco;
    }
    // ------------------------------- SOBRESCRITAS ------------------------------ //
    @Override
    public boolean salvar() {
        if (this.persistido) {
            return false;
        }
        banco_quartos.add(this);
        this.persistido = true;
        return true;
    }

    @Override
    public boolean atualizar() {
        if (!this.persistido) {
            return false;
        }
        for (int i=0; i< banco_quartos.size(); i++) {
            if (banco_quartos.get(i).id == this.id) {
                banco_quartos.set(i, this);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean carregar(int id) {
        for (int i=0; i<banco_quartos.size(); i++) {
            Quarto quarto = banco_quartos.get(i);
            if (quarto.id == id) {
                this.id = id;
                this.numero_quarto = quarto.numero_quarto;
                this.qualidade = quarto.qualidade;
                this.preco = quarto.preco;
                this.persistido = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean apagar(int id) {
        for (int i=0; i<banco_quartos.size(); i++) {
            if (banco_quartos.get(i).id == id) {
                banco_quartos.get(i).persistido = false;
                banco_quartos.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Quarto> carregarTodos() {
        return banco_quartos;
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
