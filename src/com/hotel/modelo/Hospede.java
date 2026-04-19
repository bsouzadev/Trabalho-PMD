package src.com.hotel.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Hospede extends Entidade_opcional {
    
    protected String nome;
    protected int idade;
    protected String cpf;
    protected static ArrayList<Hospede> banco = new ArrayList<>();
    
    public Hospede (String nome, int idade, String cpf, int id){
       
        super(id);
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        
    }
    // --------------------------------- MÉTODOS SOBRESCRITOS DE ENTIDADE --------------------------------- //
    @Override
    public boolean salvar(){

        if(this.persistido){
            return false;
        }
        
        banco.add(this);
        this.persistido = true;
        return true;

    }

    @Override
    public boolean atualizar(){

        if(!this.persistido){
            return false;
        }
        
        for(int i = 0; i < banco.size(); i++){
            if (banco.get(i).id == this.id){
                banco.set(i, this);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean apagar(int id){
     for (Hospede h : banco) {
            if (h.id == id) {
                banco.remove(h);
                this.persistido = false;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean carregar(int id){
        for (Hospede h : banco) {
            if (h.id == id) {
                this.nome = h.nome;
                this.idade = h.idade;
                this.cpf = h.cpf;
                this.id = h.id;
                this.persistido = true;
                return true;
            }
        }
        return false;

    }

    @Override
    public List<Hospede> carregarTodos() {
        
        return banco;

    }



    
    // --------------------------------- GETTERS DE HOSPEDE --------------------------------- //
    
    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Hospede> getBanco() {
        return banco;
    }

    // --------------------------------- SETTERS DE HOSPEDE --------------------------------- //
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // --------------------------------- ABSTRATA (VERIFICA PAGANTE) --------------------------------- //

    public abstract boolean Pagante();
    
    // --------------------------------- TOSTRING DE HOSPEDE --------------------------------- //

    @Override
    public String toString() {

        return "Nome: "+nome+", Idade: "+idade+", CPF: "+cpf;

    }
}
