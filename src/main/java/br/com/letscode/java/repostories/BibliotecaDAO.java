package br.com.letscode.java.repostories;

import br.com.letscode.java.dominio.Livro;

import java.util.Arrays;
import java.util.List;

public class BibliotecaDAO {

    private List<Livro> livros = Arrays.asList(
            new Livro(
                    8550804622L, "Java efetivo", "Joshua Bloch", "Alta Books", true),
            new Livro(
                    8576082675L, "Código limpo", "Robert C. Martin", "Alta Books", true),
            new Livro(
                    8573933755L, "Estruturas De Dados E Algoritmos Em Java",
                    "Robert Lafore", "CIENCIA MODERNA", true),
            new Livro(
                    8573076100L, "Padrões de Projetos",
                    "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "Bookman", true),
            new Livro(
                    8575227246L, "Refatoração", "Martin Fowler", "Novatec Editora", true),
            new Livro(
                    8575223305L, "Introdução às Expressões Regulares",
                    "Michael Fitzgerald", "Novatec Editora", true)
    );

    public List<Livro> getLivros() {
        return this.livros;
    }
}
