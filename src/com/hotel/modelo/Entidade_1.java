package src.com.hotel.modelo;

public abstract class Entidade_1 <T extends Entidade_1<T>> implements Comparable<Entidade_1<?>>{
    protected int id;

    public Entidade_1 (int id){
        this.id = id;
    }

    // --------------------------------- SOBRESCRITA EQUALS --------------------------------- //
    public boolean equals(Object obj){
        
        if (this == obj){ 
            
            return true; 
        }
    
        if (obj == null || getClass() != obj.getClass()){ 
            
            return false;
        
        }
    
    Entidade_1<?> outro = (Entidade_1<?>) obj;
    
    return this.id == outro.id;

    }
    /*Como pedido, sobrescrevemos o método "equals" de Object, o corpo desse método foi simplesmente
    copiado do método original presente em Object (só pesquisar no Google), e alterado em algumas partes
    para satisfazer o que foi pedido no trabalho, comparamos os ID's e fazemos cast para Entidade<?>,
    dependendo da classe.*/
    
    // --------------------------------- SOBRESCRITA COMPARETO --------------------------------- //
    @Override
    
    public int compareTo(Entidade_1<?> outra){

        return Integer.compare(this.id, outra.id);
    }
    /* Como pedido no enunciado do pacote modelo nas instruções do trabalho, temos que sobreescrever 
     compareTo de Comparable usando o valor do ID como base. Assim, usamos a classe
     Integer (igual fazemos com Strings, em que usamos o próprio compareTo da classe String)
     e seu método já existente "compare" que compara valores inteiros, retornando valores positivos,
     negativos ou nulos dependendo do resultado da comparação */

    // --------------------------------- GETTER + SETTER --------------------------------- //

    public int getId(){
        return id;
    }

    public void setId(int id){
    this.id = id;
}

// --------------------------------- TOSTRING --------------------------------- //

public String toString() {
    
    return "ID: " + this.id + "\n";
}
}
