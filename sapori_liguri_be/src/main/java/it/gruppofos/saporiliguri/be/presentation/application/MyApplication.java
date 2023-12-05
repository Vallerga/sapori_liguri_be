package it.gruppofos.saporiliguri.be.presentation.application;

import java.util.HashSet;
import java.util.Set;

import it.gruppofos.saporiliguri.be.presentation.provider.ProviderFilter;
import it.gruppofos.saporiliguri.be.presentation.rest.RicettaRest;
import it.gruppofos.saporiliguri.be.presentation.rest.ProfiloRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("antonio")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(RicettaRest.class);
        s.add(ProfiloRest.class);
        s.add(ProviderFilter.class);	

        return s;
    }
}