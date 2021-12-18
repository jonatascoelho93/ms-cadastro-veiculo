package br.com.tinnova.mscadastroveiculo.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VeiculoVO {
	
	private Long id;
	private String modelo;
	private String marca;
	private Integer ano;
	private String descricao;
	private Boolean vendido;

}
