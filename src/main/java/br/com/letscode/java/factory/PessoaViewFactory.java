package br.com.letscode.java.factory;

import br.com.letscode.java.dominio.PessoaEnum;
import br.com.letscode.java.view.PessoaView;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class PessoaViewFactory {

    @Inject
    @Any
    private Instance<PessoaView> pessoaViewInstance;

    public PessoaView create(final PessoaEnum pessoaEnum) {
        final PessoaViewAnnotationLiteral literal = new PessoaViewAnnotationLiteral(pessoaEnum);
        return this.pessoaViewInstance.select(literal).get();
    }


}
