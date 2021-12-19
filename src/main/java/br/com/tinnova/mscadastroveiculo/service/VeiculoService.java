package br.com.tinnova.mscadastroveiculo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tinnova.mscadastroveiculo.entities.VeiculoEntity;
import br.com.tinnova.mscadastroveiculo.repositories.VeiculoRepository;
import br.com.tinnova.mscadastroveiculo.vo.VeiculoVO;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public List<VeiculoVO> getAll() {
		List<VeiculoEntity> veiculos = veiculoRepository.findAll();
		
		
		System.out.println(veiculos.stream().map(v -> coverterToVO(v)).collect(Collectors.toList()));
		
		
		return veiculos.stream().map(v -> coverterToVO(v)).collect(Collectors.toList());
	}

	public VeiculoVO getById(Long id) {
		return coverterToVO(veiculoRepository.getById(id));
	}

	public VeiculoVO create(VeiculoVO veiculoVO) {
		VeiculoEntity veiculoEntity = coverterToEntity(veiculoVO);
		veiculoEntity.setDataCriacao(LocalDateTime.now());
		return coverterToVO(veiculoRepository.save(veiculoEntity));
	}

	public void update(Long id, VeiculoVO veiculoVO) {
		VeiculoEntity veiculoEntity = veiculoRepository.getById(id);
		veiculoEntity.setDataAtualizacao(LocalDateTime.now());
		veiculoEntity.setModelo(veiculoVO.getModelo());
		veiculoEntity.setMarca(veiculoVO.getMarca());
		veiculoEntity.setAno(veiculoVO.getAno());
		veiculoEntity.setDescricao(veiculoVO.getDescricao());
		veiculoEntity.setVendido(veiculoVO.getVendido());
		veiculoRepository.save(veiculoEntity);
	}

	public void updatePath(Long id, VeiculoVO veiculoVO) {
		VeiculoEntity veiculoEntity = veiculoRepository.getById(id);
		veiculoEntity.setDataAtualizacao(LocalDateTime.now());
		if (StringUtils.isNotBlank(veiculoVO.getModelo()))
			veiculoEntity.setModelo(veiculoVO.getModelo());
		if (StringUtils.isNotBlank(veiculoVO.getMarca()))
			veiculoEntity.setMarca(veiculoVO.getMarca());
		if (veiculoVO.getAno() != null)
			veiculoEntity.setAno(veiculoVO.getAno());
		if (StringUtils.isNotBlank(veiculoVO.getDescricao()))
			veiculoEntity.setDescricao(veiculoVO.getDescricao());
		if (veiculoVO.getVendido() != null)
			veiculoEntity.setVendido(veiculoVO.getVendido());
		veiculoRepository.save(veiculoEntity);
	}

	public void delete(Long id) {
		veiculoRepository.deleteById(id);
	}

	private VeiculoVO coverterToVO(VeiculoEntity entity) {
		return VeiculoVO.builder().id(entity.getId()).modelo(entity.getModelo()).marca(entity.getMarca())
				.ano(entity.getAno()).descricao(entity.getDescricao()).vendido(entity.getVendido()).build();
	}

	private VeiculoEntity coverterToEntity(VeiculoVO vo) {
		return VeiculoEntity.builder().id(vo.getId()).modelo(vo.getModelo()).marca(vo.getMarca()).ano(vo.getAno())
				.descricao(vo.getDescricao()).vendido(vo.getVendido()).build();
	}

}
