package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.DataCatalogo;


public interface DataCatalogoService {

	//Para validaciones
	public abstract List<DataCatalogo> listaDataCatalogo(int idTipo);
	public abstract List<DataCatalogo> listaDataCatalogoPorDescripcionIgual(String descripcion);
	public abstract List<DataCatalogo> listaDataCatalogoPorDescripcionIgualActualiza(String descripcion, int idDataCatalogo);
	
	//Para el crud
	public abstract DataCatalogo insertaActualizaDataCatalogo(DataCatalogo obj);
	public abstract List<DataCatalogo> listaDataCatalogoPorDescripcionLike(String nombre);
	public abstract void eliminaDataCatalogo(int idDataCatalogo);
	public abstract List<DataCatalogo> listaDataCatalogo();
	
	//Para la Consulta
		public abstract List<DataCatalogo> listaDataCatalogoConsultaCompleja(String descripcion, int idDataCatalogo, int estado);
}
