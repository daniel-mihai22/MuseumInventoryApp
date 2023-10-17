package com.project.Proiect.Practica.Service;

import com.project.Proiect.Practica.Entities.Exponat;
import com.project.Proiect.Practica.Repository.IRepositoryExponat;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ServiceExponat {
    private final IRepositoryExponat repositoryExponat;

    public ServiceExponat(IRepositoryExponat repositoryExponat) {
        this.repositoryExponat = repositoryExponat;
    }

    public void createExponat(Exponat exponat) {
        repositoryExponat.save(exponat);
    }

    public List<Exponat> getExponat() {
        return repositoryExponat.findAll();
    }

    public String isAvailable() {
        LocalDateTime dataCurenta;
        try {
            dataCurenta = LocalDateTime.now();
        } catch (Exception e) {
            return "Serviciul nu este disponibil";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'la ora' HH:mm:ss");
        String dataFormata = dataCurenta.format(formatter);

        return "Serviciul este disponibil astazi, la data " + dataFormata;
    }


    public ResponseEntity<?> getAll() {
        List<Map<String, Exponat>> rezultat;
        try {
            rezultat = repositoryExponat.getAll();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"Eroare\": \"Eroare la accesarea bazei de date.\"}");
        }

        if (rezultat.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Baza de date nu contine informatii.\"}");
        }

        return ResponseEntity.ok(rezultat);
    }

    public ResponseEntity<?> getDescrByName(String nume) {
        List<Map<String, Exponat>> rezultat;
        try {
            rezultat = repositoryExponat.getDescrByName(nume);
        }
        catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"Eroare\": \"Eroare la accesarea bazei de date.\"}");
        }
        if(nume.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"Eroare\": \"Nu s-a specificat un nume.\"}");
        }
        if (rezultat.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Nu au fost gasite exponate cu numele: " + nume + ".\" }");
        }
        return ResponseEntity.ok(repositoryExponat.getDescrByName(nume));
    }

    public ResponseEntity<?> getDescrByLocation(String locatie) {
        List<Map<String, Exponat>> rezultat;
        try {
            rezultat = repositoryExponat.getDescrByLocation(locatie);
        }
        catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"Eroare\": \"Eroare la accesarea bazei de date.\"}");
        }
        if(locatie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"Eroare\": \"Nu s-a specificat o locatie.\"}");
        }
        if (rezultat.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Nu au fost gasite exponate cu locatia: " + locatie + ".\" }");
        }
        return ResponseEntity.ok(repositoryExponat.getDescrByLocation(locatie));
    }

    public ResponseEntity<?> getByCod(String cod) {
        try {
            UUID uuid = UUID.fromString(cod);
            Exponat exponat = repositoryExponat.getByCod(uuid);
            if (exponat != null) {
                return ResponseEntity.ok(exponat);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Exponatul cu codul " + cod + " nu a fost gasit.\" }");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{ \"Eroare\": \"Format de UUID invalid.\"}");
        }
    }

    public ResponseEntity<?> getByInventory(String codInventar) {
        try {
            if (codInventar == null || codInventar.isEmpty()) {
                throw new IllegalArgumentException("Nu s-a specificat un cod.");
            }
            Exponat exponat = repositoryExponat.getByInventory(codInventar);
            if (exponat != null) {
                return ResponseEntity.ok(exponat);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"Eroare\": \"Exponatul cu codul " + codInventar + " nu a fost gasit.\" }");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{ \"Eroare\": \"" + e.getMessage() + "\" }");
        }
    }
}
