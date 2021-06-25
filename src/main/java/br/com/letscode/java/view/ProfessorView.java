package br.com.letscode.java.view;

import br.com.letscode.java.annotation.TipoPessoa;
import br.com.letscode.java.dominio.Pessoa;
import br.com.letscode.java.dominio.PessoaEnum;
import br.com.letscode.java.dominio.Professor;

import java.util.Scanner;

@TipoPessoa(PessoaEnum.PROFESSOR)
public class ProfessorView implements PessoaView {

    public Pessoa create() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome do professor");
        String nome = sc.next();
        System.out.println("Informe a matricula do professor");
        String matricula = sc.next();
        System.out.println("Informe o email do professor");
        String email = sc.next();

        return new Professor(nome, matricula, email);
    }
}
