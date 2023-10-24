package org.lemon.dockerbasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DockerBasicApplicationTests {
    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    @BeforeEach
    public void startUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                             .build();
    }

    @Test
    void testBasicIntegrationTest() throws Exception {
        mvc.perform(get("/person/Andrew"))
           .andExpect(status().isOk())
           .andExpect(content().json("""
                                             {
                                             \t"firstName": "Andrew",
                                             \t"lastName": "Jones"
                                             }""", true));
    }

}
