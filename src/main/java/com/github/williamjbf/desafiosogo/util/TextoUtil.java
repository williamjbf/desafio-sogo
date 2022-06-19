package com.github.williamjbf.desafiosogo.util;

import java.util.Random;

public class TextoUtil {

    private static char letras[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static int tamanhoLetras = letras.length;
    public static String gerarTextoAleatorio(int tamanho){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<tamanho; i++){
            sb.append(letras[random.nextInt(tamanhoLetras)]);
        }
        return sb.toString();
    }

}
