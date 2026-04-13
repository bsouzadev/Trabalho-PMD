package src.com.hotel.modelo;

public class Entidade {
    private int id; // identificador
    private boolean persistido; // informa se o objeto já foi salvo anteriormente.

    //
    public Entidade() {

    }

    public Entidade(int id) {
        this.id = id;
    }

    //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //
    public boolean salvar(){
        if(persistido){
            return false;
        }


        persistido = true;
        return true;
    }
    
    //
    @Override
    public String toString() {

        return "Id = " + id;
    }

}
