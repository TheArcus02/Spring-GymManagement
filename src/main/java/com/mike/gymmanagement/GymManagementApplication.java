package com.mike.gymmanagement;

import com.mike.gymmanagement.model.Trainer;
import com.mike.gymmanagement.repository.TrainerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication()
@EntityScan(basePackages = "com.mike.gymmanagement.model")
public class GymManagementApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(GymManagementApplication.class, args);

        TrainerRepository trainerRepository =
                configurableApplicationContext.getBean(TrainerRepository.class);

        Trainer trainer = new Trainer("Mike",
                "Smith", 1000);
        trainerRepository.save(trainer);
    }

}
