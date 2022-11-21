package org.matsim.exercises;

import com.google.inject.*;
import com.google.inject.Module;
import org.matsim.exercises.alternative.HelperAlternativeImpl;
import org.matsim.exercises.base.Helper;
import org.matsim.exercises.base.HelperDefaultImpl;
import org.matsim.exercises.base.Simulation;
import org.matsim.exercises.base.SimulationDefaultImpl;

public class RunSimulation {
    public static void main(String[] args) {
        Module baseModule = new AbstractModule() {
            @Override
            protected void configure() {
                bind(Simulation.class).to(SimulationDefaultImpl.class);
                bind(Helper.class).to(HelperDefaultImpl.class);
            }
        };

        Module alternativeModule = new AbstractModule() {
            @Override
            protected void configure() {
                bind(Simulation.class).to(SimulationDefaultImpl.class).asEagerSingleton();
                bind(Helper.class).to(HelperAlternativeImpl.class).asEagerSingleton();
            }
        };

        Injector injector = Guice.createInjector(alternativeModule);
        Simulation simulation = injector.getInstance(Simulation.class);
        simulation.doStep();
    }

}
