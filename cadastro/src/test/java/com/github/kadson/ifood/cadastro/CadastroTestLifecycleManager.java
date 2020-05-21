package com.github.kadson.ifood.cadastro;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;
public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager {
    public static final PostgreSQLContainer<?> POSTGRES =  new PostgreSQLContainer<>("postgres:12.2");

    @Override
    public Map<String, String> start() {
        POSTGRES.start();
        Map<String, String> propiedades = new HashMap<String, String>();

        //Banco de dados
        propiedades.put("quarkus.datasource.url", POSTGRES.getJdbcUrl());
        propiedades.put("quarkus.datasource.username", POSTGRES.getUsername());
        propiedades.put("quarkus.datasource.password", POSTGRES.getPassword());

        return propiedades;
    }

    @Override
    public void stop() {
        if(POSTGRES != null && POSTGRES.isRunning()){
            POSTGRES.stop();
        }
    }
}
