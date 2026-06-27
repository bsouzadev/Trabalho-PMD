package src.com.hotel.persistencia;

import java.util.HashMap;
import java.util.Map;
import src.com.hotel.modelo.Entidade_1;

public class GaranteDAO {
    
    private static Map <Class<?>, EntidadeDAO<?>> daos = new HashMap<>();
    
    @SuppressWarnings("unchecked")
    public static <E extends Entidade_1<?>> EntidadeDAO <E> getDAO (Class <E> classe) {
        if (!daos.containsKey(classe)) { //Se não existir um EntidadeDAO para a classe, então cria-se um
            daos.put(classe, new EntidadeDAO<E>(classe));
        }
        return (EntidadeDAO<E>) daos.get(classe);
    }
}

//Para que essa classe garanta que tenhamos apenas uma EntidadeDAO por entidade, devemos seguir o seguinte padrão:

//Para "criar" o objeto: EntidadeDAO<classe> dao = GaranteDAO.getDAO(classe.class)
//onde 'classe' deve ser substituído pela classe do que se deseja criar
