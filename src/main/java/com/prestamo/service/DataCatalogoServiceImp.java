package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.repository.DataCatalogoRepository;

@Service
public class DataCatalogoServiceImp implements DataCatalogoService {

	@Autowired
	private DataCatalogoRepository repository;

	@Override
	public DataCatalogo insertaActualizaDataCatalogo(DataCatalogo obj) {
		return repository.save(obj);
	}
	

	@Override
	public List<DataCatalogo> listaDataCatalogo(int idTipo) {
		return repository.listaDataCatalogo(idTipo);
	}
	
	
	public List<DataCatalogo> listaDataCatalogo() {
		return repository.findAll();
	} 

	@Override
	public List<DataCatalogo> listaDataCatalogoPorDescripcionIgual(String descripcion) {
		return repository.listaDataCatalogoPorDescripcionIgual(descripcion);
	}
	
	//////////////
	@Override
	public List<DataCatalogo> listaDataCatalogoPorDescripcionLike(String nombre) {
		return repository.listaDataCatalogoPorDescripcionLike(nombre);
	}

	@Override
	public void eliminaDataCatalogo(int idDataCatalogo) {
		repository.deleteById(idDataCatalogo);
	}
	
	@Override
	public List<DataCatalogo> listaDataCatalogoPorDescripcionIgualActualiza(String descripcion, int idDataCatalogo) {
		return repository.listaDataCatalogoPorDescripcionIgualActualiza(descripcion, idDataCatalogo);
	}
	
	//consulta
		@Override
		public List<DataCatalogo> listaDataCatalogoConsultaCompleja(String descripcion, int idDataCatalogo, int estado) {
				return repository.listaDataCatalogoConsultaCompleja(descripcion, idDataCatalogo, estado);
		}


}
