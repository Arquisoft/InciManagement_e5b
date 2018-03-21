package uo.asw;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InciManagerE5bApplication {

	public static void main(String[] args) {
		//SpringApplication.run(InciManagerE5bApplication.class, args);
		SpringApplication app = new SpringApplication(InciManagerE5bApplication.class);
	    //app.setBannerMode(Banner.Mode.OFF);
	    app.run(args);
	}
}
