package br.com.letscode.java.view;

import br.com.letscode.java.dominio.*;
import br.com.letscode.java.excecoes.ExcedeuQuantidadeMaximaLivrosException;
import br.com.letscode.java.factory.PessoaViewFactory;
import br.com.letscode.java.services.BibliotecaService;

import javax.inject.Inject;

public class BibliotecaRealizarDevolucaoView {

    @Inject
    private BibliotecaService bibliotecaService;

    @Inject
    private PessoaViewFactory pessoaViewFactory;

    @Inject
    private Logger logger;


    public void realizarDevolucao(PessoaEnum pessoaEnum) throws ExcedeuQuantidadeMaximaLivrosException {
        Pessoa aluno = this.pessoaViewFactory.create(pessoaEnum).create();
        Biblioteca biblioteca = new Biblioteca();
        var emprestimo = new Emprestimo(aluno, new Livro[]{new Livro()});
        this.bibliotecaService.realizarDevolucao(emprestimo, biblioteca);
        System.out.println(this.logger.getTempoFormatado());

    }

}
