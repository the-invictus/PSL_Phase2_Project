package com.psl.jun21.grp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.psl.jun21.grp3.user.User;
import com.psl.jun21.grp3.user.UserRole;
import com.psl.jun21.grp3.user.UserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@PropertySource("classpath:config.properties")
public class InternshipPortalApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(InternshipPortalApplication.class, args);
  }

  @Autowired
  UserService userService;

  @Value("${admin_email}")
  private String adminEmail;

  @Value("${admin_password}")
  private String adminPassword;

  @Override
  public void run(String... args) throws Exception {
//    try {
//      userService.save(new User(adminEmail, UserRole.SYSTEM_ADMIN,
//          new BCryptPasswordEncoder().encode(adminPassword)));
//    } catch (Exception ignored) {    }

  }

}
