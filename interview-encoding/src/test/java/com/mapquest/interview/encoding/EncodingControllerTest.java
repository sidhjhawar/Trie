package com.mapquest.interview.encoding;

import com.mapquest.interview.model.Term;
import com.mapquest.interview.processor.EncodingProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class EncodingControllerTest {
    EncodingController encodingController;
    HttpServletResponse response;
    MockMvc mockMvc;
    @Autowired
    EncodingProcessor encodingProcessor;

    @Before
    public void setUp(){
        encodingController =  new EncodingController();
    }

    @Test
    public void getAllTermsTest(){
        Assert.assertNotNull(encodingController.getAll());
    }

    @Test
    public void get() throws Exception {
        List<Term> words = new ArrayList<Term>();
        Term term1 = new Term();
        term1.setSuffix("dig");
        term1.setBackRef(-1);
        Term term2 = new Term();
        term2.setSuffix("est");
        term2.setBackRef(0);
        words.add(term1);
        words.add(term2);
        when(encodingProcessor.getAllTerms()).thenReturn(words);

    }
}
