package com.mapquest.interview.encoding;

import com.mapquest.interview.model.Term;
import com.mapquest.interview.processor.EncodingProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EncodingControllerTest {
    MockMvc mockMvc;
    @Mock
    EncodingProcessor encodingProcessor;
    EncodingController encodingController;
    Term term;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new EncodingController()).build();
        encodingController =  new EncodingController();
        term = new Term();
    }

    @Test
    public void getAllTermsTest() throws Exception {
        this.mockMvc.perform(get("/terms")
                .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));


    /*    ArrayList<Term> terms = new ArrayList<Term>();
        term.setBackRef(-1);
        term.setSuffix("dig");
        terms.add(term);
        when(encodingProcessor.getAllTerms()).thenReturn(terms);
        ArrayList<Term> expectedTerms = new ArrayList<Term>();
        expectedTerms = ( ArrayList<Term>)encodingController.getAll();
        Assert.assertEquals(terms,expectedTerms);
        Assert.assertNotNull(encodingController.getAll());*/
    }


}
