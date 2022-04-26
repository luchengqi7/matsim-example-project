package org.matsim.project;

import org.matsim.api.core.v01.population.Population;
import org.matsim.api.core.v01.population.PopulationFactory;
import org.matsim.api.core.v01.population.PopulationWriter;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.population.PopulationUtils;
import org.matsim.core.scenario.ProjectionUtils;

public class WritePopulation {

    public static void main(String[] args) {
        Population population = PopulationUtils.createPopulation(ConfigUtils.createConfig());
        ProjectionUtils.putCRS(population, "EPSG:4326");



        PopulationWriter populationWriter = new PopulationWriter(population);
        populationWriter.write("/Users/luchengqi/Documents/MATSimScenarios/teaching/ITS2022/Exercise2/berlin/template.plans.xml");

    }
}
