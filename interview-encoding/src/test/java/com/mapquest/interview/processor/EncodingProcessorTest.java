package com.mapquest.interview.processor;

import com.mapquest.interview.dao.WordDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class EncodingProcessorTest {
    EncodingProcessor encodingProcessor;
    WordDao wordDao;

    @Before
    public void setUp(){
        encodingProcessor =  new EncodingProcessor();
        wordDao =  new WordDao();
        wordDao.clearAllDao();
    }

    @Test
    public void testAddWords(){
        Assert.assertTrue(encodingProcessor.addWords("dig"));
        Assert.assertTrue(encodingProcessor.addWords("digress"));
        Assert.assertTrue(encodingProcessor.addWords("digest"));
        Assert.assertTrue(encodingProcessor.addWords("digresses"));
        Assert.assertFalse(encodingProcessor.addWords(""));
        Assert.assertFalse(encodingProcessor.addWords(null));
    }

    @Test
    public void testGetAllTerms(){
        encodingProcessor.generateTerms("dig");
        encodingProcessor.generateTerms("digest");
        encodingProcessor.generateTerms("digress");
        encodingProcessor.generateTerms("digresses");
        Assert.assertNotNull(encodingProcessor.getAllTerms());
        Assert.assertEquals(4,encodingProcessor.getAllTerms().size());
    }

    @Test
    public void testAddTerm(){
        encodingProcessor.addWords("dig");
        encodingProcessor.addWords("digest");
        encodingProcessor.addWords("digress");
        encodingProcessor.addWords("digresses");
        encodingProcessor.addWords("elephant");
        Assert.assertEquals(5,encodingProcessor.getAllTerms().size());
    }

    @Test
    public void testDeleteTerm(){
        encodingProcessor.addWords("dig");
        encodingProcessor.addWords("digest");
        encodingProcessor.addWords("digress");
        encodingProcessor.addWords("digresses");
        Assert.assertFalse(encodingProcessor.deleteWord("abcd"));
        Assert.assertTrue(encodingProcessor.deleteWord("digest"));
        Assert.assertFalse(encodingProcessor.deleteWord(""));
        Assert.assertFalse(encodingProcessor.deleteWord(null));
        Assert.assertEquals(3,encodingProcessor.getAllTerms().size());
    }
}
