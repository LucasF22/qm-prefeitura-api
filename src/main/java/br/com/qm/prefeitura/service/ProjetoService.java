package br.com.qm.prefeitura.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.prefeitura.DTO.CriaProjetoDTO;
import br.com.qm.prefeitura.entity.Projeto;
import br.com.qm.prefeitura.entity.Secretaria;
import br.com.qm.prefeitura.exception.PrefeituraNegocioException;
import br.com.qm.prefeitura.repository.ProjetoRepository;
import br.com.qm.prefeitura.repository.SecretariaRepository;

@Service
public class ProjetoService {
	@Autowired
	ProjetoRepository projetoRepository;
	@Autowired
	SecretariaRepository secretariaRepository;
	
//	Endpoints Projeto
//	POST /secretarias/{idSecretaria}/projetos
//	Criar Projeto
//	Validar se a secretaria tem orcamento suficiente para pagar o projeto,
//	Alterar o orcamentoFolha para se adequar ao custo do projeto,
//	Criar um DTO para não receber o id, a data de conclusao, concluido
	public Projeto criaProjeto(long idSecretaria, CriaProjetoDTO criaProjetoDTO) throws PrefeituraNegocioException {

		if (criaProjetoDTO.getDataPrevistaConclusao().isBefore(LocalDate.now())) {
			throw new PrefeituraNegocioException("Erro ao criar um projeto: Data de previsão deve ser depois da data de hoje.");
		}

		Optional<Secretaria> secretariaOP = secretariaRepository.findById(idSecretaria);

		if (secretariaOP.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao criar um projeto: Secretaria inexistente.");
		}

		Secretaria secretaria = secretariaOP.get();

		double orcamentoAtual = secretaria.getOrcamentoProjeto();
		double custoProjeto = criaProjetoDTO.getCusto();

		if (custoProjeto > orcamentoAtual) {
			throw new PrefeituraNegocioException(
					"Erro ao criar um projeto: Orçamento é insuficiente, solicite um aporte!");
		}

		Projeto projeto = criaProjetoDTO.toEntity();

		secretaria.setOrcamentoProjeto(orcamentoAtual - custoProjeto);
		projeto.setSecretaria(secretaria);

		secretariaRepository.save(secretaria);
		return projetoRepository.save(projeto);
	}

//	GET /secretarias/{idSecretaria}/projetos
//	Listar projetos por secretaria
	public List<Projeto> listarProjetoPorSecretaria(Long idSecretaria){
		return projetoRepository.findAllBySecretariaIdSecretaria(idSecretaria);
	}
//	GET /{idSecretaria}/projetos/{idProjeto}
//	Consultar projeto
	public Projeto buscarProjeto(Long idSecretaria, Long idProjeto) throws PrefeituraNegocioException{
		Optional<Projeto> projetoOP  = projetoRepository.findByIdProjetoAndSecretariaIdSecretaria(idProjeto, idSecretaria);
		if(projetoOP.isEmpty()) {
			throw new PrefeituraNegocioException("Pojeto não encontrado. Verifique se ID Projeto ou Secretaria estão corretos.");
		}
		
		return projetoOP.get();
	}
//
//	GET /{idSecretaria}/projetos/concluidos
//	Listar projetos concluidos
	public List<Projeto> listarProjetosConcluidos(Long idSecretaria){
		
		return projetoRepository.findBySecretariaIdSecretariaAndConcluido(idSecretaria, true);
	}
//
//	GET /{idSecretaria}/projetos/execucao
//	Listar projetos em execução
	public List<Projeto> listarProjetosExecucao(Long idSecretaria){
		
		return projetoRepository.findBySecretariaIdSecretariaAndConcluido(idSecretaria, false);
	}
//
//	GET /{idSecretaria}/projetos/conclusao-mes/{mes}
//	Listar projetos que tem data de previsão para o mês (numérico)
public List<Projeto> listarProjetosComPrevisao(long idSecretaria, int mes) {

		int anoAtual = LocalDate.now().getYear();

		LocalDate dataInicio = LocalDate.of(anoAtual, mes, 1);
		LocalDate dataFim = LocalDate.of(anoAtual, mes, dataInicio.lengthOfMonth());

		return projetoRepository.findAllBySecretariaIdSecretariaAndDataPrevistaConclusaoBetweenAndConcluido(idSecretaria, dataInicio,
				dataFim, false);
	}

//	PUT /{idSecretaria}/projetos/{idProjeto}
//	Alterar a conclusão para true e salvar a data de conclusão como a data da chamada à este endpoint.	
	public Projeto concluirProjeto(long idSecretaria, long idProjeto) throws PrefeituraNegocioException {

	Optional<Projeto> projetoOP = projetoRepository.findByIdProjetoAndSecretariaIdSecretaria(idProjeto, idSecretaria);

	if (projetoOP.isEmpty()) {
		throw new PrefeituraNegocioException("Erro ao concluir um projeto: O projeto não existe.");
	}

	Projeto projeto = projetoOP.get();

	if (projeto.isConcluido()) {
		throw new PrefeituraNegocioException("Erro ao concluir um projeto: O projeto já foi concluído.");
	}

	projeto.setConcluido(true);
	projeto.setDataConclusao(LocalDate.now());

	return projetoRepository.save(projeto);
}
}
