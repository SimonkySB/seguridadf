package com.simonky.seguridadf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
public class MvcConfigTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("home"));
    }

   

    @Test
    public void testSearchPage() throws Exception {
        mockMvc.perform(get("/search"))
               .andExpect(status().isOk())
               .andExpect(view().name("search"));
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
               .andExpect(status().isOk())
               .andExpect(view().name("login"));
    }

    @Test
    public void testDetalleRecetaPage() throws Exception {
        mockMvc.perform(get("/detalle-receta").with(user("username").password("password").roles("USER")))
            .andExpect(status().isOk())
            .andExpect(view().name("detalle-receta"));
    }

    @Test
    public void testDetalleRecetaRedirection() throws Exception {
        mockMvc.perform(get("/detalle-receta"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("http://localhost/login"));
    }
}
