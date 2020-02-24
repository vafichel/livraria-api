package br.com.livraria.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity(name = "LIVRO")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIVRO")
	private Long id;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "AUTOR")
	private String autor; 
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "NUMPAGINAS")
	private int numeroPaginas;
	
	@Column(name = "IDIOMA")
	private String idioma;
	
	@Column(name = "PRECO")
	private BigDecimal preco;

}
