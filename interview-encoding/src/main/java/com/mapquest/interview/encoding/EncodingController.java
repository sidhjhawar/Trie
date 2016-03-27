package com.mapquest.interview.encoding;

import com.mapquest.interview.model.Term;
import com.mapquest.interview.processor.EncodingProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class EncodingController {
    private List<Term> termList = new ArrayList<Term>();
    @Autowired
    EncodingProcessor encodingPrcoessor;

    @RequestMapping(value = "/terms", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getAll() {
        return encodingPrcoessor.getAllTerms();
    }

    @RequestMapping(value = "/terms/{term}", method = PUT)
    public @ResponseBody void addTerm(@PathVariable String term, HttpServletResponse response) {
        encodingPrcoessor.addWords(term);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value = "/terms/{term}", method = DELETE)
    public @ResponseBody void deleteTerm(@PathVariable String term, HttpServletResponse response) {
        encodingPrcoessor.deleteWord(term);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
