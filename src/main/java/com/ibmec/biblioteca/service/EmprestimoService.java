package com.ibmec.biblioteca.service;

import com.ibmec.biblioteca.model.Emprestimo;
import com.ibmec.biblioteca.model.Livro;
import com.ibmec.biblioteca.repository.EmprestimoRepository;
import com.ibmec.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository repository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository repository, LivroRepository livroRepository) {
        this.repository = repository;
        this.livroRepository = livroRepository;
    }

    public List<Emprestimo> listarTodos() {
        return repository.findAll();
    }

    public List<Emprestimo> listarPorLivro(Long livroId) {
        return repository.findByLivroId(livroId);
    }

    public Emprestimo criar(Emprestimo emprestimo) {
        if (emprestimo.getLivro() == null || emprestimo.getLivro().getId() == null) {
            throw new IllegalArgumentException("O livro do empréstimo é obrigatório");
        }
        Long livroId = emprestimo.getLivro().getId();
        Optional<Livro> livro = livroRepository.findById(livroId);
        if (livro.isEmpty()) {
            throw new IllegalArgumentException("Livro não encontrado: " + livroId);
        }
        if (emprestimo.getDataPrevistaDevolucao().isBefore(emprestimo.getDataRetirada())) {
            throw new IllegalArgumentException("A data prevista de devolução não pode ser anterior à data de retirada");
        }
        emprestimo.setId(null);
        emprestimo.setLivro(livro.get());
        return repository.save(emprestimo);
    }
}