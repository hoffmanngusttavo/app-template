package br.com.hoffmann.model.component.report;

public enum TipoExtensaoArquivo {

    PDF,
    XLSX,
    CSV,
    XML,
    HTML,
    DOC;

    public static TipoExtensaoArquivo getReportTypeByName(String fileType) {
        for (TipoExtensaoArquivo tipo : values()) {
            if(tipo.name().equalsIgnoreCase(fileType)){
                return tipo;
            }
        }
        return TipoExtensaoArquivo.PDF;
    }
}
