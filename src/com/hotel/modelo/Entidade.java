package src.com.hotel.modelo;

import java.util.List;

public abstract class Entidade {
    protected int id; // identificador
    protected boolean persistido; // informa se o objeto já foi salvo anteriormente.

    //
    public Entidade() {
        this.id = 0;
        this.persistido = false;
    }

    public Entidade(int id) {
        this.id = id;
        this.persistido = false;
    }

    //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // METODOS ABSTRATOS - NECESSÁRIO SOBRESCRITA
    
    public abstract boolean salvar();
    public abstract boolean atualizar();
    public abstract boolean carregar(int id);
    public abstract boolean apagar(int id);
    public abstract List<?> carregarTodos();


    @Override
    public String toString() {
        return "Id = " + getId();
    }
}