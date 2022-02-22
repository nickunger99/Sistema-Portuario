package com.challenger.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.challenger.crud.models.Container;

public interface ContainerRepository extends CrudRepository<Container, Long> {

	Container findById(long id);
	
	Container findByCliente(String cliente);
	
	Container findByNumero(String numero);
	//Query para a busca
	
	@Query(value = "select u from Container u where u.cliente like %?1%")
	List<Container> findByClientes(String cliente);
	
}
