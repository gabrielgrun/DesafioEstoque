package br.com.zitrus.zitrus.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.zitrus.zitrus.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query(value = "SELECT "
		+ " PRODUCT.ID, PRODUCT.DESCRIPTION,  PRODUCT.QUANTITY, SUM(STOCKMOVEMENT.QUANTITY ) AS SELL_QUANTITY"
		+ " FROM PRODUCT "
		+ " LEFT JOIN STOCKMOVEMENT ON (STOCKMOVEMENT.IDPRODUCT = PRODUCT.ID)"
		+ " WHERE STOCKMOVEMENT.TYPE = 1"
		+ " AND PRODUCT.TYPE = :type"
		+ " GROUP BY PRODUCT.ID, PRODUCT.DESCRIPTION,  PRODUCT.QUANTITY", nativeQuery = true)
	Collection<Object> findProductByType(@Param("type") Integer type);
	
	@Query(value = "SELECT "
			+ " PRODUCT.ID, PRODUCT.DESCRIPTION, SUM(STOCKMOVEMENT.QUANTITY) AS SELL_QUANTITY, SUM(STOCKMOVEMENT.VALUE - PRODUCT.PRICE) AS PROFIT"
			+ " FROM PRODUCT "
			+ " LEFT JOIN STOCKMOVEMENT ON (STOCKMOVEMENT.IDPRODUCT = PRODUCT.ID)"
			+ " WHERE STOCKMOVEMENT.TYPE = 1"
			+ " AND PRODUCT.ID = :id"
			+ " GROUP BY PRODUCT.ID, PRODUCT.DESCRIPTION", nativeQuery = true)
		Collection<Object> findProfitByProduct(@Param("id") Integer id);
}
