package com.mapquest.interview.dao;

import com.mapquest.interview.model.Term;
import com.mapquest.interview.model.TermList;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by owner on 3/19/2016.
 */
public class WordDao {

    public static HashMap<Integer, Term> words = new HashMap();
    public static List<Term> termList = new ArrayList<Term>();


    public static HashMap<Integer, Term> getWords() {
        return words;
    }

    public static void setWords(HashMap<Integer, Term> words1) {
        words = words1;
    }

    public void addWord(int key, Term word) {
        words.put(key, word);
        termList.add(word);
    }

    public void clearAllDao(){
        words.clear();
        termList.clear();
    }

    public static List<Term> getAllTerms() {

        return termList;
    }
}