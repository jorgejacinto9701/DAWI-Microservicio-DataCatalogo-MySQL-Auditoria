package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.DataCatalogo;

public interface DataCatalogoRepository extends JpaRepository<DataCatalogo, Integer>{
	
	
	@Query("Select r from DataCatalogo r where r.catalogo.idCatalogo =  ?1 order by descripcion asc")
	public abstract List<DataCatalogo> listaDataCatalogo(int idTipo);
	
	@Query("select d from DataCatalogo d where d.descripcion = ?1")
	public abstract List<DataCatalogo> listaDataCatalogoPorDescripcionIgual(String descripcion);
	
	@Query("select d from DataCatalogo d where d.descripcion like ?1")
	public abstract List<DataCatalogo> listaDataCatalogoPorDescripcionLike(String descripcion);
	
	
	@Query("select d from DataCatalogo d where d.descripcion = ?1 and d.idDataCatalogo != ?2")
	public abstract List<DataCatalogo> listaDataCatalogoPorDescripcionIgualActualiza(String descripcion, int idDataCatalogo);
	
	@Query("select d from DataCatalogo d where d.descripcion like ?1 and "
			+ " d.catalogo.idCatalogo = ?2 and "
			+ " d.estado = ?3 ")
	public abstract List<DataCatalogo> listaDataCatalogoConsultaCompleja(String descripcion, int catalogo, int estado);
}
