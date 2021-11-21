package com.sebastiaofortes.security.security.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sebastiaofortes.security.security.Rsa;
import com.sebastiaofortes.security.security.Usuariorepository;
import com.sebastiaofortes.security.security.Model.Usuarios;



@RequestMapping(path="/main")
@Controller
public class MainController {
	
	@Autowired
	private Usuariorepository userRepository;
	
	@Autowired
	private ServletContext servletContext;

	  @GetMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser1 (@RequestParam String name
	      , @RequestParam String email) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request

	    Usuarios n = new Usuarios();
	    n.setLogin(name);
	    n.setEmail(new BCryptPasswordEncoder().encode(email));
	    userRepository.save(n);
	    return "Dados salvos com sucesso!";
	  }
	  
	  @PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser (@RequestParam String name
	      , @RequestParam String email) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request

	    Usuarios n = new Usuarios();
	    n.setLogin(name);
	    n.setEmail(new BCryptPasswordEncoder().encode(email));
	    userRepository.save(n);
	    
	    Rsa chaves = new Rsa(name);
	    chaves.start();
	    return "Saved";
	  }
	
	@RequestMapping("/teste-sql")
	public String index() {

	    Usuarios n = new Usuarios();
	    n.setLogin("sebastiao");
	    n.setEmail("fortes4");
	    userRepository.save(n);
		
		return "testesql";
	}
	
	@RequestMapping("/listando")
	public String listando(ModelMap model) {

		List<Usuarios> lista = (List<Usuarios>) userRepository.findAll();
		int numres = (lista.size());
		String textres = Integer.toString(numres);
		model.addAttribute("numeroresultados", textres);
		model.addAttribute("usuarios", lista);
		return "listando";
	}
	
	@RequestMapping("/listando2")
	public String listando2(ModelMap model) {

		List<Usuarios> lista = (List<Usuarios>) userRepository.findAll();
		int numres = (lista.size());
		String textres = Integer.toString(numres);
		model.addAttribute("numeroresultados", textres);
		model.addAttribute("usuarios", lista);
		return "listando2";
	}
	
	@RequestMapping("/teste-sql-get")
	public String TesteSget() {

		
		return "testesget";
	}
	
	@RequestMapping("/cadastro")
	public String TesteSpost() {

		
		return "cadastro";
	}
	
	@RequestMapping("/sobre")
	public String sobre() {

		return "sobre";
	}
	
	@RequestMapping("/home")
	public String home() {

		return "home";
	}
	
	@RequestMapping("/exibindo")
	public String exibindo(ModelMap model) {

		   model.addAttribute("nomeDoAtributo", "Informção vinda do código java");
		return "exibindo";
	}
	
	@RequestMapping("/recebendo")
	public String recebendo(ModelMap model, @RequestParam String valor) {

		   model.addAttribute("nomeDoAtributo", valor);
		return "recebendo";
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/download/{fileName}")
    public void download(HttpServletResponse response, @PathVariable("fileName") String fileName) {
        String path = "C:/keys/";
        
        Path arquivo = Paths.get(path, fileName + ".key");
        System.out.println("Download: "+arquivo);

        if(Files.exists(arquivo)) {
           
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName +".key" + "\"");            
            try {
                Files.copy(arquivo, response.getOutputStream());
                response.getOutputStream().flush();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	

	@RequestMapping("/download-chaves")
	public String dowChaves() {

		return "dow-chave";
	}



}
