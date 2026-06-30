package testes.com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.com.hotel.modelo.HospedePagante;
import src.com.hotel.modelo.Quarto;
import src.com.hotel.modelo.Reserva;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;


public class ReservasTestes {
     
    private EntidadeDAO<Reserva> dao;

    @BeforeEach
    public void inicializar() throws Exception {

        dao = GaranteDAO.getDAO(Reserva.class);

        dao.limpar();

    }

    @Test
    public void testarSalvarReserva_1() throws PersistenceException {

        HospedePagante hospede = new HospedePagante( 
        "111122223333444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );

        Reserva reserva = new Reserva (hospede, 0);

        dao.salvar(reserva);
        
        Reserva carregado = dao.carregar(0);

        assertEquals(reserva, carregado);
    }

     @Test
    public void testarSalvarReserva_2() throws PersistenceException {

        HospedePagante hospede1 = new HospedePagante( 
        "111122223333444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );

        Reserva reserva_1 = new Reserva (hospede1, 0);

        dao.salvar(reserva_1);

        Reserva reserva_2 = new Reserva (hospede1, 0);

        assertThrows(PersistenceException.class, () -> {dao.salvar(reserva_2);});

        
    }

     @Test
    public void testarAtualizarReserva_1() throws PersistenceException {

        HospedePagante hospede = new HospedePagante( 
        "111122223333444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );

        Reserva reserva = new Reserva (hospede, 0);

        dao.salvar(reserva);

        HospedePagante hospede1 = new HospedePagante( 
        "111122223333444",
        123,
        "01/2028",
        "(32)999998888",
        "email@gmail.com",
        "Luana Sousa",
        35,
        "123.456.789-00",
        0          
        );

        Reserva reserva_2 = new Reserva (hospede, 0);
        

        dao.atualizar(reserva_2);

        Reserva carregado = dao.carregar(0);

        assertEquals(reserva_2, carregado);
    }

     @Test
    public void testarAtualizarReserva_2() throws Exception {

        HospedePagante hospede = new HospedePagante( 
        "111122223333444",
        123,
        "01/2029",
        "(32)999998889",
        "email@hotmail.com",
        "Luana Moraes",
        33,
        "123.456.789-00",
        0          
        );

        Reserva reserva = new Reserva (hospede, 4);

        assertThrows(PersistenceException.class, () -> {dao.atualizar(reserva);});

        
    }

     @Test
    public void testarApagarReserva_1() throws PersistenceException {

        HospedePagante hospede = new HospedePagante( 
        "111122223333444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );

        Reserva reserva = new Reserva (hospede, 0);
    
        dao.salvar(reserva);

        dao.apagar(0);

        assertThrows(PersistenceException.class, () -> {dao.carregar(0);});
        
    }

     @Test
    public void testarApagarReserva_2() throws Exception {

        assertThrows(PersistenceException.class, () -> {dao.apagar(1);});

    }

     @Test
    public void testarCarregarReserva_1() throws PersistenceException {

        HospedePagante hospede = new HospedePagante( 
        "111122223333444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );

        Reserva reserva = new Reserva (hospede, 0);
    
        dao.salvar(reserva);

        Reserva carregado = dao.carregar(0);

        assertEquals(reserva, carregado);
    }
    
    @Test
    public void testarCarregarQuarto_2() throws Exception {

        assertThrows(PersistenceException.class, () -> {dao.carregar(1);});
    }

}
