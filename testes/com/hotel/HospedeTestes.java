package testes.com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.com.hotel.modelo.Hospede;
import src.com.hotel.modelo.HospedePagante;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;

public class HospedeTestes {
    private EntidadeDAO<HospedePagante> dao;

    @BeforeEach
    public void inicializar() throws Exception {

        dao = GaranteDAO.getDAO(HospedePagante.class);

        dao.limpar();

    }

    @Test
    public void testarSalvarHospede_1() throws PersistenceException {

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
    
        dao.salvar(hospede);
        
        Hospede carregado = dao.carregar(0);

        assertEquals(hospede, carregado);
    }

     @Test
    public void testarSalvarHospede_2() throws PersistenceException {

        HospedePagante hospede = new HospedePagante( 
        "1111222233334444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );

        dao.salvar(hospede);

        HospedePagante hospede_2 = new HospedePagante( 
        "1111222233334444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );

        assertThrows(PersistenceException.class, () -> {dao.salvar(hospede_2);});


    }

     @Test
    public void testarAtualizarHospede_1() throws PersistenceException {

        HospedePagante hospede = new HospedePagante( 
        "1111222233334444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );

        dao.salvar(hospede);

        HospedePagante hospede_2 = new HospedePagante( 
        "4444333322221111",
        321,
        "07/2035",
        "(32)66668888",
        "email2@gmail.com",
        "Pedro Silva",
        21,
        "098.765.432-00",
        0          
        );

        dao.atualizar(hospede_2);

        Hospede carregado = dao.carregar(0);

        assertEquals(hospede_2, carregado);
    }

     @Test
    public void testarAtualizarHospede_2() throws Exception {

        HospedePagante hospede = new HospedePagante( 
        "1111222233334444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );
        
        assertThrows(PersistenceException.class, () -> {dao.atualizar(hospede);});

        
    }

     @Test
    public void testarApagarHospede_1() throws PersistenceException {

        HospedePagante hospede = new HospedePagante( 
        "1111222233334444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );
    
        dao.salvar(hospede);

        dao.apagar(0);

        assertThrows(PersistenceException.class, () -> {dao.carregar(0);});
        
    }

     @Test
    public void testarApagarHospede_2() throws Exception {

        assertThrows(PersistenceException.class, () -> {dao.apagar(1);});

    }

     @Test
    public void testarCarregarHospede_1() throws PersistenceException {

        HospedePagante hospede = new HospedePagante( 
        "1111222233334444",
        123,
        "01/2029",
        "(32)999998888",
        "email@gmail.com",
        "Luana Moraes",
        35,
        "123.456.789-00",
        0          
        );
    
        dao.salvar(hospede);

        Hospede carregado = dao.carregar(0);

        assertEquals(hospede, carregado);
    }
    
    @Test
    public void testarCarregarHospede_2() throws Exception {

        assertThrows(PersistenceException.class, () -> {dao.carregar(1);});
    }

    
}

// package testes.com.hotel;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import src.com.hotel.modelo.Quarto;
// import src.com.hotel.persistencia.EntidadeDAO;
// import src.com.hotel.persistencia.GaranteDAO;
// import src.com.hotel.persistencia.PersistenceException;


// public class QuartosTestes {
     
//     private EntidadeDAO<Quarto> dao;

//     @BeforeEach
//     public void inicializar() throws Exception {

//         dao = GaranteDAO.getDAO(Quarto.class);

//         dao.limpar();

//     }

//     @Test
//     public void testarSalvarQuarto_1() throws PersistenceException {

//         Quarto quarto = new Quarto(
//                 0,
//                 "Deluxe",
//                 5000.00,
//                 0
//         );
    
//         dao.salvar(quarto);
        
//         Quarto carregado = dao.carregar(0);

//         assertEquals(quarto, carregado);
//     }

//      @Test
//     public void testarSalvarQuarto_2() throws PersistenceException {

//         Quarto quarto = new Quarto(
//                 0,
//                 "Deluxe",
//                 5000.00,
//                 0
//         );

//         dao.salvar(quarto);

//         Quarto quarto_2 = new Quarto(
//                 0,
//                 "Deluxe",
//                 5000.00,
//                 0
//         );

//         assertThrows(PersistenceException.class, () -> {dao.salvar(quarto_2);});

        
//     }

//      @Test
//     public void testarAtualizarQuarto_1() throws PersistenceException {

//         Quarto quarto = new Quarto(
//                 0,
//                 "Deluxe",
//                 5000.00,
//                 0
//         );

//         dao.salvar(quarto);

//         Quarto quarto_2 = new Quarto(
//                 1,
//                 "Standard",
//                 2500.00,
//                 0
//         );

//         dao.atualizar(quarto_2);

//         Quarto carregado = dao.carregar(0);

//         assertEquals(quarto_2, carregado);
//     }

//      @Test
//     public void testarAtualizarQuarto_2() throws Exception {

//         Quarto quarto = new Quarto(
//                 2,
//                 "Deluxe",
//                 5000.0,
//                 5
//         );

//         assertThrows(PersistenceException.class, () -> {dao.atualizar(quarto);});

        
//     }

//      @Test
//     public void testarApagarQuarto_1() throws PersistenceException {

//         Quarto quarto = new Quarto(
//                 0,
//                 "Deluxe",
//                 5000.00,
//                 0
//         );
    
//         dao.salvar(quarto);

//         dao.apagar(0);

//         assertThrows(PersistenceException.class, () -> {dao.carregar(0);});
        
//     }

//      @Test
//     public void testarApagarQuarto_2() throws Exception {

//         assertThrows(PersistenceException.class, () -> {dao.apagar(1);});

//     }

//      @Test
//     public void testarCarregarQuarto_1() throws PersistenceException {

//         Quarto quarto = new Quarto(
//                 0,
//                 "Deluxe",
//                 5000.00,
//                 0
//         );
    
//         dao.salvar(quarto);

//         Quarto carregado = dao.carregar(0);

//         assertEquals(quarto, carregado);
//     }
    
//     @Test
//     public void testarCarregarQuarto_2() throws Exception {

//         assertThrows(PersistenceException.class, () -> {dao.carregar(1);});
//     }


// }

// package testes.com.hotel;

// public class ReservasTestes {
    
// }
