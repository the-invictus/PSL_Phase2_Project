package com.psl.jun21.grp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class InternshipPortalApplication {

  public static void main(String[] args) {
    SpringApplication.run(InternshipPortalApplication.class, args);
  }

}
