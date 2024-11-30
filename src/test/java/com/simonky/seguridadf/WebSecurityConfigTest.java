package com.simonky.seguridadf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(WebSecurityConfig.class) 
public class WebSecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPublicEndpoints() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(get("/search"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testPrivateEndpointsWithUser() throws Exception {
        mockMvc.perform(get("/detalle-receta"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPrivateEndpointsWithoutUser() throws Exception {
        mockMvc.perform(get("/detalle-receta"))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/login"));
    }
    
    // Verificar login con credenciales correctas
    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
