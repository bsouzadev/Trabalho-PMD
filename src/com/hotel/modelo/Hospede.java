package src.com.hotel.modelo;


public abstract class Hospede extends Entidade_1<Hospede> {

    protected Reserva reserva;
    protected String nome;
    protected int idade;
    protected String cpf;

    public Hospede (String nome, int idade, String cpf, int id){
        super(id);
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        
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


    
    // --------------------------------- TOSTRING DE HOSPEDE --------------------------------- //

    @Override
    public String toString() {

        return super.toString() + "\n" +
           "Nome: " + nome + "\n" +
           "Idade: " + idade + "\n" +
           "CPF: " + cpf;

    
    }
}
