package br.com.letscode.java.view;

import br.com.letscode.java.dominio.Biblioteca;
import br.com.letscode.java.dominio.Emprestimo;
import br.com.letscode.java.dominio.Logger;
import br.com.letscode.java.services.BibliotecaService;

import javax.inject.Inject;

public class BibliotecaAplicacao {

    private final BibliotecaService bibliotecaService;
    private final Logger logger;


    @Inject
    public BibliotecaAplicacao(BibliotecaService bibliotecaService, Logger logger) {
        this.bibliotecaService = bibliotecaService;
        this.logger = logger;
    }

    public void realizarEmprestimo(Emprestimo emprestimo, Biblioteca biblioteca) {
        System.out.println(this.logger.getTempoFormatado());
        this.bibliotecaService.realizarEmprestimo(emprestimo, biblioteca);

    }

    public void realizarDevolucao(Emprestimo emprestimo, Biblioteca biblioteca) {
        this.bibliotecaService.realizarDevolucao(emprestimo, biblioteca);

    }


}
