package com.example.physical.activity.login;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import com.example.physical.activity.login.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;


@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTestCase {
    
	
	@Test
	public void showUsers() throws Exception{
		List<Login> korisnik= stvoriListuKorisnika(1);
		LoginRepository mockRepository=mock(LoginRepository.class);
		when(mockRepository.findUser(username, sifra)).thenReturn(korisnik);
		
		LoginController controller = new LoginController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).setSingleView(
				new InternalResourceView("/WEB-INF/views/listakorisnika.jsp")).build();
		mockMvc.perform(get("/listakorisnika"))
		.andExpect(view().name("listakorisnika"))
		.andExpect(model().attributeExists("listaKorisnika"))
		.andExpect(model().attribute("listaKorisnika",hasItems(korisnik.toArray())));
	}
	private List<Login> stvoriListuKorisnika(int broj){
		List<Login> listakorisnika = new ArrayList<Login>();
		for(int i=0;i<broj;i++){
			listakorisnika.add(new Login("Logiran je:"+i,new Date()));
		}
		return listakorisnika;
	}
}