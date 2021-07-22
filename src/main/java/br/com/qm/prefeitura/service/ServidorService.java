package br.com.qm.prefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.prefeitura.DTO.CriaServidorDTO;
import br.com.qm.prefeitura.DTO.ResponseDTO;
import br.com.qm.prefeitura.entity.Secretaria;
import br.com.qm.prefeitura.entity.Servidor;
import br.com.qm.prefeitura.exception.PrefeituraNegocioException;
import br.com.qm.prefeitura.repository.SecretariaRepository;
import br.com.qm.prefeitura.repository.ServidorRepository;

@Service
public class ServidorService {
	@Autowired
	ServidorRepository servidorRepository;
	@Autowired
	SecretariaRepository secretariaRepository;
//	Endpoints Servidor
//
//	POST /secretarias/{idSecretaria}/servidores
//	Criar Servidor
//	Validar se a secretaria tem orcamento suficiente para pagar o salário,
//	Alterar o orcamentoFolha para se adequar ao salário do servidor
	public Servidor adicionaServidor(Long idSecretaria, CriaServidorDTO criaServidor) throws PrefeituraNegocioException{
		
		Optional<Secretaria> secretariaOP = secretariaRepository.findById(idSecretaria);
		if(secretariaOP.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao criar Servidor: Secretaria não existe");
		}
		Secretaria secretaria = secretariaOP.get();
		double orcamentoAtual = secretaria.getOrcamentoFolha();
		double salarioServidor = criaServidor.getSalario();
		
		if(orcamentoAtual < salarioServidor) {
			throw new PrefeituraNegocioException("Erro ao criar Servidor: Não há orçamento para contatar Servidor.");
		}
		
		Servidor servidor = criaServidor.toEntity();
		servidor.setSecretaria(secretaria);
		
		secretaria.setOrcamentoFolha(orcamentoAtual - salarioServidor);
		secretariaRepository.save(secretaria);
		//servidorRepository.save(servidor);
		return servidorRepository.save(servidor);
	}
	
//	GET /secretarias/{idSecretaria}/servidores
//	Listar servidores por secretaria
	public List<Servidor> listarServidoresPorSecretaria(Long idSecretaria) {
		return servidorRepository.findAllBySecretariaIdSecretaria(idSecretaria);
	}

//	GET /secretarias/{idSecretaria}/servidores/{idServidor}
//	Consultar um servidor
	public Servidor buscarServidor(Long idSecretaria, Long idServidor)throws PrefeituraNegocioException{
		Optional<Servidor> servidorOP = servidorRepository.findByIdServidorAndSecretariaIdSecretaria(idServidor, idSecretaria);
		if(servidorOP.isEmpty()) {
			throw new PrefeituraNegocioException("Servidor não encotrado. Verifique se ID de sevidor ou secretaria estão corretos.");
		}
		return servidorOP.get();
	}

//	DELETE /secretarias/{idSecretaria}/servidores/{idServidor}
//	Remover um servidor
//	alterar o orcamentoFolha para remover o gasto com esse servidor
	public ResponseDTO removeServidor(Long idSecretaria, Long idServidor) throws PrefeituraNegocioException{
		Optional<Servidor> servidorDEL = servidorRepository.findByIdServidorAndSecretariaIdSecretaria(idServidor, idSecretaria);
		
		if(servidorDEL.isEmpty()) {
			throw new PrefeituraNegocioException("Servidor não encontrado!");
		}
		Optional<Secretaria> secretariaOpt = secretariaRepository.findById(idSecretaria);
		
		if (secretariaOpt.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao remover servidor: Secretaria não encontrada.");
		}
		
		Secretaria secretaria = secretariaOpt.get();
		secretaria.setOrcamentoFolha(secretaria.getOrcamentoFolha() + servidorDEL.get().getSalario());
		secretariaRepository.save(secretaria);
		servidorRepository.deleteById(idServidor);
		
		return new ResponseDTO("Servidor removido com sucesso!");
	}

}
