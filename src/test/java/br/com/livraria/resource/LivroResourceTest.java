package br.com.livraria.resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.livraria.LivrariaApplicationTests;

public class LivroResourceTest extends LivrariaApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private LivroResource livroResource;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(livroResource).build();
	}
	
	@Test
	public void testGETListarLivroResource() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/livros")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void testGETConsultarLivroResource() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/livros"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("livro"))	
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPOSTIncluirLivroResource() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/livros")
				.param("titulo", "Os Miseráveis")
				.param("autor", "Vitor Hugo")
				.param("descricao", "Uma apaixonante história de sonhos desfeitos, de um amor não correspondido, paixão, sacrifício e redenção")
				.param("numeroPaginas", "2098")
				.param("idioma", "Portugues")
				.param("preco", "64.74")
				).andExpect(MockMvcResultMatchers.redirectedUrl("/livros"));
	}

}
