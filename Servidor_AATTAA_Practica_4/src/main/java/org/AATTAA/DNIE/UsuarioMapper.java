package org.AATTAA.DNIE;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 * Clase para mapear los parametros de Usuario y colocarlo en el objeto del usuario.
 * @author Salvador
 *
 */
public class UsuarioMapper implements RowMapper<Usuario>{

	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException{
		
     Usuario usuario = new Usuario();
     usuario.setNombre(rs.getString("nombre"));
     usuario.setApellido1(rs.getString("apellido1"));
     usuario.setApellido2(rs.getString("apellido2"));
     usuario.setNif(rs.getString("DNI"));
     usuario.setNick(rs.getString("nick"));
    

return usuario;
}
}