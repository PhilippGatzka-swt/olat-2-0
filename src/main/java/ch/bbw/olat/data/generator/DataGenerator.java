package ch.bbw.olat.data.generator;

import ch.bbw.olat.data.service.OlatDataService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PasswordEncoder passwordEncoder,OlatDataService dataService) {
        return args -> {


            /*
            OlatPersonEntity adminPerson = OlatPersonEntity.builder().firstname("Olat").lastname("Administrator").email("admin@olat.com").build();
            dataService.getOlatPersonService().save(adminPerson);
            adminPerson = dataService.getOlatPersonService().getRepository().findByEmail(adminPerson.getEmail());
            OlatUserEntity adminUser = OlatUserEntity.builder().person(adminPerson).fileSystemPrefix("admin").roles(Collections.singleton(Role.ADMIN)).username("admin").hashedPassword(passwordEncoder.encode("admin")).build();
            dataService.getOlatUserService().save(adminUser);



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

            ExampleDataGenerator<OlatPersonEntity> personGenerator = new ExampleDataGenerator<>(
                    OlatPersonEntity.class, LocalDateTime.of(2022, 5, 12, 0, 0, 0)
            );
            personGenerator.setData(OlatPersonEntity::setFirstname, DataType.FIRST_NAME);
            personGenerator.setData(OlatPersonEntity::setLastname, DataType.LAST_NAME);
            personGenerator.setData(OlatPersonEntity::setEmail, DataType.EMAIL);

            personGenerator.create(50, 79853).forEach(p -> dataService.getOlatPersonService().save(p));

            OlatPersonEntity teacherPerson = OlatPersonEntity.builder().firstname("Olat").lastname("Teacher").email("teacher@olat.com").build();
            OlatPersonEntity studentPerson = OlatPersonEntity.builder().firstname("Olat").lastname("Student").email("student@olat.com").build();

            dataService.getOlatPersonService().save(teacherPerson);
            dataService.getOlatPersonService().save(studentPerson);

            adminPerson = dataService.getOlatPersonService().getRepository().findByEmail(adminPerson.getEmail());
            teacherPerson = dataService.getOlatPersonService().getRepository().findByEmail(teacherPerson.getEmail());
            studentPerson = dataService.getOlatPersonService().getRepository().findByEmail(studentPerson.getEmail());

            OlatUserEntity teacherUser = OlatUserEntity.builder().person(teacherPerson).fileSystemPrefix("teacher").roles(Collections.singleton(Role.TEACHER)).username("teacher").hashedPassword(passwordEncoder.encode("teacher")).build();
            OlatUserEntity studentUser = OlatUserEntity.builder().person(studentPerson).fileSystemPrefix("student").roles(Collections.singleton(Role.STUDENT)).username("student").hashedPassword(passwordEncoder.encode("student")).build();

            dataService.getOlatUserService().save(teacherUser);
            dataService.getOlatUserService().save(studentUser);

            adminUser = dataService.getOlatUserService().getRepository().findByUsername(adminUser.getUsername());
            teacherUser = dataService.getOlatUserService().getRepository().findByUsername(teacherUser.getUsername());
            studentUser = dataService.getOlatUserService().getRepository().findByUsername(studentUser.getUsername());

            OlatGroupEntity group1 = OlatGroupEntity.builder().fileSystemPrefix("5IA19a").name("5IA19a").teacher(teacherUser).build();
            OlatGroupEntity group2 = OlatGroupEntity.builder().fileSystemPrefix("5II19c").name("5II19c").teacher(teacherUser).build();
            OlatGroupEntity group3 = OlatGroupEntity.builder().fileSystemPrefix("5IS19b").name("5IS19b").teacher(teacherUser).build();

            dataService.getOlatGroupService().save(group1);
            dataService.getOlatGroupService().save(group2);
            dataService.getOlatGroupService().save(group3);

            group1 = dataService.getOlatGroupService().getRepository().findByName(group1.getName());
            group2 = dataService.getOlatGroupService().getRepository().findByName(group2.getName());
            group3 = dataService.getOlatGroupService().getRepository().findByName(group3.getName());

            studentUser.getPerson().getOlatGroupEntities().add(group1);
            studentUser.getPerson().getOlatGroupEntities().add(group2);

            dataService.getOlatPersonService().save(studentPerson);

            studentPerson = dataService.getOlatPersonService().getRepository().findByEmail(studentPerson.getEmail());

            OlatSubjectEntity modul101 = OlatSubjectEntity.builder().fileSystemPrefix("modul101").name("modul101").details("modul101 - details").build();
            OlatSubjectEntity modul102 = OlatSubjectEntity.builder().fileSystemPrefix("modul102").name("modul102").details("modul102 - details").build();
            OlatSubjectEntity modul103 = OlatSubjectEntity.builder().fileSystemPrefix("modul103").name("modul103").details("modul103 - details").build();
            OlatSubjectEntity modul104 = OlatSubjectEntity.builder().fileSystemPrefix("modul104").name("modul104").details("modul104 - details").build();
            OlatSubjectEntity modul105 = OlatSubjectEntity.builder().fileSystemPrefix("modul105").name("modul105").details("modul105 - details").build();
            OlatSubjectEntity modul106 = OlatSubjectEntity.builder().fileSystemPrefix("modul106").name("modul106").details("modul106 - details").build();

            dataService.getOlatSubjectService().save(modul101);
            dataService.getOlatSubjectService().save(modul102);
            dataService.getOlatSubjectService().save(modul103);
            dataService.getOlatSubjectService().save(modul104);
            dataService.getOlatSubjectService().save(modul105);
            dataService.getOlatSubjectService().save(modul106);

            modul101 = dataService.getOlatSubjectService().getRepository().findByName("modul101");
            modul102 = dataService.getOlatSubjectService().getRepository().findByName("modul102");
            modul103 = dataService.getOlatSubjectService().getRepository().findByName("modul103");
            modul104 = dataService.getOlatSubjectService().getRepository().findByName("modul104");
            modul105 = dataService.getOlatSubjectService().getRepository().findByName("modul105");
            modul106 = dataService.getOlatSubjectService().getRepository().findByName("modul106");

            OlatAbsenceEntity absence1 = OlatAbsenceEntity.builder().comment("5 min to late").olatSubjectEntity(modul101).student(studentUser.getPerson()).date(Date.valueOf(LocalDate.now())).signed(false).build();
            OlatAbsenceEntity absence2 = OlatAbsenceEntity.builder().comment("train crash").olatSubjectEntity(modul102).student(studentUser.getPerson()).date(Date.valueOf(LocalDate.now())).signed(true).build();
            OlatAbsenceEntity absence3 = OlatAbsenceEntity.builder().comment("cat ate homework").olatSubjectEntity(modul103).student(studentUser.getPerson()).date(Date.valueOf(LocalDate.now())).signed(false).build();

            dataService.getOlatAbsenceService().save(absence1);
            dataService.getOlatAbsenceService().save(absence2);
            dataService.getOlatAbsenceService().save(absence3);




            logger.info("Generated demo data");
            */};
    }

}