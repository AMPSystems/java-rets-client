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

	@Autowired
	private RetsProperties retsProperties;

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(RetsApplication.class);
		// tell spring to not start the netty web server
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("RETS Starting ...");

		trulia();
	}

	public  void trulia() throws MalformedURLException {

		//Create a RetsHttpClient (other constructors provide configuration i.e. timeout, gzip capability)
		RetsHttpClient httpClient = new CommonsHttpClient();
		RetsVersion retsVersion = RetsVersion.RETS_1_7_2;
		String loginUrl = retsProperties.getLoginUrl();

		//Create a RetsSession with RetsHttpClient
		RetsSession session = new RetsSession(loginUrl, httpClient, retsVersion);

		String username 			= retsProperties.getUsername();
		String password 			= retsProperties.getPassword();
		String userAgent 			= retsProperties.getUserAgent();
		String userAgentPassword 	= retsProperties.getUserAgentPassword();

		//Set method as GET or POST
		session.setMethod("POST");
		try {
			//Login
			session.login(username, password);
		} catch (RetsException e) {
			log.error("", e);
			return;
		}

		try {
			Metadata m = session.getMetadata();

			MSystem system = session.getMetadata().getSystem();
			log.info("System: {} / {}", system.getSystemID(), system.getSystemDescription());
			for(MResource resource: system.getMResources()) {
				log.info("  Resource: {} / {}", resource.getResourceID(), resource.getDescription());
				for(MClass classification: resource.getMClasses()) {
					log.info("    Class: {} / {}", classification.getClassName(), classification.getDescription());
					for (MTable mTable : classification.getMTables()) {
						log.info("      Table: {} / {}", mTable.getSystemName(), mTable.getStandardName());
					}
				}
			}
		} catch (RetsException e) {
			log.error("Exception traversing metadata tree.", e);
		} finally {
			if(session != null) {
				try {
					session.logout();
				} catch(RetsException e) {
					log.error("Exception ending RETS session/logout.", e);
				}
			}
		}
	}
}
