package it.gruppofos.sapori_liguri_be.applicazione;

import java.util.HashSet;
import java.util.Set;

import it.gruppofos.sapori_liguri_be.provider.ProviderFilter;
import it.gruppofos.sapori_liguri_be.rest.ServiziRestPesto;
import it.gruppofos.sapori_liguri_be.rest.ServiziRestProfilo;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("antonio")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(ServiziRestPesto.class);
        s.add(ServiziRestProfilo.class);
        s.add(ProviderFilter.class);	

        return s;
    }
}