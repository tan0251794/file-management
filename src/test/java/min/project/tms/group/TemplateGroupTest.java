package min.project.tms.group;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TemplateGroupTest {
    @Mock
    TemplateGroupService templateGroupService;

    @Mock
    TemplateGroupController templateGroupController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        templateGroupController = new TemplateGroupController(templateGroupService);
    }

    @Test
    void getTemplateGroup() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(templateGroupController).build();
        mockMvc.perform(
                        get("/api/template_group/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("a"));
    }
    @Test
    void postTemplateGroup() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "test_name");
        json.put("code", "test_code");
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(templateGroupController).build();
        mockMvc.perform(
                post("/api/template_group/")
                .content(String.valueOf(json))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test_name"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("test_code"));
    }

    @Test
    void putTemplateGroup() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "testName");
        json.put("code", "testCode");
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(templateGroupController).build();
        mockMvc.perform(
                        put("/api/template_group/1/")
                                .content(String.valueOf(json))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("testName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("testCode"));
    }

}