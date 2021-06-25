package br.com.letscode.java;

import br.com.letscode.java.view.BibliotecaRealizarDevolucaoView;
import br.com.letscode.java.view.BibliotecaRealizarEmprestimoView;

import javax.inject.Inject;

public class PropriedadesInjetadas {

    @Inject
    private BibliotecaRealizarEmprestimoView bibliotecaRealizarEmprestimoView;

    @Inject
    private BibliotecaRealizarDevolucaoView bibliotecaRealizarDevolucaoView;

    public BibliotecaRealizarEmprestimoView getBibliotecaView() {
        return this.bibliotecaRealizarEmprestimoView;
    }

    public BibliotecaRealizarDevolucaoView getBibliotecaRealizarDevolucaoView() {
        return this.bibliotecaRealizarDevolucaoView;
    }
}
