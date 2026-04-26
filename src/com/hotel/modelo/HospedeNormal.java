package src.com.hotel.modelo;

import java.util.List;

import src.com.hotel.visao.BancoDeDados;

public class HospedeNormal extends Hospede{

    public HospedeNormal(String nome, int idade, String cpf, int id){
        super(nome, idade, cpf, id);
    }

    // --------------------------------- SOBREESCRITA ENTIDADE ---------------------------------- //


    public boolean salvar(){

        if(this.persistido){
            return false;
        }
        
        BancoDeDados.hospedes.add(this);
        this.persistido = true;
        return true;

    }

    @Override
    public boolean atualizar(){

        if(!this.persistido){
            return false;
        }
        
        for(int i = 0; i < BancoDeDados.hospedes.size(); i++){
            if(BancoDeDados.hospedes.get(i).id == this.id){
                BancoDeDados.hospedes.set(i, this);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean apagar(int id){
        
        for (int i = 0; i < BancoDeDados.hospedes.size(); i++) {
            
            if (BancoDeDados.hospedes.get(i).id == id) {
                
                BancoDeDados.hospedes.remove(i);
                this.persistido = false;
                return true;
    }
        }
        return false;
    }
    
    @Override
    public boolean carregar(int id){
        for (Hospede h : BancoDeDados.hospedes) {
            if (h.id == id && h instanceof HospedeNormal) {
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
        
        return BancoDeDados.hospedes;

    }

    // --------------------------------- SOBREESCRITA HOSPEDE ---------------------------------- //


    @Override
    public boolean Pagante(){

        return false;
        
    }
    
}
