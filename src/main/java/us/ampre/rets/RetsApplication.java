package us.ampre.rets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.ampre.rets.client.*;
import us.ampre.rets.common.metadata.Metadata;
import us.ampre.rets.common.metadata.MetadataType;
import us.ampre.rets.common.metadata.types.MClass;
import us.ampre.rets.common.metadata.types.MResource;
import us.ampre.rets.common.metadata.types.MSystem;
import us.ampre.rets.common.metadata.types.MTable;

import java.net.MalformedURLException;

@Slf4j
@SpringBootApplication
public class RetsApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(RetsApplication.class);
		// tell spring to not start the netty web server
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("RETS Starting ...");
	}

}
