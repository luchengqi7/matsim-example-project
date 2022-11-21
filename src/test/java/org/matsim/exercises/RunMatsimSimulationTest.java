package org.matsim.exercises;

import org.junit.Rule;
import org.junit.Test;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.testcases.MatsimTestUtils;

import static org.junit.Assert.assertEquals;

public class RunMatsimSimulationTest {
    @Rule
    public MatsimTestUtils utils = new MatsimTestUtils();

    @Test
    public void test() {
        System.out.println("class input directory: " + utils.getClassInputDirectory());
        System.out.println("output directory: " + utils.getOutputDirectory());
        System.out.println("input directory: " + utils.getInputDirectory());

//        Config config = ConfigUtils.createConfig();
//        config.network().setInputFile(utils.getClassInputDirectory() + "/network.xml.gz");
//        config.plans().setInputFile(utils.getClassInputDirectory() + "/plans.xml.gz");
//        config.controler().setOutputDirectory(utils.getOutputDirectory());
//        config.controler().setLastIteration(1);
//
//        Scenario scenario = ScenarioUtils.loadScenario(config);
//        Controler controler = new Controler(scenario);
//        controler.run();


        System.out.println("test is complete");
    }

}