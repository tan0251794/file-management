package min.project.fms.controller;

import min.project.fms.dao.ItemMapper;
import min.project.fms.util.FileUploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest {
    @Mock
    ItemController itemController;

    @Mock
    ItemMapper itemMapper;

    @Mock
    FileUploader fileUploader;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        itemController = new ItemController(itemMapper, fileUploader);
    }

    @Test
    void detail() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
        String itemUuid = "123456789101213";
        mockMvc.perform(get("/api/" + itemUuid + "/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("{\"errno\":0,\"data\":null,\"errmsg\":\"success\"}"));
    }
}