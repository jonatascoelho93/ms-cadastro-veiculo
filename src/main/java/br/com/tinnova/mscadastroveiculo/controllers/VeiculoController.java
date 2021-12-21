package br.com.tinnova.mscadastroveiculo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tinnova.mscadastroveiculo.service.VeiculoService;
import br.com.tinnova.mscadastroveiculo.vo.VeiculoVO;

@CrossOrigin
@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

	public static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

	@Autowired
	VeiculoService veiculoService;

	@GetMapping
	public ResponseEntity<List<VeiculoVO>> getAll() {
		logger.info("Acessando GET veiculos");
		return ResponseEntity.ok(veiculoService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<VeiculoVO> getById(@PathVariable(name = "id") Long id) {
		logger.info("Acessando GET veiculos/" + id);
		return ResponseEntity.ok(veiculoService.getById(id));
	}

	@PostMapping
	public ResponseEntity<VeiculoVO> create(@RequestBody VeiculoVO veiculoVO) {
		logger.info("Acessando POST veiculos");
		return new ResponseEntity<>(veiculoService.create(veiculoVO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody VeiculoVO veiculoVO) {
		logger.info("Acessando PUT veiculos/" + id);
		veiculoService.update(id, veiculoVO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePatch(@PathVariable(name = "id") Long id, @RequestBody VeiculoVO veiculoVO) {
		logger.info("Acessando PATCH veiculos/" + id);
		veiculoService.updatePath(id, veiculoVO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		logger.info("Acessando DELETE veiculos/" + id);
		veiculoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
