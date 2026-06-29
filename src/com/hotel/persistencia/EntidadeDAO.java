package src.com.hotel.persistencia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import src.com.hotel.modelo.Entidade_1;


public class EntidadeDAO <E extends Entidade_1<?>> {

    protected Set<E> setObjetos = new HashSet<>();
    private Class<E> classeObj;

    public EntidadeDAO (Class <E> classeObj) {
        this.classeObj = classeObj;
    }

    public void salvar(E objt) throws PersistenceException {

        if (!setObjetos.add(objt)) {
            throw new PersistenceException("salvar", "O objeto já existe no conjunto!", objt);
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
            throw new PersistenceException("atualizar", "Objeto não encontrado.", objt);
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
            throw new PersistenceException("apagar", "Objeto não encontrado.", id);
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

        throw new PersistenceException("carregar", "Não existe um objeto com o mesmo id no conjunto!", id);
    }

    /*public E[] carregarTodos() throws PersistenceException {

        ArrayList<E> aux = new ArrayList<>(setObjetos);

        if (aux.isEmpty()) {
            throw new PersistenceException("carregarTodos", "Conjunto Vazio", aux);
        }

        Collections.sort(aux);

        @SuppressWarnings("unchecked")
        E[] vet = (E[]) aux.toArray(new Entidade_1[aux.size()]);

        return vet;
    }*/

    public ArrayList<E> carregarTodos() throws PersistenceException {

        ArrayList<E> lista = new ArrayList<>(setObjetos);

        if (lista.isEmpty()) {
            throw new PersistenceException(
                    "carregarTodos",
                    "Conjunto vazio.",
                    lista
            );
        }

        Collections.sort(lista);

        return lista;
    }

    public void limpar() {

        setObjetos.clear();
    
    }

    public void persistir() throws IOException {

        String nomeArquivo = classeObj.getSimpleName() + "Dados.dat";
        try (ObjectOutputStream escrita = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            //O arquivo .dat consegue salvar o objeto inteiro. A linha abaixo salva todo o setObjetos de uma só vez no arquivo próprio da classe a ele destinada
            escrita.writeObject(setObjetos);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar() throws IOException, ClassNotFoundException {

        String nomeArquivo = classeObj.getSimpleName() + "Dados.dat";

        //Se o arquivo não existir, não há o que recuperar, e, portanto, interrompe o método
        File arquivo = new File (nomeArquivo);
        if (!arquivo.exists()) return;

        try (ObjectInputStream leitura = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            setObjetos.clear();
            setObjetos.addAll((Set <E>) leitura.readObject());
        }
    }
}
