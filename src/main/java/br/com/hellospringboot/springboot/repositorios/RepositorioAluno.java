package br.com.hellospringboot.springboot.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hellospringboot.springboot.entidades.Aluno;

public interface RepositorioAluno extends JpaRepository<Aluno, Long>{
	List<Aluno> findByNomeContaining(String nome);
}
