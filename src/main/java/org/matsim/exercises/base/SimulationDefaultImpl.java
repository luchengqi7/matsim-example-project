package org.matsim.exercises.base;

import com.google.inject.Inject;

public class SimulationDefaultImpl implements Simulation {
    @Inject
    private Helper helper;

    @Override
    public void doStep() {
        System.out.println("entering " + this.getClass().getSimpleName());
        helper.help();
        System.out.println("leaving " + this.getClass().getSimpleName());
    }

}
