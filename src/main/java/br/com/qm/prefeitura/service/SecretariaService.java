package br.com.qm.prefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.prefeitura.DTO.AumentaDTO;
import br.com.qm.prefeitura.DTO.CriarSecretariaDTO;
import br.com.qm.prefeitura.DTO.ResponseDTO;
import br.com.qm.prefeitura.DTO.SecretariaCriadaDTO;
import br.com.qm.prefeitura.entity.Secretaria;
import br.com.qm.prefeitura.exception.PrefeituraNegocioException;
import br.com.qm.prefeitura.repository.ProjetoRepository;
import br.com.qm.prefeitura.repository.SecretariaRepository;
import br.com.qm.prefeitura.repository.ServidorRepository;

@Service
public class SecretariaService {
	@Autowired
	SecretariaRepository secretariaRepository;
	
	@Autowired
	ServidorRepository servidorRepository;
	
	@Autowired
	ProjetoRepository projetoRepository;
//	Endpoints Secretaria /secretarias
//	POST
//	Criar Secretaria
//	Validar existencia de outra com a mesma pasta
	
	public SecretariaCriadaDTO criarSecretaria(CriarSecretariaDTO criaSecretaria) throws PrefeituraNegocioException{
		if(secretariaRepository.findFirstByPasta(criaSecretaria.getPasta()) != null) {
			throw new PrefeituraNegocioException("Erro ao criar secretaria: Já existe pasta criada");
		}
		Secretaria secretaria = secretariaRepository.save(criaSecretaria.toEntity());
		return new SecretariaCriadaDTO(secretaria.getIdSecretaria(), secretaria.getNome(), 
				"Secretaria de "+ secretaria.getPasta().getDescricao() + " criada com sucesso!");
	}
//	GET  ok
//	Listar Secretarias
	public List<Secretaria> listarSecretaria(){
		return (List<Secretaria>) secretariaRepository.findAll();
	}
//	GET /{idSecretaria} ok
//	BuscarSecretaria pelo Id
	public Optional<Secretaria> consultaSecretaria(Long idSecretaria)throws PrefeituraNegocioException{
		if(!secretariaRepository.existsById(idSecretaria)) {
			throw new PrefeituraNegocioException("Secretaria não Existe!");
		}
		
		return secretariaRepository.findById(idSecretaria);
	}
	
//	DELETE /{idSecretaria}
//	Remover Secreatria
	public ResponseDTO removeSecretaria(Long idSecretaria) throws PrefeituraNegocioException{
		if(!secretariaRepository.existsById(idSecretaria)) {
			throw new PrefeituraNegocioException("Secretaria não Exite!");
		}
		secretariaRepository.deleteById(idSecretaria);
		return new ResponseDTO("Secretaria de ID "+idSecretaria+" foi removido com sucesso!");
	}
//	GET /{idSecretaria}/folha-pagamento
//	Devolver a soma do salário de todos os funcionários dessa secretaria
	public ResponseDTO folhaPagamento(Long idSecretaria) throws PrefeituraNegocioException {
		if(!secretariaRepository.existsById(idSecretaria)) {
			throw new PrefeituraNegocioException("Secretaria não Exite!");
		}
		Double totalFolha = secretariaRepository.somaSalarioServidor(idSecretaria);
		
		return new ResponseDTO("O custo toatal da Folha de pagamentos da Secreataria é: R$ "+totalFolha);
	}
//	GET /{idSecretaria}/custo-projeto
//	Devolver a soma dos custos de todos os projetos
	public ResponseDTO custosEmProjetos(Long idSecretaria)throws PrefeituraNegocioException{
		if(secretariaRepository.existsById(idSecretaria)){
			throw new PrefeituraNegocioException("Secretaria não Exite!");
		}
		Double totalProjetos = secretariaRepository.somaCustoProjetos(idSecretaria);
		
		return new ResponseDTO("O custo total em Projetos da Secretaria é de: R$ "+totalProjetos);
	}
//	PUT /{idSecretaria}/aporte-projetos
//	Aumentar orcamento de projetos
//	receber um AumentoDTO{ "valorAumento": valor} no corpo
	public ResponseDTO aumentaAporteProjeto(Long idSecretaria, AumentaDTO aumentoDTO) throws PrefeituraNegocioException{
		Optional<Secretaria> secretariaOP = secretariaRepository.findById(idSecretaria);
		
		if(secretariaOP.isEmpty()) {
			throw new PrefeituraNegocioException("Secretaria não Existe!");
		}
		
		Secretaria secretaria = secretariaOP.get();
		
		secretaria.setOrcamentoProjeto(secretaria.getOrcamentoProjeto() + aumentoDTO.getValorAumenta());
		
		secretariaRepository.save(secretaria);
		
		return new ResponseDTO("Aporte de R$ "+aumentoDTO.getValorAumenta()+" foi realizado com sucesso!"
				+ " O novo orçamento de projeto é de R$ "+secretaria.getOrcamentoProjeto()+".");
		
	}
}
