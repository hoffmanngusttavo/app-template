package br.com.hoffmann.model.component.report;

import lombok.Getter;
import org.springframework.core.io.ByteArrayResource;

@Getter
public class DownloadFile {


    private final ByteArrayResource resource;

    private final String nomeArquivo;

    public DownloadFile(byte[] conteudo, String nomeArquivo, TipoExtensaoArquivo tipo){
         this.resource = new ByteArrayResource(conteudo);
         this.nomeArquivo = nomeArquivo + tipo.getExtensao();
    }


    public Long getContentLength(){
        return resource.contentLength();
    }



}
