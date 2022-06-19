package com.github.williamjbf.desafiosogo.util;

import com.github.williamjbf.desafiosogo.detail.UsuarioDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LogadoUtil {

    public static String usuarioLogado(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UsuarioDetail){
            return  ((UsuarioDetail)principal).getUsername();
        }
        return principal.toString();
    }

    public static Boolean isAdmin(){
        return "admin".equals(usuarioLogado());
    }

}
