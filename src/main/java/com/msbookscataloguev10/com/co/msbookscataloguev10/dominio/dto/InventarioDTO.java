package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto;

import lombok.Data;

@Data
public class InventarioDTO {
  private Long idInventario;
  private Long idLibro;
  private String tipoMovimiento;
  private String fechaInventario;
  private Integer cantidadInventario;
}
