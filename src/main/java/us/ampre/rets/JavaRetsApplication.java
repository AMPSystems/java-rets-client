package us.ampre.rets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.ampre.rets.client.*;
import us.ampre.rets.client.exceptions.RetsException;
import us.ampre.rets.common.metadata.Metadata;
import us.ampre.rets.common.metadata.types.MClass;
import us.ampre.rets.common.metadata.types.MResource;
import us.ampre.rets.common.metadata.types.MSystem;
import us.ampre.rets.common.metadata.types.MTable;

import java.net.MalformedURLException;

// Placeholder class kept for API compatibility. The runnable example has been moved to
// the 'examples' subproject to avoid accidental application startup during library use.
public final class JavaRetsApplication {
	private JavaRetsApplication() { /* no-op */ }
}
