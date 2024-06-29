package br.com.hoffmann.model.component.report;

import lombok.Getter;

public enum TipoExtensaoArquivo {

    PDF(".pdf"),
    XLSX(".xlsx"),
    CSV(".csv"),
    XML(".xml"),
    HTML(".html"),
    DOC(".doc");


    @Getter
    private final String extensao;

    private TipoExtensaoArquivo(String extensao){
        this.extensao = extensao;
    }

    public static TipoExtensaoArquivo getReportTypeByName(String fileType) {
        for (TipoExtensaoArquivo tipo : values()) {
            if(tipo.name().equalsIgnoreCase(fileType)){
                return tipo;
            }
        }
        return TipoExtensaoArquivo.PDF;
    }
}
