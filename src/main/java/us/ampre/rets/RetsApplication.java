package us.ampre.rets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.ampre.rets.client.*;
import us.ampre.rets.common.metadata.Metadata;
import us.ampre.rets.common.metadata.types.MClass;
import us.ampre.rets.common.metadata.types.MResource;
import us.ampre.rets.common.metadata.types.MSystem;

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

		//Create a RetesSession with RetsHttpClient
		RetsSession session = new RetsSession(loginUrl, httpClient, retsVersion);

		String username = retsProperties.getUsername();
		String password = retsProperties.getPassword();

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
			System.out.println(
					"SYSTEM: " + system.getSystemID() +
							" - " + system.getSystemDescription());

			for(MResource resource: system.getMResources()) {

				System.out.println(
						"    RESOURCE: " + resource.getResourceID());

				for(MClass classification: resource.getMClasses()) {
					System.out.println(
							"        CLASS: " + classification.getClassName() +
									" - " + classification.getDescription());


				}
			}
		}
		catch (RetsException e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				try {
					session.logout();
				}
				catch(RetsException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
