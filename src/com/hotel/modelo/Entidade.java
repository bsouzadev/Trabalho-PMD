package src.com.hotel.modelo;

import java.util.List;
import src.com.hotel.visao.BancoDeDados;

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

    //
    public boolean salvar(){ //salva na persistência = classe banco de dados.
        if(persistido){
            return false;
        }

        BancoDeDados.SalvaEntidades.add(this);

        persistido = true;
        return true;
    }

    public boolean atualizar(){ //pega os dados que já estão dentro desse objeto, e atualiza na persistência.
        if(!persistido) return false;

        for (int i = 0; i < BancoDeDados.SalvaEntidades.size(); i++){
            if(BancoDeDados.SalvaEntidades.get(i).getId() == this.id){
                BancoDeDados.SalvaEntidades.set(i, this);
                return true;
            }
        }

        return false; //não encontrou o id na lista (o id possivelmente não existe).
    }

    public boolean apagar(int id){
        for (int i = 0; i < BancoDeDados.SalvaEntidades.size(); i++) {
            if(BancoDeDados.SalvaEntidades.get(i).getId() == id){
                BancoDeDados.SalvaEntidades.remove(i);
                persistido = false;
                return true;
            }
        }

        return false; //não encontrou o id na lista (o id possivelmente não existe).
    }

    public boolean carregar(int id){
        for (int i = 0; i < BancoDeDados.SalvaEntidades.size(); i++) {
            if(BancoDeDados.SalvaEntidades.get(i).getId() == id){
                System.out.println(BancoDeDados.SalvaEntidades.get(i));
                return true;
            }
        }

        return false; //não encontrou o id na lista (o id possivelmente não existe).
    }

    public abstract List<?> carregarTodos(); //Retorna uma lista com todos os registros daquela entidade armazenados na persistência. Se não houver nenhum, a lista retornada será vazia.

    //
    @Override
    public String toString() {
        return "Id = " + getId();
    }
}