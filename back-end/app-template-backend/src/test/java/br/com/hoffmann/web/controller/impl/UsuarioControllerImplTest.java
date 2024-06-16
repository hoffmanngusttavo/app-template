package br.com.hoffmann.web.controller.impl;

import br.com.hoffmann.TesteUnitario;
import br.com.hoffmann.model.component.UsuarioComponent;
import br.com.hoffmann.model.dto.UsuarioDTO;
import br.com.hoffmann.model.dto.input.UsuarioInputDTO;
import br.com.hoffmann.model.service.exception.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UsuarioControllerImplTest extends TesteUnitario {


    @Autowired
    private MockMvc mvc;
    @MockBean
    private UsuarioComponent component;
    private static final String path = "/api/v1/usuario";

    @Autowired
    private JacksonTester<Object> dadosSalvoJson;

    @Autowired
    private JacksonTester<UsuarioInputDTO> dadosInputJson;

    @Autowired
    private JacksonTester<UsuarioDTO> dadosOutputJson;

    @Test
    @DisplayName("Deveria devolver status 400 quando informações forem inválidas")
    void deveRetornarStatus400CastrarNovoCenario1() throws Exception {
        var response = mvc.perform(post(path))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Deveria retornar status 200 quando informações estão válidas")
    void deveRetornarStatus200SalvarUsuarioCenario1() throws Exception {

        var input = new UsuarioInputDTO();
        input.setLogin("gustavo@teste.com");
        input.setSenha("123");
        input.setCriadoPor("hoffmann");

        when(component.salvarNovo(any())).thenReturn(1L);

        var response = mvc.
                perform(
                        post(path)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosInputJson.write(input).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosSalvoJson
                .write(1L)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


    @Test
    @DisplayName("Deve retornar erro 404-not found por nao encontrar id")
    void deveDeletarCenario1() throws Exception {

        doThrow(EntityNotFoundException.class).when(component).deleteById(1L);

        var response = mvc.
                perform(delete(path+"/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }


    @Test
    @DisplayName("Deveria retornar status 200 quando existir na busca")
    void deveRetornarStatus200BuscarPorId() throws Exception {

        var user = new UsuarioDTO();
        user.setLogin("gustavo@teste.com");
        user.setSenha("123");
        user.setCriadoPor("hoffmann");

        when(component.findById(any(Long.class))).thenReturn(user);

        var response = mvc.perform(get(path+"/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosOutputJson
                .write(user)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}