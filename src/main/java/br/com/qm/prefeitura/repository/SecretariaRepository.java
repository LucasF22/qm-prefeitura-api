package br.com.qm.prefeitura.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.qm.prefeitura.entity.Secretaria;
import br.com.qm.prefeitura.enums.Pasta;

public interface SecretariaRepository extends CrudRepository<Secretaria, Long> {
	Secretaria findFirstByPasta(Pasta pasta);
	@Query("select sum(s.salario) from Servidor s where s.secretaria.idSecretaria = ?1")
	Double somaSalarioServidor(Long idSecretaria);
	@Query("select sum(p.custo) from Projeto p where p.secretaria.idSecretaria = ?1")
	Double somaCustoProjetos(Long idSecretaria);
}
