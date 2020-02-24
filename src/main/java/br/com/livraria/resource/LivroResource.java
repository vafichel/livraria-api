package br.com.livraria.resource;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.dto.LivroDTO;
import br.com.livraria.entity.Livro;
import br.com.livraria.service.LivroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("livros")
public class LivroResource {

    @Autowired
    private LivroService service;
    
    
	@PostMapping
	@ApiOperation(value = "Inclui um novo livro")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Inclusão realizada com sucesso"),
            @ApiResponse(code =404, message ="Recurso nao encontrado"),
            @ApiResponse(code =500, message ="Erro ao efetuar inclusão")
    })
	public ResponseEntity<Livro> incluir(@RequestBody LivroDTO dto) {
		Livro livro = service.incluir(LivroDTO.convertFromLivro(dto));

		return new ResponseEntity<>(livro, HttpStatus.CREATED);
	}
	
	
    
	@PutMapping(path = { "/{id}" })
	@ApiOperation(value = "Atualiza o livro informado ")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Atualização realizada com sucesso"),
			@ApiResponse(code = 404, message = "Recurso nao encontrado"),
			@ApiResponse(code = 500, message = "Erro ao efetuar atualização") 
	})
	public Livro atualizar(@PathVariable long id, @RequestBody LivroDTO dto) {
		Optional<Livro> livroAtual = service.consultar(id);
		Livro livroAtualizado = new Livro();
		
		if (livroAtual.isPresent()) {
			livroAtualizado = service.atualizar(LivroDTO.convertFromLivro(dto));
		}
		return livroAtualizado;
	}
	
	

	@GetMapping
	@ApiOperation(value = "Lista todos os livros")
	@ApiResponses( value = {
            @ApiResponse(code = 200, message = "Consulta realizada com sucesso"),
            @ApiResponse(code =404, message ="Recurso nao encontrado"),
            @ApiResponse(code =500, message ="Erro ao efetuar consulta ")
	})
	public ResponseEntity<List<Livro>> listar() {
		List<Livro> livros = service.listar();
		
		return new ResponseEntity<>(livros, HttpStatus.OK);
	}
	
	
	
	@GetMapping(path = {"/{id}"})
	@ApiOperation(value = "Consulta um livro a partir do id informado")
	@ApiResponses( value = {
            @ApiResponse(code = 200, message = "Consulta realizada com sucesso"),
            @ApiResponse(code =404, message ="Recurso nao encontrado"),
            @ApiResponse(code =500, message ="Erro ao efetuar consulta ")
	})
	public ResponseEntity<Livro> consultar(@PathVariable long id) {
		Optional<Livro> livro = service.consultar(id);
		
		if (livro.isPresent()) {
			return new ResponseEntity<Livro>(livro.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(path = { "/{id}" })
	@ApiOperation(value = "Exclui um livro a partir do id informado")
	@ApiResponses( value = {
            @ApiResponse(code = 200, message = "Exclusão realizada com sucesso"),
            @ApiResponse(code =404, message ="Recurso nao encontrado"),
            @ApiResponse(code =500, message ="Erro ao efetuar exclusão")
	})
	public void excluir(@PathVariable long id) {
		Optional<Livro> livro = service.consultar(id);

		if (livro.isPresent()) {
			service.excluir(id);
		}
	}
	
	@GetMapping(path = {"/googlebooks/volumes"})
	@ApiOperation(value = "Consulta acervo fornecido pela api pública Google Books")
	@ApiResponses( value = {
            @ApiResponse(code = 200, message = "Consulta realizada com sucesso"),
            @ApiResponse(code =404, message ="Recurso nao encontrado"),
            @ApiResponse(code =500, message ="Erro ao efetuar consulta")
	})
	public ResponseEntity<String> consultarVolumes(@RequestParam("param") String param) throws URISyntaxException {
		String googleBooks = service.consultarGoogleBooks("q",param);
		
		return new ResponseEntity<String>(googleBooks, HttpStatus.OK);
	}
	
	


}
