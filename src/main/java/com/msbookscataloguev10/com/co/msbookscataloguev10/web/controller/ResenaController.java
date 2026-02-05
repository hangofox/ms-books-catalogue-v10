package com.msbookscataloguev10.com.co.msbookscataloguev10.web.controller;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.ResenaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.ResenaService;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Resena;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Operation(
            summary = "Obtener las reseñas de un libro",
            description = "Devuelve todas las reseñas ingresadas por los usuarios en un libro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseñas obtenidas correctamente"),
            @ApiResponse(responseCode = "404", description = "Reseñas no encontradas")
    })
    @GetMapping("/{idLibro}")
    public ResponseEntity<List<ResenaDTO>> listarResenas(@PathVariable Long idLibro){
        List<ResenaDTO> resenas= resenaService.listarResenas(idLibro);
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    @GetMapping("/calificacion/{idLibro}")
    public Double consultarCalificacion(@PathVariable Long idLibro){
        return resenaService.consultarCalificacion(idLibro);
    }

    @PostMapping
    public ResponseEntity<Void> crearResena(@RequestBody ResenaDTO resenaDTO){
        resenaService.crearResena(resenaDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idResena}")
    public ResponseEntity<ResenaDTO> consultarResenaporId(@PathVariable Long idResena){
        try {
            ResenaDTO resenaDTO=resenaService.consultarResenaporId(idResena);
            return ResponseEntity.ok(resenaDTO);
        } catch (IllegalStateException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idResena}")
    public ResponseEntity<Void> actualizarResena(@PathVariable Long idResena, @RequestBody ResenaDTO resenaDTO) {
        resenaDTO.setIdResena(idResena);
        resenaService.actualizarResena(resenaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idResena}")
    public ResponseEntity<String> eliminarResena(@PathVariable Long idResena){
        try {
            resenaService.eliminarResena(idResena);
            return ResponseEntity.ok("Reseña eliminada correctamente");
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
