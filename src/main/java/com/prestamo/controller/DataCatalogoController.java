package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.service.DataCatalogoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/dataCatalogo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class DataCatalogoController {

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

	
	@PostMapping("/registraDataCatalogo")
	@ResponseBody
	public ResponseEntity<?> insertaDataCatalogo(@RequestBody DataCatalogo obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdDataCatalogo(0);
			obj.setFechaActualizacion(new Date());
			obj.setFechaRegistro(new Date());
			obj.setEstado(AppSettings.ACTIVO);
			
			DataCatalogo objSalida = dataCatalogoService.insertaActualizaDataCatalogo(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO + " Data Catálogo de ID ==> " + obj.getIdDataCatalogo() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaDataCatalogo")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaDataCatalogo(@RequestBody DataCatalogo obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			obj.setFechaActualizacion(new Date());

			DataCatalogo objSalida = dataCatalogoService.insertaActualizaDataCatalogo(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Data catálogo de ID ==> " + obj.getIdDataCatalogo() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@DeleteMapping("/eliminaDataCatalogo/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaDataCatalogo(@PathVariable("id") int idDataCatalogo) {
		Map<String, Object> salida = new HashMap<>();
		try {
			dataCatalogoService.eliminaDataCatalogo(idDataCatalogo);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Data catálogo de ID ==> " + idDataCatalogo + ".");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	

}