package com.example.wegetvax.Models;

public class StrutturaAppoggio {

    private static volatile Vaccinando sessionVaccinando;
    private static volatile Medico sessionMedico;


    public static Vaccinando getVaccinando() {
        if (sessionVaccinando == null) {
            synchronized (StrutturaAppoggio.class) {
                if (sessionVaccinando == null) sessionVaccinando = new Vaccinando(null, null, null, null, null, null,null,null,null,null,null,null,null);
            }
        }
        return sessionVaccinando;
    }

    public static void setVaccinando(Vaccinando newUser) { sessionVaccinando = newUser; }

    public static Medico getMedico() {
        if (sessionMedico == null) {
            synchronized (StrutturaAppoggio.class) {
                if (sessionMedico == null) sessionMedico = new Medico(null, null, null, null, null);
            }
        }
        return sessionMedico;
    }

    public static void setMedico(Medico newUser) { sessionMedico = newUser; }

}
