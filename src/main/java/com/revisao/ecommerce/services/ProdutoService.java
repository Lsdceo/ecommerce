package com.revisao.ecommerce.services;

import java.util.List;

import com.revisao.ecommerce.dto.CategoriaDTO;
import com.revisao.ecommerce.entities.Categoria;
import com.revisao.ecommerce.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.entities.Produto;
import com.revisao.ecommerce.repositories.ProdutoRepository;


@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repo;

	@Autowired
	CategoriaRepository repo1;
	
	public List<ProdutoDTO> findAll(){
		List<Produto> lista = repo.findAll();
		
		return lista.stream().map(x -> new ProdutoDTO(x)).toList();
	}
	
	public Page<ProdutoDTO> findPagina(Pageable pagina){
		Page<Produto> busca = repo.findAll(pagina);
		return busca.map(x -> new ProdutoDTO(x));
	}

	@Transactional
	public ProdutoDTO insert(ProdutoDTO dto){
		Produto entity = new Produto();
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setPreco(dto.getPreco());
		entity.setImgUrl(dto.getImgUrl());

		for (CategoriaDTO cDTO : dto.getCategorias()){

			Categoria cat = repo1.getReferenceById(cDTO.getId());
			entity.getCategorias().add(cat);
		}

		entity = repo.save(entity);
		return new ProdutoDTO(entity);
	}

	public ProdutoDTO findById(Long id) {
		Produto produto = repo.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		return new ProdutoDTO(produto);
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO dto) {
		Produto produto = repo.getReferenceById(id);
		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		produto.setImgUrl(dto.getImgUrl());

		produto.getCategorias().clear();
		for (CategoriaDTO cDTO : dto.getCategorias()) {
			Categoria cat = repo1.getReferenceById(cDTO.getId());
			produto.getCategorias().add(cat);
		}

		produto = repo.save(produto);
		return new ProdutoDTO(produto);
	}

	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);
	}

}
