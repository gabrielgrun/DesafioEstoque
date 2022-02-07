package br.com.zitrus.zitrus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.com.zitrus.zitrus.exception.NoBalanceException;
import br.com.zitrus.zitrus.model.Product;
import br.com.zitrus.zitrus.model.StockMovement;

public class StockMovementServiceTest {

	StockMovementService service = new StockMovementService();
	
	@Test
	void positiveBalanceShouldReturnNull() {
		Product product = new Product();
		StockMovement stock = new StockMovement();
		product.setQuantity(2);
		stock.setQuantity(1);
		assertNull(service.validateBalance(product, stock));
	}
	
	@Test
	void zeroBalanceShouldReturnNull() {
		Product product = new Product();
		StockMovement stock = new StockMovement();
		product.setQuantity(1);
		stock.setQuantity(1);
		assertNull(service.validateBalance(product, stock));
	}
	
	@Test
	void negativeBalanceShouldReturnNull() {
		Product product = new Product();
		StockMovement stock = new StockMovement();
		product.setQuantity(1);
		stock.setQuantity(2);
		assertEquals(NoBalanceException.MESSAGE, service.validateBalance(product, stock));
	}
}
