package br.com.livraria.util;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.livraria.dto.LivroDTO;
import br.com.livraria.entity.Livro;
import br.com.livraria.repository.LivroRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CargaDadosInicial {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Bean
	public String inicioCarga() {
		log.info("INICIANDO CARGA DE DADOS");
		cargaLivros();
	
		return "";
	}
	
	

	/**
	 * Iniciando carga do arquivo livros.json
	 */
	private void cargaLivros() {
		Reader reader = lerArquivo("src/main/resources/livros.json");
		List<LivroDTO> livrosDTO = new ArrayList<>();
		List<Livro> livros = new ArrayList<>();

		if (reader != null) {
			livrosDTO = deserialize(reader);
		} else {
			log.info("NÃO FOI POSSÍVEL LER O ARQUIVO");
		}

		for (LivroDTO itemLivroDto : livrosDTO) {
			Livro livro = new Livro();

			livro = LivroDTO.convertFromLivro(itemLivroDto);
			livros.add(livro);
		}
		
		livroRepository.saveAll(livros);
	}
	

	
	/**
	 * @param caminho
	 * @return
	 * criando uma instancia do arquivo
	 */
	private Reader lerArquivo (String caminho) {
		Path path = new File(caminho).toPath();
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return reader;
	}

	/**
	 * @param f
	 * @return
	 * Deserializando os dados do Arquivo livros
	 */
	private List <LivroDTO> deserialize(final Reader reader){
		Gson gson = new Gson();
		LivroDTO[] livros = null; 
		 
		if ( reader != null) {
			livros = gson.fromJson(reader, LivroDTO[].class);
		}
		return Arrays.asList(livros);
	}
}
