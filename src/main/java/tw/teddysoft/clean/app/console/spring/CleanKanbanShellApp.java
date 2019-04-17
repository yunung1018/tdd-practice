package tw.teddysoft.clean.app.console.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan(basePackages = {
        "tw.teddysoft.clean",
        "tw.teddysoft.clean.app",
        "tw.teddysoft.clean.domain",
})
@SpringBootApplication
public class CleanKanbanShellApp {

  public static void main(String[] args) {
    SpringApplication.run(CleanKanbanShellApp.class, args);
  }


  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      System.out.println("Let's inspect the beans provided by Spring Boot:");
      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        System.out.println(beanName);
      }

    };
  }

}
