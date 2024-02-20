package com.example.psds;

import com.example.psds.knowledge_base.model.*;
import com.example.psds.knowledge_base.repository.*;
import com.example.psds.personal_account.repository.ModeratorRepository;
import com.example.psds.personal_account.model.*;
import com.example.psds.personal_account.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class PsdsApplicationTests {
    @Autowired
    private ModeratorRepository moderatorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private RelationUsersRepository relationUsersRepository;
    @Autowired
    private RoleInGroupRepository roleInGroupRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private SpecialistProfileRepository specialistProfileRepository;
    @Autowired
    private ThemeAndProfileRepository themeAndProfileRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /*Тесты для заполнения БД Личного кабинета*/
    @Test
    void createModerator(){
        Moderator moderator = new Moderator();
        moderator.setEmail("admin@psds.ru");
        moderator.setPassword(passwordEncoder.encode("admin"));
        moderatorRepository.save(moderator);
    }
    @Test
    void writePA_DB(){
        Group group1 = new Group(), group2 = new Group();
        User student = new User(), mentor = new User(), director = new User();
        /*группы*/
        group1.setName("Группа 1");
        group1.setDescription("Здесь должно быть описание группы 1");
        group1 = groupRepository.save(group1);

        group2.setName("Группа 2");
        group2.setDescription("Здесь должно быть описание группы 2");
        group2 = groupRepository.save(group2);
        /*пользователи*/
        student.setLastName("Петров");
        student.setFirstName("Иван");
        student.setFatherName("Сергеевич");
        student.setRole(ERole.ROLE_STUDENT);
        student.setCity("Пермь");
        student.setPhoneNumber("81234567890");
        student.setPhoto("photo");
        student.setEmail("petrov@mail.ru");
        student.setPassword(passwordEncoder.encode("petrov"));
        student = userRepository.save(student);

        mentor.setLastName("Иванов");
        mentor.setFirstName("Петр");
        mentor.setFatherName("Маратович");
        mentor.setRole(ERole.ROLE_MENTOR);
        mentor.setCity("Пермь");
        mentor.setPhoneNumber("89876543210");
        mentor.setPhoto("photo");
        mentor.setEmail("ivanov@mail.ru");
        mentor.setPassword(passwordEncoder.encode("ivanov"));
        mentor = userRepository.save(mentor);

        director.setLastName("Крапивин");
        director.setFirstName("Николай");
        director.setFatherName("Федорович");
        director.setRole(ERole.ROLE_DIRECTOR);
        director.setCity("Пермь");
        director.setPhoneNumber("84567891230");
        director.setPhoto("photo");
        director.setEmail("krapivin@mail.ru");
        director.setPassword(passwordEncoder.encode("krapivin"));
        director = userRepository.save(director);
        /*связи между пользователями*/
        RelationUsers relationUsers = new RelationUsers();
        relationUsers.setStudent(student);
        relationUsers.setMaster(mentor);
        relationUsers.setGroup(group1);
        relationUsersRepository.save(relationUsers);

        Plan plan = new Plan();
        plan.setRelationUsersId(relationUsers.getId());
        planRepository.save(plan);

        relationUsers = new RelationUsers();
        relationUsers.setStudent(mentor);
        relationUsers.setMaster(director);
        relationUsers.setGroup(group2);
        relationUsersRepository.save(relationUsers);

        plan = new Plan();
        plan.setRelationUsersId(relationUsers.getId());
        planRepository.save(plan);
        /*роли в группе*/
        /*Группа 1 - студент*/
        RoleInGroup roleInGroup = new RoleInGroup();
        roleInGroup.setUser(student);
        roleInGroup.setGroup(group1);
        roleInGroup.setRole(roleRepository.findByName(ERole.ROLE_STUDENT));
        roleInGroupRepository.save(roleInGroup);
        /*Группа 1 - ментор*/
        roleInGroup = new RoleInGroup();
        roleInGroup.setUser(mentor);
        roleInGroup.setGroup(group1);
        roleInGroup.setRole(roleRepository.findByName(ERole.ROLE_MENTOR));
        roleInGroupRepository.save(roleInGroup);
        /*Группа 2 - директор*/
        roleInGroup = new RoleInGroup();
        roleInGroup.setUser(director);
        roleInGroup.setGroup(group1);
        roleInGroup.setRole(roleRepository.findByName(ERole.ROLE_DIRECTOR));
        roleInGroupRepository.save(roleInGroup);
        /*Группа 2 - студент*/
        roleInGroup = new RoleInGroup();
        roleInGroup.setUser(mentor);
        roleInGroup.setGroup(group2);
        roleInGroup.setRole(roleRepository.findByName(ERole.ROLE_STUDENT));
        roleInGroupRepository.save(roleInGroup);
        /*Группа 2 - ментор*/
        roleInGroup = new RoleInGroup();
        roleInGroup.setUser(director);
        roleInGroup.setGroup(group2);
        roleInGroup.setRole(roleRepository.findByName(ERole.ROLE_MENTOR));
        roleInGroupRepository.save(roleInGroup);
        /*Группа 2 - директор*/
        roleInGroup = new RoleInGroup();
        roleInGroup.setUser(student);
        roleInGroup.setGroup(group2);
        roleInGroup.setRole(roleRepository.findByName(ERole.ROLE_DIRECTOR));
        roleInGroupRepository.save(roleInGroup);
    }
    /*Тесты для заполнения БД Базы знаний*/
    @Test
    void writeKB_DB(){
        Material material1 = new Material(), material2 = new Material(), material3 = new Material(),
                material4 = new Material(), material5 = new Material();
        Lesson lesson1 = new Lesson(), lesson2 = new Lesson(), lesson3 = new Lesson(),
                lesson4 = new Lesson(), lesson5 = new Lesson();
        Theme theme1 = new Theme(), theme2 = new Theme();
        SpecialistProfile specialistProfile1 = new SpecialistProfile(),
                specialistProfile2 = new SpecialistProfile();
        ThemeAndProfile themeAndProfile1 = new ThemeAndProfile(),
                themeAndProfile2 = new ThemeAndProfile(), themeAndProfile3 = new ThemeAndProfile();
        /*материалы*/
        material1.setTitle("LearnGitBranching");
        material1.setDescription("https://learngitbranching.js.org/?locale=ru_RU");

        material2.setTitle("Основы командной строки");
        material2.setDescription("https://ru.hexlet.io/courses/cli-basics");

        material3.setTitle("Введение в Git");
        material3.setDescription("https://ru.hexlet.io/courses/intro_to_git");

        material4.setTitle("Подходы к проектированию RESTful API");
        material4.setDescription("https://habr.com/ru/companies/dataart/articles/277419/");

        material5.setTitle("PostgreSQL. Изоляция транзакций");
        material5.setDescription("https://postgrespro.ru/docs/postgresql/15/transaction-iso");
        /*уроки*/
        lesson1.setTitle("Работа с Git");
        lesson1.setDescription("Основы работы с Git");
        lesson1.setLevel(1);
        material1.setLesson(lesson1);
        lesson1.setMaterial(material1);
        lesson1.setTheme(theme1);

        lesson2.setTitle("Работа с командной строкой");
        lesson2.setDescription("Основы работы с командной строкой");
        lesson2.setLevel(2);
        material2.setLesson(lesson2);
        lesson2.setMaterial(material2);
        lesson2.setTheme(theme2);

        lesson3.setTitle("Введение в Git");
        lesson3.setDescription("Изучение Git");
        lesson3.setLevel(3);
        material3.setLesson(lesson3);
        lesson3.setMaterial(material3);
        lesson3.setTheme(theme1);

        lesson4.setTitle("Подходы к проектированию RESTful API");
        lesson4.setDescription("Основы REST и красивое проектирование сервисов");
        lesson4.setLevel(4);
        material4.setLesson(lesson4);
        lesson4.setMaterial(material4);
        lesson4.setTheme(theme2);

        lesson5.setTitle("PostgreSQL");
        lesson5.setDescription("Изоляция транзакций");
        lesson5.setLevel(3);
        material5.setLesson(lesson5);
        lesson5.setMaterial(material5);
        lesson5.setTheme(theme2);
        /*профили специалиста*/
        specialistProfile1.setTitle("Истинное знание Git");
        specialistProfile1.setDescription("Данный профиль специалиста содержит темы, уроки и материалы, необходимые для  приобретения истинного знания Git");
        specialistProfile1 = specialistProfileRepository.save(specialistProfile1);

        specialistProfile2.setTitle("Backend-разработчик");
        specialistProfile2.setDescription("Данный профиль специалиста содержит темы, уроки и материалы, необходимые для приобретения навыков Backend-разработчика");
        specialistProfile2 = specialistProfileRepository.save(specialistProfile2);
        /*темы*/
        theme1.setTitle("Работа с Git");
        theme1.setDescription("Уроки по работе с системой Git");
        theme1.getLessons().add(lesson1);
        theme1.getLessons().add(lesson3);
        theme1 = themeRepository.save(theme1);

        theme2.setTitle("Основы разработки приложений");
        theme2.setDescription("Уроки по работе с командной строкой, системой Git и базой данных");
        theme2.getLessons().add(lesson2);
        theme2.getLessons().add(lesson4);
        theme2.getLessons().add(lesson5);
        theme2 = themeRepository.save(theme2);
        /*темы и профили*/
        themeAndProfile1.setTapTheme(theme1);
        themeAndProfile1.setTapSpecialistProfileId(specialistProfile1);
        themeAndProfileRepository.save(themeAndProfile1);

        themeAndProfile2.setTapTheme(theme2);
        themeAndProfile2.setTapSpecialistProfileId(specialistProfile2);
        themeAndProfileRepository.save(themeAndProfile2);

        themeAndProfile3.setTapTheme(theme1);
        themeAndProfile3.setTapSpecialistProfileId(specialistProfile1);
        themeAndProfileRepository.save(themeAndProfile3);
    }
}
