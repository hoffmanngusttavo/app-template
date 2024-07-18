package br.com.hoffmann.model.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable {

    private String emailFrom;

    private String emailTo;

    private String subject;

    private String message;

}
