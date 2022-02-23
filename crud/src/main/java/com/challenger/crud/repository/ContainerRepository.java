package com.challenger.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.challenger.crud.models.Container;

public interface ContainerRepository extends CrudRepository<Container, Long> {

	Container findById(long id);

	Container findByCliente(String cliente);

	Container findByNumero(String numero);
	
	// Query para a busca

	@Query(value = "select u from Container u where u.cliente like %?1%")
	List<Container> findByClientes(String cliente);

	/*
	 * @Query(value =
	 * "SELECT id, cliente, count(categoria) as exportacao FROM challenger1.container where categoria = 'exportacao' group by cliente;"
	 * , nativeQuery = true) List<Container> findByExportacao(String categoria);
	 * 
	 * @Query(value =
	 * "SELECT id, cliente, count(categoria) as importacao FROM challenger1.container where categoria = 'exportacao' group by cliente;"
	 * , nativeQuery = true) List<Container> findByImportacao(String categoria);
	 */


}
