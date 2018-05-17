package org.AATTAA.DNIE;

import java.util.Base64;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.AATTAA.DNIE.UsuarioDAOInterface;
import org.AATTAA.DNIE.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UsuarioDAOInterface dao;  //Declaramos el objeto beans (Definido en el servlet-context.xml).
	private ObtenerDatos od=new ObtenerDatos();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	
	/**
	 * Respuesta del servidor GET al accceder a la URL mostrando la página principal.
	 * @param locale
	 * @param model
	 * @return vista que se devuelve al cliente
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String home(Locale locale, Model model) {
		return "index"; // Te envÃ­a a la pÃ¡gina principal
	}
	
	/**
	 * 
	 * MÃ©todo para comprobar si un usuario estÃ¡ registrado mediante su NIF y su NICK, 
	 * en caso de no estarlo  lo redirecciona al NoExiste.jsp(Posibilidad de registrarte), 
	 * y en caso contrario al Existe.jsp (Muestra los datos del usuario guardados en la BBDD).
	 * 
	 * Después de la modificación del cliente este método se una para recibir la respuesta HTTP REQUEST del cliente 
	 * y comprobar si un usuario existe en la BBDD. Si existe en la BBDD se manda un mensaje de respuesta 
	 * 
	 * 
	 * @param request Object de peticion
	 * @param nick nick del usuario recogido en la petición HTTP REQUEST enviada del cliente
	 * @param nif contraseÃ±a del usuario recogida en la petición HTTP REQUEST enviada del cliente
	 * @param req Object de peticion
	 * @param locale parametro de Spring
	 * @param model parametro de la vista en Spring
	 * @param respuesta variable usada para enviar un mensaje de respuesta a la vista y así anunciar la respuesta al usuario
	 * @return vista que se devuelve al cliente
	 */
	@RequestMapping(value = "/CompruebaBBDD", method = {RequestMethod.POST, RequestMethod.GET})
	public String sesion(HttpServletRequest request,Locale locale, Model model) {
		//HttpSession sesion = request.getSession();
		Usuario db= new Usuario();
		String nick=request.getParameter("nick");    //parametros recojidos de la peticion
		String nif=request.getParameter("nif");   
		String hash=request.getParameter("hash");
		String certificado=request.getParameter("certificado");
		String respuesta="";
		
		
		if (dao.BuscarUsuario(nif,nick)==null) {     //Si no existe el usuario
	 
			respuesta="ERROR 401:  USUARIO NO ENCONTRADO";
			model.addAttribute("respuesta", respuesta);
			
			return "ServidorR";       //Devuelvo el valor del String en vez de devolver el jsp.
		
			}else { 
				Usuario user=dao.BuscarUsuario(nif,nick);
				
				//decodificamos el certificado
				byte[] certificado_decodificado = Base64.Decoder(certificado);
				System.out.println("Decoded value is " + new String(certificado_decodificado));
				
				//Decodificamos el Hash
				byte[] hash_decodificado = Base64.Decoder(hash);
				System.out.println("Decoded value is " + new String(hash_decodificado));
				
				respuesta="200 OK:  USUARIO AUTENTICADO CORRECTAMENTE";
				model.addAttribute("respuesta", respuesta);
				return "ServidorR";
			}
	}
			

}
