package org.lemon.dockerbasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lemon.dockerbasic.model.Person;
import org.lemon.dockerbasic.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
class DockerBasicApplicationTests {
    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected PersonRepository personRepository;

    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    protected MockMvc mvc;

    @BeforeEach
    public void startUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                             .build();
    }

    @Test
    void testBasicIntegrationTest() throws Exception {

        personRepository.save(Person.builder()
                                    .firstName("Andrew")
                                    .lastName("Jones")
                                    .build());

        mvc.perform(get("/person/Andrew"))
           .andExpect(status().isOk())
           .andExpect(content().json("""
                                             {
                                             \t"id": 1,
                                             \t"firstName": "Andrew",
                                             \t"lastName": "Jones"
                                             }""", true));
    }

}
