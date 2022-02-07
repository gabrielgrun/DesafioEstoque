package br.com.zitrus.zitrus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zitrus.zitrus.exception.NoBalanceException;
import br.com.zitrus.zitrus.service.StockMovementService;

@RestController
@RequestMapping(value = "/estoque")
public class StockMovementController {
	private final StockMovementService stockService;

	public StockMovementController(StockMovementService stockService) {
		this.stockService = stockService;
	}

	@PostMapping("")
	public  ResponseEntity<String>  save(@RequestBody String json) throws NoBalanceException{
		return stockService.save(json);
	}
}
