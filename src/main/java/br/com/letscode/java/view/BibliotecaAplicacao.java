package br.com.letscode.java.view;

import br.com.letscode.java.dominio.Biblioteca;
import br.com.letscode.java.dominio.Emprestimo;
import br.com.letscode.java.services.BibliotecaService;
import br.com.letscode.java.services.LoggerService;

import javax.inject.Inject;

public class BibliotecaAplicacao {

    private BibliotecaService bibliotecaService;
    private LoggerService loggerService;


    @Inject
    public BibliotecaAplicacao(BibliotecaService bibliotecaService, LoggerService loggerService) {
        this.bibliotecaService = bibliotecaService;
        this.loggerService = loggerService;
    }

    public void realizarEmprestimo(Emprestimo emprestimo, Biblioteca biblioteca) {
        System.out.println(this.loggerService.getLogger().getTempoFormatado());
        this.bibliotecaService.realizarEmprestimo(emprestimo, biblioteca);

    }

    public void realizarDevolucao(Emprestimo emprestimo, Biblioteca biblioteca) {
        this.bibliotecaService.realizarDevolucao(emprestimo, biblioteca);

    }


}
