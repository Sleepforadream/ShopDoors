package com.shopdoors.config;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfiguration {

    @Bean
    @ServiceConnection
    @RestartScope
    PostgreSQLContainer<?> postgreSQLContainer() {
        try (var postgres = new PostgreSQLContainer<>(
                DockerImageName.parse("postgres:latest")
                        .asCompatibleSubstituteFor("postgres"))) {
            String port = System.getProperty("postgres.container.port");
            if (port != null) {
                postgres.withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(new HostConfig()
                        .withPortBindings(new PortBinding(
                                Ports.Binding.bindPort(Integer.parseInt(port)), new ExposedPort(POSTGRESQL_PORT)))));
            }
            return postgres;
        }
    }

    static {
        var minIOContainer = new MinIOContainer(DockerImageName.parse(
                                "minio/minio:latest")
                        .asCompatibleSubstituteFor("minio/minio"))
                .withEnv("MINIO_ACCESS_KEY", "minioadmin")
                .withEnv("MINIO_SECRET_KEY", "minioadmin")
                .withReuse(true)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(new HostConfig()
                        .withPortBindings(
                                new PortBinding(Ports.Binding.bindPort(9996), new ExposedPort(9000)),
                                new PortBinding(Ports.Binding.bindPort(9997), new ExposedPort(9001)))));
        minIOContainer.start();

    }
}
