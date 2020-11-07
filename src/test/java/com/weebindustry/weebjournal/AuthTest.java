package com.weebindustry.weebjournal;

import com.google.gson.Gson;
import com.weebindustry.weebjournal.dto.LoginRequest;
import com.weebindustry.weebjournal.dto.RegisterRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private MockMvc mockMvc;
    private final RegisterRequest sampleRegisterRequest = new RegisterRequest("sample", "sample@email.com", "demo123");
    private final LoginRequest sampleLoginRequest = new LoginRequest("sample", "demo123");
    private final Gson gson = new Gson();

    @Test
    public void userRegistration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(sampleRegisterRequest))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void userLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(sampleLoginRequest))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
