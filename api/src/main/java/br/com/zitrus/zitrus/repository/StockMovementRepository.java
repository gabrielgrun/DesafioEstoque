package br.com.zitrus.zitrus.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zitrus.zitrus.model.StockMovement;

public interface StockMovementRepository extends CrudRepository<StockMovement, Integer> {

}
