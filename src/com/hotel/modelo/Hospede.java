package src.com.hotel.modelo;

import java.util.ArrayList;
import java.util.List;
import src.com.hotel.visao.BancoDeDados;

public abstract class Hospede extends Entidade {
    
    protected String nome;
    protected int idade;
    protected String cpf;
    
    public Hospede (String nome, int idade, String cpf, int id){
       
        super(id);
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        
    }
    // --------------------------------- MÉTODOS SOBRESCRITOS DE ENTIDADE --------------------------------- //
    @Override
    
    public abstract boolean salvar();
    public abstract boolean atualizar();
    public abstract boolean carregar(int id);
    public abstract boolean apagar(int id);
    public abstract List<?> carregarTodos();

    
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
        return BancoDeDados.hospedes;
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
