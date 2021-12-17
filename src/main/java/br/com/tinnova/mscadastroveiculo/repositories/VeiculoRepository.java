package br.com.tinnova.mscadastroveiculo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tinnova.mscadastroveiculo.entities.VeiculoEntity;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

}
