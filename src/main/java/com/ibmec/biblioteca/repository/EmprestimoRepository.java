package com.ibmec.biblioteca.repository;

import com.ibmec.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByLivroId(Long livroId);

    boolean existsByLivroId(Long livroId);
}