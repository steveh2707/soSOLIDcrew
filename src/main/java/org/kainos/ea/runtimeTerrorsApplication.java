package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.resources.AuthController;
import org.kainos.ea.resources.EmployeeController;
import org.kainos.ea.resources.ProjectController;

public class runtimeTerrorsApplication extends Application<runtimeTerrorsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new runtimeTerrorsApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<runtimeTerrorsConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new SwaggerBundle<runtimeTerrorsConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(runtimeTerrorsConfiguration configuration){
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final runtimeTerrorsConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new EmployeeController());
        environment.jersey().register(new ProjectController());
        environment.jersey().register(new AuthController());
    }

}
