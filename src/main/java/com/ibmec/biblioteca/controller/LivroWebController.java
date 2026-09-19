package com.ibmec.biblioteca.controller;

import com.ibmec.biblioteca.model.Livro;
import com.ibmec.biblioteca.service.EmprestimoService;
import com.ibmec.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/livros/pagina")
public class LivroWebController {

    private final LivroService service;
    private final EmprestimoService emprestimoService;

    public LivroWebController(LivroService service, EmprestimoService emprestimoService) {
        this.service = service;
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public String listar(Model model) {
        prepararPagina(model);
        model.addAttribute("livro", new Livro());
        return "livros";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("livro") Livro livro,
                        BindingResult resultado,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            prepararPagina(model);
            return "livros";
        }
        try {
            service.criar(livro);
        } catch (IllegalArgumentException excecao) {
            resultado.rejectValue("isbn", "isbn.duplicado", excecao.getMessage());
            prepararPagina(model);
            return "livros";
        }
        redirectAttributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso");
        return "redirect:/livros/pagina";
    }

    private void prepararPagina(Model model) {
        List<Livro> livros = service.listarTodos();
        Map<Long, Integer> totalEmprestimos = new HashMap<>();
        for (Livro item : livros) {
            totalEmprestimos.put(item.getId(), emprestimoService.listarPorLivro(item.getId()).size());
        }
        model.addAttribute("livros", livros);
        model.addAttribute("totalEmprestimos", totalEmprestimos);
        model.addAttribute("emprestimos", emprestimoService.listarTodos());
    }
}