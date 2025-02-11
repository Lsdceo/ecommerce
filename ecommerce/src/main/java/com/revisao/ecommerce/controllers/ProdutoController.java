package com.revisao.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.entities.Produto;
import com.revisao.ecommerce.repositories.ProdutoRepository;

@RestController
@RequestMapping(value = "produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repo;


    @GetMapping(value = "/all")
    public ResponseEntity <List<Produto>> mostrarTodos(){
        List<Produto> prod = repo.findAll();
        return ResponseEntity.ok(prod);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@RequestBody Long id){
        Produto prod = repo.getById(id);
        return ResponseEntity.ok(prod);
    }

}