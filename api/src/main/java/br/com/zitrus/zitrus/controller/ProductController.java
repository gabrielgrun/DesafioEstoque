package br.com.zitrus.zitrus.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zitrus.zitrus.model.Product;
import br.com.zitrus.zitrus.service.ProductService;

@RestController
@RequestMapping(value = "/produto")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/list")
	public List<Product> getProducts() {
		return productService.listAll();
	}

	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Product> findById(@PathVariable Integer id){
		return productService.findById(id);
	}
	
	@GetMapping(path = {"/tipo/{type}"})
	public ResponseEntity<String> findProductByType(@PathVariable Integer type) throws SQLException{
		return ResponseEntity.ok().body(productService.findProductByType(type));
	}
	
	@GetMapping(path = {"/lucro/{id}"})
	public ResponseEntity<String> findProfitByProduct(@PathVariable Integer id) throws SQLException{
		return ResponseEntity.ok().body(productService.findProfitByProduct(id));
	}


	@PostMapping("")
	public String save(@RequestBody String json){
		return productService.save(json);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Integer id, @RequestBody String json) {
		return productService.update(id, json);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id){
		return productService.delete(id);
	}
}
