package br.com.livraria.dto;

import java.math.BigDecimal;

import br.com.livraria.entity.Livro;
import lombok.Data;

@Data
public class LivroDTO {

	private Long id;
	
	private String titulo;
	
	private String autor;
	
	private String descricao;
	
	private int numeroPaginas;
	
	private String idioma;
	
	private BigDecimal preco;
	
	
	public static Livro convertFromLivro(LivroDTO livroDTO){
		
		Livro livro = new Livro();
		
		livro.setTitulo(livroDTO.getTitulo());
		livro.setAutor(livroDTO.getAutor());
		livro.setDescricao(livroDTO.getDescricao());
		livro.setNumeroPaginas(livroDTO.getNumeroPaginas());
		livro.setIdioma(livroDTO.getIdioma());
		livro.setPreco(livroDTO.getPreco());
		
		return livro;
	}
	
}
