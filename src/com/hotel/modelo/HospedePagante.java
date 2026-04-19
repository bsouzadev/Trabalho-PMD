package src.com.hotel.modelo;

public class HospedePagante extends Hospede{
    
    protected String numero_cartao;
    protected String cvv;
    protected String data_vencimento;
    protected String telefone;
    protected String email;

    public HospedePagante(String numero_cartao, String cvv, String data_vencimento, String telefone,
        String email, String nome, int idade, String cpf, int id){
            
            super(nome, idade, cpf, id);
            this.numero_cartao =  numero_cartao;
            this.cvv = cvv;
            this.data_vencimento = data_vencimento;
            this.telefone = telefone;
            this.email = email; 
        
        }

    // --------------------------------- GETTERS --------------------------------- //

    public String getNumeroCartao(){
        return this.numero_cartao;
    }

    public String getCvv(){
        return this.cvv;
    }

    public String getDataVencimento(){
        return this.data_vencimento;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public String getEmail(){
        return this.email;
    }

    // --------------------------------- SETTERS --------------------------------- //

    public void setNumeroCartao(String numero_cartao){
        this.numero_cartao = numero_cartao;
    }

    public void setCvv(String cvv){
       this.cvv = cvv;
    }

    public void setDataVencimento(String data_vencimento){
       this.data_vencimento = data_vencimento;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    // --------------------------------- SOBRESCRITA DE HÓSPEDE --------------------------------- //

    @Override
    public boolean Pagante(){

        return true;
    
    }
    
}
