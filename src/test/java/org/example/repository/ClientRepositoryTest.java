package org.example.repository;

import org.example.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    ClientRepository clientRepo;
    Client c1;
    Client c2;
    Client c3;

    @BeforeEach
    void setUp() {
        clientRepo = ClientRepository.getClientRepository();
        c1 = new Client(1L,"AAAA","Pedro","p");
        c2 = new Client(2L,"BBBB","Joao","j");
        clientRepo.add(c1);
        clientRepo.add(c2);
    }

    @Test
    void add() {
        c3 = new Client(3L,"CCCC","Susana","s");
        clientRepo.add(c3);
        Assertions.assertEquals("Susana", clientRepo.findById(3l).getName());

    }
    @Test
    void findById() {
    Assertions.assertEquals("Pedro",clientRepo.findById(1l).getName());
    Assertions.assertNull(clientRepo.findById(100L));
    Assertions.assertEquals(null,clientRepo.findById(100L));
    }

    @Test
    void update() {
        c3 = new Client(2L,"CCCC","Susana","s");
        clientRepo.update(c3);
        Assertions.assertEquals("Susana",clientRepo.findById(2L).getName());
    }
    @Test
    void findAll() {
        ArrayList<Client> clients2 = clientRepo.findAll();
        Assertions.assertEquals(clients2,clientRepo.findAll());
        Assertions.assertTrue(clients2==clientRepo.findAll());
    }
    @Test
    void deleteById() {
        c3 = new Client(3L,"CCCC","Susana","s");
        clientRepo.add(c3);
        Assertions.assertEquals("Susana", clientRepo.findById(3L).getName());
        clientRepo.deleteById(3l);
        Assertions.assertEquals(null, clientRepo.findById(3l));

    }

    @Test
    void nextIdAvailable() {
        /*Long siguiente=clientRepo.nextIdAvailable();
        Assertions.assertEquals(3L,siguiente);*/
        Assertions.assertEquals(3L,clientRepo.nextIdAvailable());
        c3 = new Client(3L,"CCCC","Susana","s");
        clientRepo.add(c3);
        Assertions.assertEquals(4L,clientRepo.nextIdAvailable());
    }

    @Test
    void findByDni() {
        Assertions.assertEquals("Pedro",clientRepo.findByDni("AAAA").getName());
        Assertions.assertNull(clientRepo.findByDni("XXXX"));
        Assertions.assertEquals(null,clientRepo.findByDni("WWWW"));
    }

    @Test
    void cleanUp() {
        Assertions.assertEquals("Pedro",clientRepo.findByDni("AAAA").getName());
        clientRepo.cleanUp();
        Assertions.assertEquals(null,clientRepo.findByDni("AAAA"));
        Assertions.assertNull(clientRepo.findByDni("XXXX"));

    }
}