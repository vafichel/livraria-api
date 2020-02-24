package br.com.livraria.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.livraria.entity.Livro;
import br.com.livraria.repository.LivroRepository;
import br.com.livraria.util.LivrariaUtil;

@Service
public class LivroService {
	
	@Autowired
	LivroRepository repository;
	
	@Value("${api.google.books.url}")
	String url;
	
	@Value("${api.google.books.credentials}")
	String credencial;
	
	private final String concat = "&"; 
	
	public Livro incluir(Livro livro) {
		return repository.save(livro);
	}
	
	public Livro atualizar(Livro livro) {
		return repository.save(livro);
	}
	
	public List<Livro> listar(){
		List<Livro> livros = repository.findAll();
		return livros;
	}
	
	public Optional<Livro> consultar(Long id){
		Optional<Livro> livro = repository.findById(id);
		return livro;
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public String consultarGoogleBooks(String key , String value) throws URISyntaxException{
		
		RestTemplate restTemplate = new RestTemplate();
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set(key, value);
		
		String param = LivrariaUtil.montarParametro(key , value);
		String autenticKey = LivrariaUtil.montarParametro("key" , credencial);
		
		URI uri = new URI(url + param+ concat + autenticKey );

		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		
		//System.out.println(response);
		
		return response.getBody();
	}

}
