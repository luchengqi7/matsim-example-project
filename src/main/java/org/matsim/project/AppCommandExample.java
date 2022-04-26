package org.matsim.project;

import com.beust.jcommander.Parameter;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.matsim.application.MATSimAppCommand;

import java.nio.file.Path;

@CommandLine.Command(
        name = "example",
        description = "example app command"
)
public class AppCommandExample implements MATSimAppCommand {
    @CommandLine.Option(names = "--input", required = true)
    private Path input;

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
