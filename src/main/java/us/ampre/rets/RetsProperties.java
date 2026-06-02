package us.ampre.rets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for ampapi.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "ampre.rets")
public class RetsProperties {
    /**
     * Whether to enable the job queue.
     */
    @Default
    private String username = null;
    @Default
    private String password = null;
    @Default
    private String loginUrl = null;
    @Default
    private String userAgent = null;
    @Default
    private String userAgentPassword = null;
}
