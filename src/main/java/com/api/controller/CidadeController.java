package com.api.controller;

import com.api.food.domain.exception.EntidadeNaoEncontradaException;
import com.api.food.domain.model.Cidade;
import com.api.food.domain.model.Restaurante;
import com.api.food.domain.repository.CidadeRepository;
import com.api.food.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> list() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        if (cidade.isPresent()) {
            return ResponseEntity.ok(cidade.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidade.salvar(cidade);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
        if (cidadeAtual.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        try {
            BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
            Cidade cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
            return ResponseEntity.ok(cidadeSalva);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cadastroCidade.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
