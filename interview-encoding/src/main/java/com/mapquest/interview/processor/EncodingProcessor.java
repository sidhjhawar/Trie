package com.mapquest.interview.processor;

import com.mapquest.interview.dao.WordDao;
import com.mapquest.interview.model.Term;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by owner on 3/19/2016.
 */

@Component
public class EncodingProcessor {

  //  @Autowired
    private WordDao wordDao = new WordDao();
    public static int index = 0;
    private ArrayList<String> wordsList;

    public EncodingProcessor(){
        wordsList = new ArrayList<String>();
    }

    public boolean addWords(String word){
        if(!wordsList.contains(word) && !"".equalsIgnoreCase(word) && word!=null){
            wordsList.add(word);
            sortWords();
            wordDao.clearAllDao();
            EncodingProcessor.setIndex(0);
            for(int i = 0; i < wordsList.size() ; i++){
                generateTerms(wordsList.get(i));
            }
            return true;
        }
        return false;
    }

    public List<Term> getAllTerms(){
        return wordDao.getAllTerms();
    }

    public void generateTerms(String word){
            Term newTerm = new Term();
            newTerm.setBackRef(-1);
            newTerm.setSuffix(word);
            if (wordDao.getWords().size() == 0) {
                wordDao.addWord(index++, newTerm);
            }
            else{
                Iterator it = WordDao.getWords().entrySet().iterator();
                int j = 0;
                char[] newLetter = word.toCharArray();
                while(it.hasNext() && newLetter.length!=0){
                    Map.Entry pair = (Map.Entry)it.next();
                    Term term = (Term)pair.getValue();
                    char[] letters = term.getSuffix().toCharArray();
                    for( j = 0 ; j < letters.length &&  j < newLetter.length  ; j++){
                        if(!(letters[j] == newLetter[j])) break;
                    }
                    if(j == letters.length ){ //If prefix is a perfect match
                        String suffix = new String(newLetter);
                        if(suffix != null) {
                            newTerm.setBackRef((Integer)pair.getKey());
                            newTerm.setSuffix(suffix.substring(j)); // Add the rest of the word as a term
                            newLetter = suffix.substring(j).toCharArray();

                        }
                    }
                }
                if(newTerm.getSuffix().length() > 0) {  //If by the end of the loop the suffix of length of more then 0 , add the term.
                    wordDao.addWord(index++, newTerm);
                }
            }

    }



    public boolean deleteWord(String word){
        StringBuffer wordFormed = new StringBuffer();
        Term term = null;
        boolean wordFound = false;
        int indexToBeRemoved = 999;
        if(wordsList.contains(word)) {
            for (int i = 0; i < WordDao.getAllTerms().size(); i++) {
                wordFormed = new StringBuffer();
                term = WordDao.getAllTerms().get(i);
                wordFormed.append(term.getSuffix());
                if (wordFormed.toString().equals(word)) {
                    wordFound = true;
                    wordsList.remove(wordFormed.toString());
                    resetAllBackRef(i);
                    break;
                }
                for (int j = i + 1; j < WordDao.getAllTerms().size(); j++) {
                    Term otherTerm = WordDao.getAllTerms().get(j);
                    wordFormed.append(otherTerm.getSuffix());
                    if (wordFormed.toString().equals(word)) {
                        indexToBeRemoved = j;
                        wordsList.remove(word);
                        resetAllBackRef(indexToBeRemoved);
                        wordFound = true;
                        break;
                    }

                }
                if (wordFound) break;

            }
        }

        return wordFound;
    }



    public void resetAllBackRef(int indexToBeRemoved){
        WordDao.getWords().remove(indexToBeRemoved);
        HashMap<Integer, String> clonedWordList = (HashMap<Integer, String>) WordDao.getWords().clone();
        ArrayList<String> clonedTerms = (ArrayList<String>) wordsList.clone();
        wordsList.clear();
        wordDao.clearAllDao();
        index = 0;
        for(int i = 0; i < clonedTerms.size(); i++){
            addWords(clonedTerms.get(i));
        }
    }

    public void sortWords(){
        Collections.sort(wordsList);
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        EncodingProcessor.index = index;
    }

    public  void clearWordList(){
        wordsList.clear();
    }

}
