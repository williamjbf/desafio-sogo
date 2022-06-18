package com.github.williamjbf.desafiosogo.util;

import java.time.LocalDate;

public class DateUtil {

    public static LocalDate dia(int dia, int mes){
        return LocalDate.of(
                LocalDate.now().getYear(),
                mes,
                dia
        );
    }

    public static LocalDate primeiroDiaDaSemana(int semana, int mes){
        return LocalDate.of(
                LocalDate.now().getYear(),
                mes,
                semana*7 +1
                );
    }

    public static LocalDate diaNaSemana(int dia, int semana, int mes){
        LocalDate diaSemana = primeiroDiaDaSemana(semana,mes);
        return diaSemana.plusDays(dia);
    }

    public static LocalDate ultimoDiaDaSemana(int semana, int mes){
        LocalDate date = primeiroDiaDaSemana(semana,mes);
        return date.plusDays(6);
    }

}
