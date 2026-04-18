package src.com.hotel.modelo;

import java.util.List;

public abstract class Entidade_opcional {
    protected int id; // identificador
    protected boolean persistido; // informa se o objeto já foi salvo anteriormente.

    //
    public Entidade_opcional() {
        this.id = 0;
    }

    public Entidade_opcional(int id) {
        this.id = id;
    }

    //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // METODOS ABSTRATOS - NECESSÁRIO SOBREESCRITA
    
    public abstract boolean salvar();
    public abstract boolean atualizar();
    public abstract boolean carregar();
    public abstract boolean apagar(int id);
    public abstract List<?> carregarTodos();
       
    
    
    @Override
    public String toString() {

        return "Id = " + id;
    }
}
