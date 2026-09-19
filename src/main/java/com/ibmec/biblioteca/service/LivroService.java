package com.ibmec.biblioteca.service;

import com.ibmec.biblioteca.model.Livro;
import com.ibmec.biblioteca.repository.EmprestimoRepository;
import com.ibmec.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final EmprestimoRepository emprestimoRepository;

    public LivroService(LivroRepository repository, EmprestimoRepository emprestimoRepository) {
        this.repository = repository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public boolean existe(Long id) {
        return repository.existsById(id);
    }

    public Livro criar(Livro livro) {
        if (repository.existsByIsbn(livro.getIsbn())) {
            throw new IllegalArgumentException("Já existe um livro cadastrado com o ISBN " + livro.getIsbn());
        }
        livro.setId(null);
        return repository.save(livro);
    }

    public Optional<Livro> atualizar(Long id, Livro dados) {
        Optional<Livro> encontrado = repository.findById(id);
        if (encontrado.isEmpty()) {
            return Optional.empty();
        }
        if (repository.existsByIsbnAndIdNot(dados.getIsbn(), id)) {
            throw new IllegalArgumentException("Já existe um livro cadastrado com o ISBN " + dados.getIsbn());
        }
        Livro livro = encontrado.get();
        livro.setTitulo(dados.getTitulo());
        livro.setAutor(dados.getAutor());
        livro.setIsbn(dados.getIsbn());
        livro.setAnoPublicacao(dados.getAnoPublicacao());
        livro.setDisponivel(dados.isDisponivel());
        return Optional.of(repository.save(livro));
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        if (emprestimoRepository.existsByLivroId(id)) {
            throw new IllegalArgumentException("Não é possível excluir o livro " + id + " porque ele possui empréstimos registrados");
        }
        repository.deleteById(id);
        return true;
    }
}