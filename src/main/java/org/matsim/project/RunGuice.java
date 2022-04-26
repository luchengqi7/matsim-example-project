package org.matsim.project;

import org.checkerframework.checker.units.qual.C;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.scoring.ScoringFunction;
import org.matsim.core.scoring.ScoringFunctionFactory;
import org.matsim.core.scoring.SumScoringFunction;

public class RunGuice {

    static class DummyScoringFunction implements ScoringFunctionFactory {
        @Override
        public ScoringFunction createNewScoringFunction(Person person) {
            SumScoringFunction sumScoringFunction = new SumScoringFunction();
            if (person.getId().toString().equals("lucky_person")) {
                sumScoringFunction.addScore(100);
                sumScoringFunction.addMoney(100);
            }

            SumScoringFunction.LegScoring myLegScoringFunction = new SumScoringFunction.LegScoring() {
                private double bonusScore = 0;

                @Override
                public void handleLeg(Leg leg) {
                    if (leg.getTravelTime().orElse(99999) < 10000) {
                        bonusScore = 100;
                    }

                }

                @Override
                public void finish() {
                    bonusScore = 0;
                }

                @Override
                public double getScore() {
                    return bonusScore;
                }
            };
            sumScoringFunction.addScoringFunction(myLegScoringFunction);

            return sumScoringFunction;
        }
    }

    public static void main(String[] args) {
        Config config = ConfigUtils.createConfig();
        config.controler().setLastIteration(1);
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        Scenario scenario = ScenarioUtils.loadScenario(config);
        Controler controler = new Controler(scenario);

        controler.addOverridingModule(new AbstractModule() {
            @Override
            public void install() {
                this.bind(ScoringFunctionFactory.class).to(DummyScoringFunction.class);

            }
        });

        controler.run();
    }


}
