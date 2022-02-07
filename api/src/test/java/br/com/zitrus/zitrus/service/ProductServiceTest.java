package br.com.zitrus.zitrus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

import br.com.zitrus.zitrus.model.Product;
import br.com.zitrus.zitrus.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	Gson gson = new Gson();
	@Mock
	ProductRepository repository;
	@InjectMocks
	ProductService service = new ProductService();

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldSaveProduct() {
		when(this.repository.save(Mockito.any())).thenReturn(createFakeProduct());
		Product product = createFakeProduct();
		assertEquals(gson.toJson(product), service.save(gson.toJson(product)));
	}

	@Test
	void shouldUpdateProduct() {
		Product product = createFakeProduct();
		when(this.repository.save(Mockito.any())).thenReturn(product);
		when(this.repository.findById(Mockito.any())).thenReturn(Optional.of(product));
		service.save(gson.toJson(product));
		assertEquals(ResponseEntity.ok().body(product), service.update(1, gson.toJson(product)));
	}

	@Test
	void shouldDeleteProduct(){
		Product product = createFakeProduct();
		when(this.repository.save(Mockito.any())).thenReturn(product);
		when(this.repository.findById(Mockito.any())).thenReturn(Optional.of(product));
		service.save(gson.toJson(product));
		assertEquals(ResponseEntity.ok().build(), service.delete(1));
	}

	@Test
	void shouldListAll() {
		List<Product> list = getFakeList();
		when(this.repository.findAll()).thenReturn(getFakeList());
		Iterable<Product> products = repository.findAll();
		assertEquals(list.size(), service.toList(products).size());
	}

	private Product createFakeProduct() {
		Product product = new Product();
		product.setId(1);
		product.setDescription("test");
		product.setQuantity(1);
		product.setPrice(1.0);
		product.setType(1);
		return product;
	}

	private List<Product> getFakeList(){
		List<Product> list = new ArrayList<>();
		Product product = createFakeProduct();
		Product product2 = createFakeProduct();
		product2.setId(2);
		list.add(product);
		list.add(product2);
		return list;
	}
}
