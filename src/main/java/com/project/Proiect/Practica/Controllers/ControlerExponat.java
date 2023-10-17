package com.project.Proiect.Practica.Controllers;

import com.project.Proiect.Practica.Entities.Exponat;
import com.project.Proiect.Practica.Service.ServiceExponat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/muzeu/exponat")

@Tag(name = "Exponate")
public class ControlerExponat {

    private final ServiceExponat serviceExponat;


    public ControlerExponat(ServiceExponat serviceExponat) {
        this.serviceExponat = serviceExponat;
    }

    @Operation(
            description = "Listarea exponatelor cu fiecare atribut.",
            summary = "Endpoint pentru listare a tuturor exponatelor.",
            responses = {
                    @ApiResponse(
                            description = "Success!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unathorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )

    @GetMapping
    public List<Exponat> getExponat(){
        return serviceExponat.getExponat();
    }

    @Operation(
            description = "Verificam daca aplicatia este disponibila la data si ora actuala.",
            summary = "Endpoint pentru dispobilitate aplicatie.",
            responses = {
                    @ApiResponse(
                            description = "Success!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unathorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping(path = "/isAvailable")
    public String isAvailable() {
        return serviceExponat.isAvailable();
    }

    @Operation(
            description = "Listare a tuturor exponatelor.",
            summary = "Endpoint pentru listare a tuturor exponatelor.",
            responses = {
                    @ApiResponse(
                            description = "Success!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unathorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll() {
        return serviceExponat.getAll();
    }

    @Operation(
            description = "Listare a tuturor exponatelor cu un anumit nume.",
            summary = "Endpoint pentru listare a tuturor exponatelor cu un anumit nume.",
            responses = {
                    @ApiResponse(
                            description = "Success!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unathorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping(path = "/getDescrByName")
    public ResponseEntity<?> getDescrByName(@RequestParam("nume") String nume) {
        return serviceExponat.getDescrByName(nume);
    }

    @Operation(
            description = "Listare a tuturor exponatelor cu o anumita locatie.",
            summary = "Endpoint pentru listare a tuturor exponatelor cu o anumita locatie.",
            responses = {
                    @ApiResponse(
                            description = "Success!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unathorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping(path = "/getDescrByLocation")
    public ResponseEntity<?> getDescrByLocation(@RequestParam("locatie") String locatie) {
        return serviceExponat.getDescrByLocation(locatie);
    }

    @Operation(
            description = "Listare a exponatului cu un anumit cod.",
            summary = "Endpoint pentru listare a unui exponat cu un anumit cod.",
            responses = {
                    @ApiResponse(
                            description = "Success!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unathorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/getByCod")
    public ResponseEntity<?> getByCod(@RequestParam("cod") String cod) {
        return serviceExponat.getByCod(cod);
    }

    @Operation(
            description = "Listare a exponatului cu un anumit cod inventar.",
            summary = "Endpoint pentru listare a unui exponat cu un anumit cod inventar.",
            responses = {
                    @ApiResponse(
                            description = "Success!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unathorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/getByInventory")
    public ResponseEntity<?> getByInventory(@RequestParam("codInventar") String codInventar) {
        return serviceExponat.getByInventory(codInventar);
    }

    @Operation(
            description = "Adauga un exponat in baza de date.",
            summary = "Endpoint pentru a adauga un nou exponat.",
            responses = {
                    @ApiResponse(
                            description = "Success!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unathorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @PostMapping("/createExponat")
    public void createExponat(@RequestBody Exponat exponat) {
        serviceExponat.createExponat(exponat);
    }

}
