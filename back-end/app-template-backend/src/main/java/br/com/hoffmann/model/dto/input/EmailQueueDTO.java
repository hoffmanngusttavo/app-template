package br.com.hoffmann.model.dto.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Data
public class EmailQueueDTO implements Serializable {


    private String emailFrom;

    private String emailTo;

    private String subject;

    private String message;

}
