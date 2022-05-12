package ch.bbw.olat.data.generator;

import ch.bbw.olat.data.Role;
import ch.bbw.olat.data.entity.OlatPersonEntity;
import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.entity.SamplePerson;
import ch.bbw.olat.data.service.OlatDataService;
import ch.bbw.olat.data.service.SamplePersonRepository;
import ch.bbw.olat.data.service.UserRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PasswordEncoder passwordEncoder, SamplePersonRepository samplePersonRepository,
                                      UserRepository userRepository, OlatDataService dataService) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (samplePersonRepository.count() != 0L) {
                logger.info("Using existing database");
                // return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Sample Person entities...");
            ExampleDataGenerator<SamplePerson> samplePersonRepositoryGenerator = new ExampleDataGenerator<>(
                    SamplePerson.class, LocalDateTime.of(2022, 5, 12, 0, 0, 0));
            samplePersonRepositoryGenerator.setData(SamplePerson::setFirstName, DataType.FIRST_NAME);
            samplePersonRepositoryGenerator.setData(SamplePerson::setLastName, DataType.LAST_NAME);
            samplePersonRepositoryGenerator.setData(SamplePerson::setEmail, DataType.EMAIL);
            samplePersonRepositoryGenerator.setData(SamplePerson::setPhone, DataType.PHONE_NUMBER);
            samplePersonRepositoryGenerator.setData(SamplePerson::setDateOfBirth, DataType.DATE_OF_BIRTH);
            samplePersonRepositoryGenerator.setData(SamplePerson::setOccupation, DataType.OCCUPATION);
            samplePersonRepositoryGenerator.setData(SamplePerson::setImportant, DataType.BOOLEAN_10_90);
            samplePersonRepository.saveAll(samplePersonRepositoryGenerator.create(100, seed));

            logger.info("... generating 2 User entities...");

            OlatPersonEntity adminPerson = OlatPersonEntity.builder().firstname("Olat").lastname("Administrator").email("admin@olat.com").build();
            dataService.getOlatPersonService().save(adminPerson);
            OlatPersonEntity teacherPerson = OlatPersonEntity.builder().firstname("Olat").lastname("Teacher").email("teacher@olat.com").build();
            dataService.getOlatPersonService().save(teacherPerson);
            OlatPersonEntity studentPerson = OlatPersonEntity.builder().firstname("Olat").lastname("Student").email("student@olat.com").build();
            dataService.getOlatPersonService().save(studentPerson);

            adminPerson = dataService.getOlatPersonService().getRepository().findByEmail(adminPerson.getEmail());
            teacherPerson = dataService.getOlatPersonService().getRepository().findByEmail(teacherPerson.getEmail());
            studentPerson = dataService.getOlatPersonService().getRepository().findByEmail(studentPerson.getEmail());

            OlatUserEntity adminUser = OlatUserEntity.builder().person(adminPerson).fileSystemPrefix("admin").roles(Collections.singleton(Role.ADMIN)).username("admin").hashedPassword(passwordEncoder.encode("admin")).build();
            OlatUserEntity teacherUser = OlatUserEntity.builder().person(teacherPerson).fileSystemPrefix("teacher").roles(Collections.singleton(Role.TEACHER)).username("teacher").hashedPassword(passwordEncoder.encode("teacher")).build();
            OlatUserEntity studentUser = OlatUserEntity.builder().person(studentPerson).fileSystemPrefix("student").roles(Collections.singleton(Role.STUDENT)).username("student").hashedPassword(passwordEncoder.encode("student")).build();

            dataService.getOlatUserService().save(adminUser);
            dataService.getOlatUserService().save(teacherUser);
            dataService.getOlatUserService().save(studentUser);


            logger.info("Generated demo data");
        };
    }

}