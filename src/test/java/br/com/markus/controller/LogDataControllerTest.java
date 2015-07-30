package br.com.markus.controller;

import br.com.markus.ApplicationTests;
import br.com.markus.dto.LogDataQueryDTO;
import br.com.markus.dto.LogaDataDTO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test cases for controller
 *
 * @author Markus Kopinits
 */
public class LogDataControllerTest extends ApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

   /* @Test
    public void testQueryLog() throws Exception {
        LogDataQueryDTO logData = new LogDataQueryDTO();
        logData.setLogType(LogTypeEnum.CSTM_PRDT_VIEW.getDescription());
        logData.setTimestampFrom(String.valueOf(new Date().getTime()));
        logData.setTimestampTo(String.valueOf(new Date().getTime()));
        logData.setCustumerID("10023FA34");
        mockMvc.perform(post("/queryLog")
                .content(asJsonString(asJsonString(logData)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/

    @Test
    public void testRegisterLog() throws Exception {
        LogaDataDTO logData = new LogaDataDTO();
        logData.setAppCode("gu4a");
        logData.setLogType(LogTypeEnum.CSTM_PRDT_VIEW.getDescription());
        logData.setTimestamp(String.valueOf(new Date().getTime()));
        logData.setDataLogged("Iphone 6");
        logData.setCustumerID("10023FA34");
        mockMvc.perform(post("/saveLog")
                .content(asJsonString(logData))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
