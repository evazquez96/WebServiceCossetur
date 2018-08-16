/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosettur.webservice;

import com.cosettur.helpers.Security;
import com.cosettur.models.dao.UsuarioDAO;
import com.cosettur.models.pojo.Usuario;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author evazquez
 */
@WebService(serviceName = "UserWS")
public class UserWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "login")
    public int login(
            @WebParam(name = "user") String user,
            @WebParam(name = "pass") String pass) {
        /**
         * El atributo user puede hacer referencia al
         * nombre de usuario o al correo.
         **/
        return UsuarioDAO.loginUsuario(user, pass);

    }
    
    @WebMethod(operationName = "insertaUsuario")
    public int insertaUsuario(
            @WebParam(name = "user") String user,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "pass") String pass,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "correo") String correo) {
        
        Usuario u=new Usuario();
        u.setUsuario(user);
        u.setNombre(nombre);
        u.setPass(Security.encriptarMD5(pass));//Encripta la contraseña
        u.setTelefono(telefono);
        u.setCorreo(correo);
        
        return UsuarioDAO.insertarUsuario(u);
    }
}
