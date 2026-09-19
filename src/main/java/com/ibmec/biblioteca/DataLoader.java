package com.ibmec.biblioteca;

import com.ibmec.biblioteca.model.Emprestimo;
import com.ibmec.biblioteca.model.Livro;
import com.ibmec.biblioteca.repository.EmprestimoRepository;
import com.ibmec.biblioteca.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    public DataLoader(LivroRepository livroRepository, EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Override
    public void run(String... args) {
        Livro aRepublica = livroRepository.save(
                new Livro("A República", "Platão", "9788525406262", 1965, false));
        Livro etica = livroRepository.save(
                new Livro("Ética a Nicômaco", "Aristóteles", "9788578274603", 1973, true));
        Livro meditacoes = livroRepository.save(
                new Livro("Meditações", "Marco Aurélio", "9788544001363", 2016, false));
        Livro discurso = livroRepository.save(
                new Livro("Discurso do Método", "René Descartes", "9788572329972", 2001, true));
        Livro assimFalou = livroRepository.save(
                new Livro("Assim Falou Zaratustra", "Friedrich Nietzsche", "9788525406729", 2011, true));

        Livro caminhoDosReis = livroRepository.save(
                new Livro("O Caminho dos Reis", "Brandon Sanderson", "9788576573418", 2010, false));
        Livro palavrasDeLuz = livroRepository.save(
                new Livro("Palavras de Luz", "Brandon Sanderson", "9788576576792", 2014, true));
        Livro imperioFinal = livroRepository.save(
                new Livro("O Império Final", "Brandon Sanderson", "9788576571872", 2006, true));
        Livro elantris = livroRepository.save(
                new Livro("Elantris", "Brandon Sanderson", "9788576573012", 2005, true));
        Livro narnia = livroRepository.save(
                new Livro("As Crônicas de Nárnia", "C. S. Lewis", "9788578270001", 2009, false));
        Livro cartasDiabo = livroRepository.save(
                new Livro("Cartas de um Diabo a seu Aprendiz", "C. S. Lewis", "9788578600914", 2017, true));
        Livro senhorDosAneis = livroRepository.save(
                new Livro("O Senhor dos Anéis", "J. R. R. Tolkien", "9788595084759", 2019, true));
        livroRepository.save(
                new Livro("O Hobbit", "J. R. R. Tolkien", "9788595084742", 2019, true));
        livroRepository.save(
                new Livro("O Silmarillion", "J. R. R. Tolkien", "9788595086111", 2019, true));

        Livro codigoLimpo = livroRepository.save(
                new Livro("Código Limpo", "Robert C. Martin", "9788576082675", 2009, false));
        Livro refatoracao = livroRepository.save(
                new Livro("Refatoração", "Martin Fowler", "9788575227244", 2020, true));
        Livro programadorPragmatico = livroRepository.save(
                new Livro("O Programador Pragmático", "Andrew Hunt", "9788577807758", 2010, true));
        livroRepository.save(
                new Livro("Padrões de Projeto", "Erich Gamma", "9788573076103", 2000, true));
        livroRepository.save(
                new Livro("Use a Cabeça! Java", "Kathy Sierra", "9788576081739", 2007, true));
        livroRepository.save(
                new Livro("Estruturas de Dados e Algoritmos em Java", "Robert Lafore", "9788575225417", 2004, true));

        emprestimoRepository.save(new Emprestimo(aRepublica, "Ana Souza",
                LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 17), true));
        emprestimoRepository.save(new Emprestimo(aRepublica, "Bruno Lima",
                LocalDate.of(2026, 9, 8), LocalDate.of(2026, 9, 22), false));
        emprestimoRepository.save(new Emprestimo(meditacoes, "Carla Dias",
                LocalDate.of(2026, 9, 5), LocalDate.of(2026, 9, 19), false));
        emprestimoRepository.save(new Emprestimo(etica, "Daniel Alves",
                LocalDate.of(2026, 7, 14), LocalDate.of(2026, 7, 28), true));
        emprestimoRepository.save(new Emprestimo(discurso, "Eduarda Nunes",
                LocalDate.of(2026, 8, 25), LocalDate.of(2026, 9, 8), true));
        emprestimoRepository.save(new Emprestimo(assimFalou, "Felipe Rocha",
                LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 15), true));

        emprestimoRepository.save(new Emprestimo(caminhoDosReis, "Gabriela Martins",
                LocalDate.of(2026, 9, 2), LocalDate.of(2026, 9, 16), false));
        emprestimoRepository.save(new Emprestimo(caminhoDosReis, "Henrique Costa",
                LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 24), true));
        emprestimoRepository.save(new Emprestimo(palavrasDeLuz, "Isabela Ramos",
                LocalDate.of(2026, 8, 12), LocalDate.of(2026, 8, 26), true));
        emprestimoRepository.save(new Emprestimo(imperioFinal, "João Pedro Silva",
                LocalDate.of(2026, 7, 30), LocalDate.of(2026, 8, 13), true));
        emprestimoRepository.save(new Emprestimo(narnia, "Larissa Prado",
                LocalDate.of(2026, 9, 9), LocalDate.of(2026, 9, 23), false));
        emprestimoRepository.save(new Emprestimo(elantris, "Marcos Vieira",
                LocalDate.of(2026, 8, 18), LocalDate.of(2026, 9, 1), true));
        emprestimoRepository.save(new Emprestimo(senhorDosAneis, "Natália Freitas",
                LocalDate.of(2026, 5, 20), LocalDate.of(2026, 6, 3), true));
        emprestimoRepository.save(new Emprestimo(cartasDiabo, "Otávio Barros",
                LocalDate.of(2026, 9, 11), LocalDate.of(2026, 9, 25), true));

        emprestimoRepository.save(new Emprestimo(codigoLimpo, "Paula Mendes",
                LocalDate.of(2026, 9, 4), LocalDate.of(2026, 9, 18), false));
        emprestimoRepository.save(new Emprestimo(codigoLimpo, "Rafael Antunes",
                LocalDate.of(2026, 7, 2), LocalDate.of(2026, 7, 16), true));
        emprestimoRepository.save(new Emprestimo(refatoracao, "Sofia Carvalho",
                LocalDate.of(2026, 8, 28), LocalDate.of(2026, 9, 11), true));
        emprestimoRepository.save(new Emprestimo(programadorPragmatico, "Thiago Moura",
                LocalDate.of(2026, 9, 7), LocalDate.of(2026, 9, 21), true));
    }}