package com.drools.first.config;

import com.drools.first.service.Jewelservice;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class Config {

    private KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieContainer kieContainer() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        String path = "C:\\Users\\Balaji\\Downloads\\sample.xlsx";
//        File file = new File(path);
        kieFileSystem.write(ResourceFactory.newClassPathResource("sample.xlsx"));


        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession kieSession() {
        KieSession kieSession = kieContainer().newKieSession();

        // Inject the KieSession into the Jewelservice bean
        jewelservice().setKieSession(kieSession);

        return kieSession;
    }

    @Bean
    public Jewelservice jewelservice() {
        return new Jewelservice();
    }
}
