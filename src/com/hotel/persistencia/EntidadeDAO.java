package src.com.hotel.persistencia;
import src.com.hotel.modelo.Entidade_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class EntidadeDAO <E extends Entidade_1<?>> {

    protected Set<E> setObjetos = new HashSet<>();

    public void salvar(E objt) throws PersistenceException {

        if (!setObjetos.add(objt)) {
            throw new PersistenceException("O objeto já existe no conjunto!");
        }
    }

    public void atualizar(E objt) throws PersistenceException {

        E encontrado = null;

        for (E a : setObjetos) {
            if (a.getId() == objt.getId()) {
                encontrado = a;
                break;
            }
        }

        if (encontrado == null) {
            throw new PersistenceException("Objeto não encontrado.");
        }

        setObjetos.remove(encontrado);
        setObjetos.add(objt);
    }

    public E apagar(int id) throws PersistenceException {

        E encontrado = null;

        for (E a : setObjetos) {
            if (a.getId() == id) {
                encontrado = a;
                break;
            }
        }

        if (encontrado == null) {
            throw new PersistenceException("Objeto não encontrado.");
        }

        setObjetos.remove(encontrado);

        return encontrado;
    }

    public E carregar(int id) throws PersistenceException {
        for (E a : setObjetos) {
            if (a.getId() == id) {
                return a;
            }
        }

        throw new PersistenceException("Não existe um objeto com o mesmo id no conjunto!");
    }

    public E[] carregarTodos() throws PersistenceException {

        ArrayList<E> aux = new ArrayList<>(setObjetos);

        if (aux.isEmpty()) {
            throw new PersistenceException("Conjunto vazio!");
        }

        Collections.sort(aux);

        @SuppressWarnings("unchecked")
        E[] vet = (E[]) aux.toArray(new Entidade_1[aux.size()]);

        return vet;
    }

    public boolean persistir(){

    }

    public boolean recuperar(){
        
    }
}
