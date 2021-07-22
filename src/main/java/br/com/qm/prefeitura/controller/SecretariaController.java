package br.com.qm.prefeitura.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qm.prefeitura.DTO.AumentaDTO;
import br.com.qm.prefeitura.DTO.CriarSecretariaDTO;
import br.com.qm.prefeitura.DTO.ResponseDTO;
import br.com.qm.prefeitura.DTO.SecretariaCriadaDTO;
import br.com.qm.prefeitura.entity.Secretaria;
import br.com.qm.prefeitura.exception.PrefeituraNegocioException;
import br.com.qm.prefeitura.service.SecretariaService;

@RestController
@RequestMapping("/secretarias")
public class SecretariaController {
	@Autowired
	SecretariaService secretariaService;
	
	@PostMapping
	public SecretariaCriadaDTO criarSecretaria(@Valid @RequestBody CriarSecretariaDTO criaSecretaria)throws PrefeituraNegocioException{
		return secretariaService.criarSecretaria(criaSecretaria);
	}
	@GetMapping
	public List<Secretaria> listarSecretaria(){
		return secretariaService.listarSecretaria();
	}
	@GetMapping(path = "/{idSecretaria}")
	public Optional<Secretaria> buscarSecretaria(@PathVariable long idSecretaria) throws PrefeituraNegocioException{
		return secretariaService.consultaSecretaria(idSecretaria);
	}
	@DeleteMapping(path = "/{idSecretaria}")
	public ResponseDTO removerSecretaria(@PathVariable long idSecretaria) throws PrefeituraNegocioException {
		return secretariaService.removeSecretaria(idSecretaria);
	}
//	GET /{idSecretaria}/folha-pagamento
//	Devolver a soma do salário de todos os funcionários dessa secretaria
	@GetMapping(path = "/{idSecretaria}/folha-pagamento")
	public ResponseDTO folhaPagamento(@PathVariable long idSecretaria)throws PrefeituraNegocioException {
		return secretariaService.folhaPagamento(idSecretaria);
	}
//	GET /{idSecretaria}/custo-projeto
//	Devolver a soma dos custos de todos os projetos
	@GetMapping(path = "/{idSecretaria}/custo-projeto")
	public ResponseDTO custoEmProjetos(@PathVariable long idSecretaria)throws PrefeituraNegocioException {
		return secretariaService.custosEmProjetos(idSecretaria);
	}
//	PUT /{idSecretaria}/aporte-projetos
//	Aumentar orcamento de projetos
//	receber um AumentoDTO{ "valorAumento": valor} no corpo
	@PutMapping(path = "/{idSecretaria}/aporte-projetos")
	public ResponseDTO realizaAporteProjetos(@PathVariable long idSecretaria,@Valid @RequestBody AumentaDTO aumentaDTO) throws PrefeituraNegocioException  {
		return secretariaService.aumentaAporteProjeto(idSecretaria, aumentaDTO);
	}
}
