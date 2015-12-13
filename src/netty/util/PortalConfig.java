package netty.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by raja on 02/12/15.
 */
@Configuration
public class PortalConfig {

    @Autowired
    private ConfigFile properties;

    public ConfigFile getProperties() {
        return properties;
    }

    @Bean(name="properties")
    public static ConfigFile properties() {
        ConfigFile ppc = new ConfigFile();
        ClassPathResource[] resources = new ClassPathResource[]
                {new ClassPathResource("server.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

}
