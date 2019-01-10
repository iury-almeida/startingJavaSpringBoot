package br.com.hellospringboot.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.hellospringboot.springboot.entidades.Aluno;
import br.com.hellospringboot.springboot.entidades.Instituicao;
import br.com.hellospringboot.springboot.repositorios.RepositorioAluno;
import br.com.hellospringboot.springboot.repositorios.RepositorioInstituicao;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private RepositorioAluno repositorioAluno;
	
	@Autowired
	private RepositorioInstituicao repositorioInstituicao;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("aluno/index");
		List<Aluno> alunos = repositorioAluno.findAll();
		resultado.addObject("alunos", alunos);
		return resultado;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("aluno/inserir");
		Aluno aluno = new Aluno();
		aluno.setInstituicao(new Instituicao());
		resultado.addObject("aluno", new Aluno());
		resultado.addObject("instituicoes", repositorioInstituicao.findAll());
		return resultado;
	}
	
	@PostMapping("/inserir")
	public String inserir(Aluno aluno) {
		repositorioAluno.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Aluno aluno = repositorioAluno.getOne(id);
		ModelAndView resultado = new ModelAndView("aluno/editar");
		resultado.addObject("aluno", aluno);
		resultado.addObject("instituicoes", repositorioInstituicao.findAll());
		return resultado;
	}
	
	@PostMapping("/editar")
	public String editar(Aluno aluno) {
		repositorioAluno.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioAluno.delete(id);
		return "redirect:/alunos/index";
	}
	
	@GetMapping({"/pesquisar-por-nome/{nome}", "/pesquisar-por-nome"})
	public @ResponseBody List<Aluno> pesquisarPorNome(@PathVariable Optional<String> nome) {
		if (nome.isPresent())
			return repositorioAluno.findByNomeContaining(nome.get());
		else
			return repositorioAluno.findAll();
	}
	
}
