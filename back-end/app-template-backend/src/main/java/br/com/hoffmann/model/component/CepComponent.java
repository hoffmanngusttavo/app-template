package br.com.hoffmann.model.component;

import br.com.hoffmann.model.dto.DadosEnderecoDetalhe;
import br.com.hoffmann.web.client.CepEnderecoWebRequestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CepComponent {

    private final CepEnderecoWebRequestClient client;

    public DadosEnderecoDetalhe buscarCep(String cep) {
        return client.buscarDadosEnderecoByCep(cep);
    }



}
