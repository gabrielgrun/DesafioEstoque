package br.com.zitrus.zitrus.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zitrus.zitrus.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{}
