package src.com.hotel.persistencia;

public class PersistenceException extends Exception {
    
    //Criei esses atributos para que depois consigamos personalizar e direcionar o tratamento das exceções para o local onde ocorreram (operacao), se for necessário
    private String operacao;
    private Object valor;

    public PersistenceException(String operacao, String mensagem, Object valor){
        super(operacao + ": " + mensagem + " // Inconsistência em " + valor + "\n"); //Esse formato de aviso da exceção não é definitivo. Mudem pra forma que acharem melhor
        this.operacao = operacao;
        this.valor = valor;
    }

    String getOperacao () {
        return this.operacao;
    }

    Object getValor () {
        return this.valor;
    }

}
