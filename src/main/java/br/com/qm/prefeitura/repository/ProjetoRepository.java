package br.com.qm.prefeitura.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.qm.prefeitura.entity.Projeto;

public interface ProjetoRepository extends CrudRepository<Projeto, Long>{
	List<Projeto> findAllBySecretariaIdSecretaria(Long idSecretaria);
	Optional<Projeto> findByIdProjetoAndSecretariaIdSecretaria(Long idProjeto, Long idSecretaria);
	List<Projeto> findBySecretariaIdSecretariaAndConcluido(Long idSecretaria, Boolean concluido);
	
	List<Projeto> findAllBySecretariaIdSecretariaAndDataPrevistaConclusaoBetweenAndConcluido(Long idSecretaria, LocalDate inicioDoMes, LocalDate fimDoMes, Boolean concluido);
	
//	@Query("select p from Projeto p where p.secretaria.idSecretaria = ?1 and month(p.dataPrevistaConclusao) = ?2 and p.concluido = false")
//	List<Projeto> findAllMes(Long idSecretaria, int mes);
}
