package org.matsim.exercises;

import com.google.inject.Inject;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.ControlerI;
import org.matsim.core.controler.NewControlerModule;
import org.matsim.core.controler.corelisteners.ControlerDefaultCoreListenersModule;
import org.matsim.core.events.EventsManagerModule;
import org.matsim.core.mobsim.DefaultMobsimModule;
import org.matsim.core.replanning.StrategyManagerModule;
import org.matsim.core.router.TripRouterModule;
import org.matsim.core.router.costcalculators.TravelDisutilityModule;
import org.matsim.core.scenario.ScenarioByInstanceModule;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.scoring.functions.CharyparNagelScoringFunctionModule;
import org.matsim.core.trafficmonitoring.TravelTimeCalculatorModule;
import org.matsim.core.utils.timing.TimeInterpretationModule;
import org.matsim.exercises.base.Helper;
import org.matsim.exercises.base.HelperDefaultImpl;

public class RunMatsimSimulation {

    public static void main(String[] args) {
        Config config = ConfigUtils.loadConfig("scenarios/equil/config.xml");
        config.controler().setLastIteration(2);

        Scenario scenario = ScenarioUtils.loadScenario(config);

        AbstractModule module = new AbstractModule() {
            @Override
            public void install() {
                bind(Abc.class).to(AbcImplement.class);
                bind(Helper.class).to(HelperDefaultImpl.class);

                install( new NewControlerModule() );
                install( new ControlerDefaultCoreListenersModule() );
                install( new ScenarioByInstanceModule( scenario ) );


                install(new EventsManagerModule());
                install(new DefaultMobsimModule());
                install(new TravelTimeCalculatorModule());
                install(new TravelDisutilityModule());
                install(new CharyparNagelScoringFunctionModule());
                install(new TripRouterModule());
                install(new StrategyManagerModule());
                install(new TimeInterpretationModule());
//                if (getConfig().replanningAnnealer().isActivateAnnealingModule()) {
//                    addControlerListenerBinding().to(ReplanningAnnealer.class);
//                }

                // I think that the ones coming here are all for analysis only, and thus not central to the iterations. kai, apr'18
//                install(new LinkStatsModule());
//                install(new VolumesAnalyzerModule());
//                install(new LegHistogramModule());
//                install(new LegTimesModule());
//                install(new IterationTravelStatsModule());
//                install(new ScoreStatsModule());
//                install(new ModeStatsModule());
//                install(new CountsModule());
//                install(new PtCountsModule());
//                install(new VspPlansCleanerModule());
//                install(new SnapshotWritersModule());
//                install(new DependencyGraphModule());

            }
        };

        com.google.inject.Injector injector = org.matsim.core.controler.Injector.createInjector(config, module);
        Abc abc = injector.getInstance(Abc.class);
        abc.doSomething();

        ControlerI controlerI = injector.getInstance(ControlerI.class);
        controlerI.run();
    }

    interface Abc {
        void doSomething();
    }

    private static class AbcImplement implements Abc {
        @Inject
        private Helper helper;

        @Override
        public void doSomething() {
            System.out.println("Abc implement is used");
            helper.help();
        }
    }

    private static class AbcImplement2 implements Abc {
        @Override
        public void doSomething() {
            System.out.println("Abc implement 2 is used! ");

        }
    }


}
