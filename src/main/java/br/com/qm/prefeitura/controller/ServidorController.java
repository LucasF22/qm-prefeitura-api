package br.com.qm.prefeitura.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qm.prefeitura.DTO.CriaServidorDTO;
import br.com.qm.prefeitura.DTO.ResponseDTO;
import br.com.qm.prefeitura.entity.Servidor;
import br.com.qm.prefeitura.exception.PrefeituraNegocioException;
import br.com.qm.prefeitura.service.ServidorService;

@RestController
@RequestMapping(path = "/secretarias/{idSecretaria}/servidores")
public class ServidorController {
	@Autowired
	ServidorService servidorService;
		
//	POST /secretarias/{idSecretaria}/servidores
//	Criar Servidor
//	Validar se a secretaria tem orcamento suficiente para pagar o salário,
//	Alterar o orcamentoFolha para se adequar ao salário do servidor
	@PostMapping
	public Servidor cadastraServidor(@PathVariable long idSecretaria, @Valid @RequestBody CriaServidorDTO servidor) throws PrefeituraNegocioException{
		return servidorService.adicionaServidor(idSecretaria, servidor);
	}

//	GET /secretarias/{idSecretaria}/servidores
//	Listar servidores por secretaria
	@GetMapping
	public List<Servidor> listarServidor(@PathVariable long idSecretaria){
		return servidorService.listarServidoresPorSecretaria(idSecretaria);
	}
//	GET /secretarias/{idSecretaria}/servidores/{idServidor}
//	Consultar um servidor
	@GetMapping(path = "/{idServidor}")
	public Servidor buscarServidor(@PathVariable long idSecretaria, @PathVariable long idServidor) throws PrefeituraNegocioException{
		return servidorService.buscarServidor(idSecretaria, idServidor);
	}
//	DELETE /secretarias/{idSecretaria}/servidores/{idServidor}
//	Remover um servidor
//	alterar o orcamentoFolha para remover o gasto com esse servidor
	@DeleteMapping(path = "/idServidor")
	public ResponseDTO removeServidor(@PathVariable long idSecretaria, @PathVariable long idServidor) throws PrefeituraNegocioException{
		return servidorService.removeServidor(idSecretaria, idServidor);
	}
}
