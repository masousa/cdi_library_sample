package br.com.letscode.java.view;

import br.com.letscode.java.annotation.TipoPessoa;
import br.com.letscode.java.dominio.Aluno;
import br.com.letscode.java.dominio.Pessoa;
import br.com.letscode.java.dominio.PessoaEnum;

import java.util.Scanner;

@TipoPessoa(PessoaEnum.ALUNO)
public class AlunoView implements PessoaView {

    public Pessoa create() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome do aluno");
        String nome = sc.next();
        System.out.println("Informe a matricula do aluno");
        String matricula = sc.next();
        System.out.println("Informe o email do aluno");
        String email = sc.next();

        return new Aluno(nome, matricula, email);
    }
}
