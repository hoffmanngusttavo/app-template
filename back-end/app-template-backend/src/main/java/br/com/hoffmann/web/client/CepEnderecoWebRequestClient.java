package br.com.hoffmann.web.client;

import br.com.hoffmann.model.dto.DadosEnderecoDetalhe;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CepEnderecoWebRequestClient extends WebRequestClient{

    @Override
    protected String getBaseUrl() {
        return "https://viacep.com.br/ws";
    }


    public DadosEnderecoDetalhe buscarDadosEnderecoByCep(String cep){
        var cepFormatado = cep.replaceAll("[^-?0-9]+", "");
        Mono<DadosEnderecoDetalhe> stringMono = get("/"+cepFormatado+"/json/", DadosEnderecoDetalhe.class);
        return stringMono.block();
    }

}
