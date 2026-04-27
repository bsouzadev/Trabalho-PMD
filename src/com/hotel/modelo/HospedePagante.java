package src.com.hotel.modelo;
import java.util.List;
import src.com.hotel.visao.BancoDeDados;

public class HospedePagante extends Hospede{
    
    protected int numero_cartao;
    protected int cvv;
    protected int data_vencimento;
    protected int telefone;
    protected String email;

    public HospedePagante(int numero_cartao, int cvv, int data_vencimento, int telefone,
        String email, String nome, int idade, String cpf, int id){
            
            super(nome, idade, cpf, id);
            this.numero_cartao =  numero_cartao;
            this.cvv = cvv;
            this.data_vencimento = data_vencimento;
            this.telefone = telefone;
            this.email = email; 
        
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
            if (h.id == id && h instanceof HospedePagante) {
                HospedePagante hp = (HospedePagante) h;
                this.nome = hp.nome;
                this.idade = hp.idade;
                this.cpf = hp.cpf;
                this.id = hp.id;
                this.numero_cartao = hp.numero_cartao;
                this.cvv = hp.cvv;
                this.data_vencimento = hp.data_vencimento;
                this.telefone = hp.telefone;
                this.email = hp.email;
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

    // --------------------------------- SOBRESCRITA HOSPEDE --------------------------------- //

    @Override
    public boolean Pagante(){

        return true;
    
    }

     @Override
    public String toString() {
        
        return String.format(
        "%s\nCartão: %s\nCVV: %d\nVencimento: %s\nTelefone: %s\nEmail: %s",
        super.toString(),
        numero_cartao,
        cvv,
        data_vencimento,
        telefone,
        email
    );
        
    }
    
}
