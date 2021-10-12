package com.betha.admin.resources;

import com.betha.admin.model.Saida;
import com.betha.admin.model.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
public class SaidaDto {

    @Column
    private Long id;

    @Column
    private LocalDate data;

    @Column
    private Double valor;

    @Column
    private TipoTransacao tipoTransacao;

    @Column
    private String descricao;

    public static SaidaDto toDTO (Saida saida) {
        SaidaDto dto = new SaidaDto();
        dto.setId(saida.getId());
        dto.setData(saida.getData());
        dto.setDescricao(saida.getDescricao());
        dto.setValor(saida.getValor());
        dto.setTipoTransacao(saida.getTipoTransacao());
        return dto;
    }

    public static Saida fromDTO (SaidaDto saidaDto) {
        Saida entity = new Saida();
        entity.setData(saidaDto.getData());
        entity.setValor(saidaDto.getValor());
        entity.setTipoTransacao(saidaDto.getTipoTransacao());
        entity.setDescricao(saidaDto.getDescricao());
        return entity;
    }

}
