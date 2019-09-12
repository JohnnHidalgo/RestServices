package platform;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(TeacherRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Teacher("Bilbo Baggins", "Matemática")));
      log.info("Preloading " + repository.save(new Teacher("Frodo Baggins", "Física")));
    };
  }
}