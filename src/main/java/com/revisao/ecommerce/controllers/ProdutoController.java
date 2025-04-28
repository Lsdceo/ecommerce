package com.revisao.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService service;

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll(){
		List<ProdutoDTO> produtos = service.findAll();
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/pagina")
	public ResponseEntity<Page<ProdutoDTO>> findAll(Pageable pagina){
		Page<ProdutoDTO> produtos = service.findPagina(pagina);
		return ResponseEntity.ok(produtos);
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO dto){
		dto = service.insert(dto);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
		ProdutoDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}