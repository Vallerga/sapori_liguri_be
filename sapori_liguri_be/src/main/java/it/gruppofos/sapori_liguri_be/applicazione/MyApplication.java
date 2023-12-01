package it.gruppofos.sapori_liguri_be.applicazione;

import java.util.HashSet;
import java.util.Set;

import it.gruppofos.sapori_liguri_be.rest.ServiziRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("antonio")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(ServiziRest.class);

        return s;
    }
}