package com.challenger.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.challenger.crud.models.Container;
import com.challenger.crud.models.Movimento;

public interface MovimentoRepository extends CrudRepository<Movimento, Long> {

	Iterable<Movimento> findByContainer(Container container);

	Movimento findById(long id);
	List<Movimento> findByTipo(String tipo);

	// Query para a busca
	@Query(value = "select u from Movimento u where u.tipo like %?1%")
	List<Movimento> findByTiposMovimentos(String tipo);

}
