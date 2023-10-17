package com.project.Proiect.Practica.Service;

import com.project.Proiect.Practica.Entities.Exponat;
import com.project.Proiect.Practica.Repository.IRepositoryExponat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ServiceExponatTest {

    @InjectMocks
    private ServiceExponat serviceExponat;

    @Mock
    private IRepositoryExponat repositoryExponat;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateExponat() {
        Exponat exponat = new Exponat();
        serviceExponat.createExponat(exponat);
        Mockito.verify(repositoryExponat).save(exponat);
    }

    @Test
    public void testGetExponat() {
        List<Exponat> exponatList = new ArrayList<>();
        Exponat exponat1 = new Exponat();
        exponat1.setNume("Exponat1");
        exponatList.add(exponat1);

        Exponat exponat2 = new Exponat();
        exponat2.setNume("Exponat2");
        exponatList.add(exponat2);

        Mockito.when(repositoryExponat.findAll()).thenReturn(exponatList);

        List<Exponat> result = serviceExponat.getExponat();
        assertEquals(exponatList, result);
    }
    @Test
    public void testIsAvailable() {
        String result = serviceExponat.isAvailable();
        // Verifică că mesajul conține "Serviciul este disponibil"
        assert(result.contains("Serviciul este disponibil"));
    }

    @Test
    public void testGetAll() {
        List<Map<String, Exponat>> exponatList = new ArrayList<>();

        Map<String, Exponat> exponatMap1 = new HashMap<>();
        Exponat exponat1 = new Exponat();
        exponat1.setNume("Exponat1");
        exponatMap1.put("key1", exponat1);
        exponatList.add(exponatMap1);

        Map<String, Exponat> exponatMap2 = new HashMap<>();
        Exponat exponat2 = new Exponat();
        exponat2.setNume("Exponat2");
        exponatMap2.put("key2", exponat2);
        exponatList.add(exponatMap2);

        Mockito.when(repositoryExponat.getAll()).thenReturn(exponatList);

        ResponseEntity<?> result = serviceExponat.getAll();
        assertEquals(ResponseEntity.ok(exponatList), result);
    }

    @Test
    public void testGetDescrByName() {
        String numeExistent = "NumeExistent";
        List<Map<String, Exponat>> exponatList = new ArrayList<>();

        Map<String, Exponat> exponatMap = new HashMap<>();
        Exponat exponat = new Exponat();
        exponat.setNume(numeExistent);
        exponatMap.put("key", exponat);
        exponatList.add(exponatMap);

        Mockito.when(repositoryExponat.getDescrByName(numeExistent)).thenReturn(exponatList);

        ResponseEntity<?> result = serviceExponat.getDescrByName(numeExistent);
        assertEquals(ResponseEntity.ok(exponatList), result);

        String numeInexistent = "NumeInexistent";
        Mockito.when(repositoryExponat.getDescrByName(numeInexistent)).thenReturn(new ArrayList<>());
        result = serviceExponat.getDescrByName(numeInexistent);
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Nu au fost gasite exponate cu numele: " + numeInexistent + ".\" }"), result);
    }

    @Test
    public void testGetDescrByLocation() {
        String locatieExistenta = "LocatieExistenta";
        List<Map<String, Exponat>> exponatList = new ArrayList<>();

        Map<String, Exponat> exponatMap = new HashMap<>();
        Exponat exponat = new Exponat();
        exponat.setLocatie(locatieExistenta);
        exponatMap.put("key", exponat);
        exponatList.add(exponatMap);

        Mockito.when(repositoryExponat.getDescrByLocation(locatieExistenta)).thenReturn(exponatList);

        ResponseEntity<?> result = serviceExponat.getDescrByLocation(locatieExistenta);
        assertEquals(ResponseEntity.ok(exponatList), result);

        String locatieInexistenta = "LocatieInexistenta";
        Mockito.when(repositoryExponat.getDescrByLocation(locatieInexistenta)).thenReturn(new ArrayList<>());
        result = serviceExponat.getDescrByLocation(locatieInexistenta);
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Nu au fost gasite exponate cu locatia: " + locatieInexistenta + ".\" }"), result);
    }

    @Test
    public void testGetByCod() {
        UUID codExistent = UUID.randomUUID();
        Exponat exponatExistent = new Exponat();
        Mockito.when(repositoryExponat.getByCod(codExistent)).thenReturn(exponatExistent);

        ResponseEntity<?> result = serviceExponat.getByCod(codExistent.toString());
        assertEquals(ResponseEntity.ok(exponatExistent), result);

        UUID codInexistent = UUID.randomUUID();
        Mockito.when(repositoryExponat.getByCod(codInexistent)).thenReturn(null);
        result = serviceExponat.getByCod(codInexistent.toString());
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Exponatul cu codul " + codInexistent + " nu a fost gasit.\" }"), result);

        String codInvalid = "InvalidUUID";
        result = serviceExponat.getByCod(codInvalid);
        assertEquals(ResponseEntity.badRequest().body("{ \"Eroare\": \"Format de UUID invalid.\"}"), result);
    }

    @Test
    public void testGetByInventory() {
        String codInventarExistent = "CodInventarExistent";
        Exponat exponatExistent = new Exponat();
        Mockito.when(repositoryExponat.getByInventory(codInventarExistent)).thenReturn(exponatExistent);

        ResponseEntity<?> result = serviceExponat.getByInventory(codInventarExistent);
        assertEquals(ResponseEntity.ok(exponatExistent), result);

        String codInventarInexistent = "CodInventarInexistent";
        Mockito.when(repositoryExponat.getByInventory(codInventarInexistent)).thenReturn(null);
        result = serviceExponat.getByInventory(codInventarInexistent);
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Exponatul cu codul " + codInventarInexistent + " nu a fost gasit.\" }"), result);

        String codInventarInvalid = "";
        result = serviceExponat.getByInventory(codInventarInvalid);
        assertEquals(ResponseEntity.badRequest().body("{ \"Eroare\": \"Nu s-a specificat un cod.\" }"), result);
    }

}
