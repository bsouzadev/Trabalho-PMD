package testes.com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.com.hotel.modelo.Hospede;
import src.com.hotel.modelo.HospedePagante;
import src.com.hotel.modelo.HospedeNormal;
import src.com.hotel.persistencia.EntidadeDAO;
import src.com.hotel.persistencia.GaranteDAO;
import src.com.hotel.persistencia.PersistenceException;

public class HospedeNormalTestes {
    private EntidadeDAO<HospedeNormal> dao;
    @BeforeEach
    public void inicializar() throws Exception {

        dao = GaranteDAO.getDAO(HospedeNormal.class);

        dao.limpar();

    }

    @Test
    public void testarSalvarHospede_1() throws PersistenceException {

        HospedeNormal hospedeNormal = new HospedeNormal(
            "Luana Moraes",
            35,
            "123.456.789-00",
            0          
        );
    
        dao.salvar(hospedeNormal);
        
        Hospede carregado = dao.carregar(0);

        assertEquals(hospede, carregado);
    }

     @Test
    public void testarSalvarHospede_2() throws PersistenceException {

        HospedeNormal hospedeNormal = new HospedeNormal(
            "Luana Moraes",
            35,
            "123.456.789-00",
            0          
        );
    
        dao.salvar(hospedeNormal);

        HospedeNormal hospedeNormal2 = new HospedeNormal(
            "Luana Moraes",
            35,
            "123.456.789-00",
            0          
        );

        assertThrows(PersistenceException.class, () -> {dao.salvar(hospedeNormal2);});


    }

     @Test
    public void testarAtualizarHospede_1() throws PersistenceException {

        HospedeNormal hospedeNormal = new HospedeNormal(
            "Luana Moraes",
            35,
            "123.456.789-00",
            0          
        );
    
        dao.salvar(hospedeNormal);

        HospedeNormal hospedeNormal2 = new HospedeNormal(
            "Luan Sousa",
            30,
            "123.456.789-00",
            0          
        );

        dao.atualizar(hospedeNormal2);

        Hospede carregado = dao.carregar(0);

        assertEquals(hospedeNormal2, carregado);
    }

     @Test
    public void testarAtualizarHospede_2() throws Exception {

        HospedeNormal hospedeNormal = new HospedeNormal(
            "Luana Moraes",
            35,
            "123.456.789-00",
            0          
        );
        
        assertThrows(PersistenceException.class, () -> {dao.atualizar(hospedeNormal);});

        
    }

     @Test
    public void testarApagarHospede_1() throws PersistenceException {

        HospedeNormal hospedeNormal = new HospedeNormal(
            "Luana Moraes",
            35,
            "123.456.789-00",
            0          
        );
    
        dao.salvar(hospedeNormal);

        dao.apagar(0);

        assertThrows(PersistenceException.class, () -> {dao.carregar(0);});
        
    }

     @Test
    public void testarApagarHospede_2() throws Exception {

        assertThrows(PersistenceException.class, () -> {dao.apagar(1);});

    }

     @Test
    public void testarCarregarHospede_1() throws PersistenceException {

        HospedeNormal hospedeNormal = new HospedeNormal(
            "Luana Moraes",
            35,
            "123.456.789-00",
            0          
        );
    
        dao.salvar(hospedeNormal);

        Hospede carregado = dao.carregar(0);

        assertEquals(hospedeNormal, carregado);
    }
    
    @Test
    public void testarCarregarHospede_2() throws Exception {

        assertThrows(PersistenceException.class, () -> {dao.carregar(1);});
    }

    
}