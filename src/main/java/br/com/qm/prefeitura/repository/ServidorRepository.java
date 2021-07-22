package br.com.qm.prefeitura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.qm.prefeitura.entity.Servidor;

public interface ServidorRepository extends CrudRepository<Servidor, Long>{
	
	List<Servidor> findAllBySecretariaIdSecretaria(Long idSecretaria);
	Optional<Servidor> findByIdServidorAndSecretariaIdSecretaria(Long idServidor, Long idSecretaria);
	
}
