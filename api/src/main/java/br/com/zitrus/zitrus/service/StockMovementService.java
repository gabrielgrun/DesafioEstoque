package br.com.zitrus.zitrus.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.zitrus.zitrus.exception.NoBalanceException;
import br.com.zitrus.zitrus.model.Product;
import br.com.zitrus.zitrus.model.StockMovement;
import br.com.zitrus.zitrus.repository.ProductRepository;
import br.com.zitrus.zitrus.repository.StockMovementRepository;

@Service
public class StockMovementService {

	@Autowired
	private StockMovementRepository repository;
	@Autowired
	private ProductRepository productRepository;
	
	private Gson gson = new GsonBuilder().serializeNulls().create();
	
	public ResponseEntity<String> save(String json) {
		StockMovement stock = gson.fromJson(json, StockMovement.class);
		Optional<Product> optionalProduct = productRepository.findById(stock.getProduct().getId());
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			if(stock.getType() == 1) {
				String message = validateBalance(product, stock);
				if(message != null) {
					return ResponseEntity.ok().body(message);
				}
				product.setQuantity(product.getQuantity() - stock.getQuantity());
			} else if(stock.getType() == 0) {
				product.setQuantity(product.getQuantity() + stock.getQuantity());
			}

			stock.setDate(new Date());
			repository.save(stock);
			productRepository.save(product);
		}
		return ResponseEntity.ok().body(gson.toJson(stock));
	}

	protected String validateBalance(Product product, StockMovement stock) {
		if(product.getQuantity() - stock.getQuantity() < 0) {
			return NoBalanceException.MESSAGE;
		}
		return null;
	}
}
