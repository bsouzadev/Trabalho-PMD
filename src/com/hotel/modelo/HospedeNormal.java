package src.com.hotel.modelo;

public class HospedeNormal extends Hospede{

    public HospedeNormal(String nome, int idade, int cpf, int id){
        super(nome, idade, cpf, id);
    }

    public boolean Pagante(){

        return false;
        
    }
    
}
