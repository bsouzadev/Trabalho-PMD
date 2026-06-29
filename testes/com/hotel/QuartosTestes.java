package testes.com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.com.hotel.modelo.Quarto;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;


public class QuartosTestes {
     
    private EntidadeDAO<Quarto> dao;

    @BeforeEach
    public void inicializar() throws Exception {

        dao = GaranteDAO.getDAO(Quarto.class);

        dao.limpar();

    }

    @Test
    public void testarSalvarQuarto_1() throws PersistenceException {

        Quarto quarto = new Quarto(
                0,
                "Deluxe",
                5000.00,
                0
        );
    
        dao.salvar(quarto);
        
        Quarto carregado = dao.carregar(0);

        assertEquals(quarto, carregado);
    }

     @Test
    public void testarSalvarQuarto_2() throws PersistenceException {

        Quarto quarto = new Quarto(
                0,
                "Deluxe",
                5000.00,
                0
        );

        dao.salvar(quarto);

        Quarto quarto_2 = new Quarto(
                0,
                "Deluxe",
                5000.00,
                0
        );

        assertThrows(PersistenceException.class, () -> {dao.salvar(quarto_2);});

        
    }

     @Test
    public void testarAtualizarQuarto_1() throws PersistenceException {

        Quarto quarto = new Quarto(
                0,
                "Deluxe",
                5000.00,
                0
        );

        dao.salvar(quarto);

        Quarto quarto_2 = new Quarto(
                1,
                "Standard",
                2500.00,
                0
        );

        dao.atualizar(quarto_2);

        Quarto carregado = dao.carregar(0);

        assertEquals(quarto_2, carregado);
    }

     @Test
    public void testarAtualizarQuarto_2() throws Exception {

        Quarto quarto = new Quarto(
                2,
                "Deluxe",
                5000.0,
                5
        );

        assertThrows(PersistenceException.class, () -> {dao.atualizar(quarto);});

        
    }

     @Test
    public void testarApagarQuarto_1() throws PersistenceException {

        Quarto quarto = new Quarto(
                0,
                "Deluxe",
                5000.00,
                0
        );
    
        dao.salvar(quarto);

        dao.apagar(0);

        assertThrows(PersistenceException.class, () -> {dao.carregar(0);});
        
    }

     @Test
    public void testarApagarQuarto_2() throws Exception {

        assertThrows(PersistenceException.class, () -> {dao.apagar(1);});

    }

     @Test
    public void testarCarregarQuarto_1() throws PersistenceException {

        Quarto quarto = new Quarto(
                0,
                "Deluxe",
                5000.00,
                0
        );
    
        dao.salvar(quarto);

        Quarto carregado = dao.carregar(0);

        assertEquals(quarto, carregado);
    }
    
    @Test
    public void testarCarregarQuarto_2() throws Exception {

        assertThrows(PersistenceException.class, () -> {dao.carregar(1);});
    }


}
