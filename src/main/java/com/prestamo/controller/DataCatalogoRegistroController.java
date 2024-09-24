package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.service.DataCatalogoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/dataCatalogo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class DataCatalogoRegistroController {

	@Autowired
	private DataCatalogoService dataCatalogoService;

	@GetMapping
	public ResponseEntity<List<DataCatalogo>> lista(int idTipo) {
		List<DataCatalogo> lstSalida = dataCatalogoService.listaDataCatalogo(idTipo);
		return ResponseEntity.ok(lstSalida);
	}

	@PostMapping
	public ResponseEntity<?> registra(@RequestBody DataCatalogo objDataCatalogo) {
		HashMap<String, Object> salida = new HashMap<>();
		objDataCatalogo.setFechaRegistro(new Date());
		objDataCatalogo.setFechaActualizacion(new Date());
		objDataCatalogo.setEstado(AppSettings.ACTIVO);

		DataCatalogo objSalida = dataCatalogoService.insertaActualizaDataCatalogo(objDataCatalogo);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		} else {
			salida.put("mensaje", "Registro exitoso el ID es >>> " + objDataCatalogo.getIdDataCatalogo()
					+ " >>> Descripcion >> " + objDataCatalogo.getDescripcion());
		}
		return ResponseEntity.ok(salida);
	}

	@GetMapping("/validaDescripcionRegistra")
	public String validaDescripcion(@RequestParam(name = "descripcion") String descripcion) {
		List<DataCatalogo> lstSalida = dataCatalogoService.listaDataCatalogoPorDescripcionIgual(descripcion);
		if (lstSalida.isEmpty()) {
			return "{\"valid\":true}";
		} else {
			return "{\"valid\":false}";
		}

	}

}