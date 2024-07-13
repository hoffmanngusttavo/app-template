package br.com.hoffmann.model.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;


@Getter
public class PageableResponseDTO implements Serializable {

    private List<?> content;

    private boolean hasContent;

    private int pageNumber;
    private int pageSize;

    private int totalPages;
    private long totalElements;


    public PageableResponseDTO(Page<?> pageSpring) {
        this.content = pageSpring.getContent();
        this.hasContent = pageSpring.hasContent();
        this.totalPages = pageSpring.getTotalPages();
        this.totalElements = pageSpring.getTotalElements();
        this.pageNumber = pageSpring.getPageable().getPageNumber();
        this.pageSize = pageSpring.getPageable().getPageSize();
    }
}
