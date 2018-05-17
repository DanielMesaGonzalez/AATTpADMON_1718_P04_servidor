package org.AATTAA.DNIE;

import java.util.List;


/**
 * Interfaz donde se definen los metodos a implementar básicos para manejar la BBDD
 * @author Salvador
 *
 */
public interface UsuarioDAOInterface {
	/**
	 * Metodo para insertar usuario a la BBDD
	 * @param usuario
	 */
	public void InsertaUsuario(Usuario usuario);
	/**
	 * Metodo muestra todos los usuarios
	 * @return Lista con Usuarios
	 */
	public List<Usuario> leeUsuario();
	/**
	 * Metodo que devuelve un usuario en concreto segun los parametros siguientes.
	 * @param nif
	 * @param nick
	 * @return Usuario en concreto
	 */
	public Usuario BuscarUsuario(String nif, String nick);
}
