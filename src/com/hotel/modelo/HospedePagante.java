package src.com.hotel.modelo;

public class HospedePagante extends Hospede{
    
    protected int numero_cartao;
    protected int cvv;
    protected int data_vencimento;
    protected int telefone;
    protected String email;

    public HospedePagante(int numero_cartao, int cvv, int data_vencimento, int telefone,
        String email, String nome, int idade, int cpf, int id){
            
            super(nome, idade, cpf, id);
            this.numero_cartao =  numero_cartao;
            this.cvv = cvv;
            this.data_vencimento = data_vencimento;
            this.telefone = telefone;
            this.email = email; 
        
        }

    // --------------------------------- GETTERS --------------------------------- //

    public int getNumeroCartao(){
        return this.numero_cartao;
    }

    public int getCvv(){
        return this.cvv;
    }

    public int getDataVencimento(){
        return this.data_vencimento;
    }

    public int getTelefone(){
        return this.telefone;
    }

    public String getEmail(){
        return this.email;
    }

    // --------------------------------- SETTERS --------------------------------- //

    public void setNumeroCartao(int numero_cartao){
        this.numero_cartao = numero_cartao;
    }

    public void setCvv(int cvv){
       this.cvv = cvv;
    }

    public void setDataVencimento(int data_vencimento){
       this.data_vencimento = data_vencimento;
    }

    public void setTelefone(int telefone){
        this.telefone = telefone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    // --------------------------------- SOBRESCRITA DE HÓSPEDE --------------------------------- //

    public boolean Pagante(){

        return true;
    
    }
    
}
