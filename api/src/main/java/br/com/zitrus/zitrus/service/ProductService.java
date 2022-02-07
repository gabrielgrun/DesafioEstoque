package br.com.zitrus.zitrus.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.zitrus.zitrus.model.Product;
import br.com.zitrus.zitrus.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	private Gson gson = new GsonBuilder().serializeNulls().create();

	public List<Product> listAll() {
		Iterable<Product> products = repository.findAll();
		return toList(products);
	}

	public ResponseEntity<Product> findById(Integer id){
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	public String save(String json) {
		Product product = gson.fromJson(json, Product.class);
		repository.save(product);
		return gson.toJson(product);
	}

	public ResponseEntity<Product> update(Integer id, String json) {
		Product product = gson.fromJson(json, Product.class);
		return repository.findById(id)
				.map(record -> {
					record.setDescription(product.getDescription());
					record.setPrice(product.getPrice());
					record.setQuantity(product.getQuantity());
					record.setType(product.getType());
					Product updated = repository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity <Object> delete(Integer id) {
		return repository.findById(id)
				.map(product -> {
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
	
	public String findProductByType(Integer type){
		return gson.toJson(repository.findProductByType(type));
	}
	
	public String findProfitByProduct(Integer id){
		return gson.toJson(repository.findProfitByProduct(id));
	}

	private <E> List<E> toList(Iterable<E> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}
}
