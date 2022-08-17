import org.springframework.beans.factory.annotation.Configurable;

@Configuration
public class AWSSNSConfig {
	 public static final String SECRET_KEY = "2sVDGZBfei00zK9/WCB7bozIlruDMYpSrrf9dF0d";
	    public static final String ACCESS_KEY = "AKIA4VACHIE4DCVOQRNT";

	    @Primary
	    @Bean
	    public AmazonSNSClient getSnsClient() {
	        return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
	                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY,
	                        SECRET_KEY)))
	                .build();
	    }

}
