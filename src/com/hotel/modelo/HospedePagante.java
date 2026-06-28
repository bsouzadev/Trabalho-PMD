package src.com.hotel.modelo;


public class HospedePagante extends Hospede{
    
    protected String numero_cartao;
    protected int cvv;
    protected String data_vencimento;
    protected String telefone;
    protected String email;

    public HospedePagante(String numero_cartao, int cvv, String data_vencimento, String telefone,
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

    public int getCvv(){
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

    public void setCvv(int cvv){
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


     @Override
    public String toString() {
        
        return String.format(
        "%s\nCartão: %s\nCVV: %d\nVencimento: %s\nTelefone: %s\nEmail: %s\n",
        super.toString(),
        numero_cartao,
        cvv,
        data_vencimento,
        telefone,
        email
    );
        
    }
    
}
