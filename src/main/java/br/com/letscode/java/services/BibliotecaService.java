package br.com.letscode.java.services;

import br.com.letscode.java.dominio.Biblioteca;
import br.com.letscode.java.dominio.Emprestimo;

import java.util.List;

public interface BibliotecaService {
    public void realizarEmprestimo(Emprestimo emprestimo, Biblioteca biblioteca);
    public void realizarDevolucao(Emprestimo emprestimo, Biblioteca biblioteca);
}