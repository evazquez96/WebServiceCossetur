package com.cosettur.models.dao;

import com.cosettur.helpers.Security;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import com.cosettur.models.pojo.Usuario;
import java.util.List;

public class UsuarioDAO {
    
    public static int loginUsuario(String user,String pass){
        SessionFactory sf=null;
        Session session=null;
        int r=0;
        try{
            sf=HibernateUtil.getSessionFactory();
            session=sf.openSession();
            Query query=session.createQuery(
                    "SELECT u FROM Usuario u where u.usuario = :user and u.pass = :pass "
            );
            query.setParameter("user",user);
            query.setParameter("pass",Security.encriptarMD5(pass));
            List<Usuario> result=query.list();
            r=result.size();
            if(r==0){
                /***
                 * si la longitud de result es 0 puede significar
                 * que se esta logeando utilizando el correo.
                 * Dentro del if se hara la búsqueda utilizando
                 * el correo y la contraseña.
                 */
                query=session.createQuery(
                    "SELECT u FROM Usuario u where u.correo = :correo and u.pass = :pass "
                );
                query.setParameter("correo",user);
                query.setParameter("pass",Security.encriptarMD5(pass));
                result=query.list();
                r=result.size();
            }
            System.out.println("abc");

        }catch(Exception e){
            e.printStackTrace();
        
        }finally{
            session.close();
            return r;
        }
    
        //return 0;
    }
    
    public static int insertarUsuario(Usuario user){
        SessionFactory sf=null;
        Session session=null;
        Transaction tx=null;
        int bandera=0;
        try{
            sf=HibernateUtil.getSessionFactory();
            session=sf.openSession();
            tx=session.beginTransaction();
            session.save(user);
            tx.commit();
            bandera=1;
            session.close();
        }catch(Exception e){
            bandera=0;
            tx.rollback();
            System.out.println(e.toString());
        }finally{
            return bandera;
        }
    }
    
}
