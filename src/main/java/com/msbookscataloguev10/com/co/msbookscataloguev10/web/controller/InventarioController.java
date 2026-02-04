package com.msbookscataloguev10.com.co.msbookscataloguev10.web.controller;

import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.InventarioDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

  @Autowired
  private InventarioService inventarioService;

  @GetMapping("/kardex/{idLibro}")
  public ResponseEntity<List<InventarioDTO>> listarKardex(@PathVariable Long idLibro){
    List<InventarioDTO> inventariosSlice = inventarioService.listarKardex(idLibro);

    return new ResponseEntity<>(inventariosSlice, HttpStatus.OK);
  }

  @PostMapping("/entrada")
  public ResponseEntity<Void> registrarEntrada(@RequestBody InventarioDTO inventarioDTO) {
    inventarioService.registrarEntrada(inventarioDTO);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/salida")
  public ResponseEntity<String> registrarSalida(@RequestBody InventarioDTO inventarioDTO) {
    try {
      inventarioService.registrarSalida(inventarioDTO);
      return ResponseEntity.ok("Salida registrada correctamente");
    } catch (IllegalStateException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(e.getMessage());
    }

  }

  @GetMapping("/stock/{idLibro}")
  public Integer consultarStock(@PathVariable Long idLibro) {
    return inventarioService.consultarStock(idLibro);
  }

}

