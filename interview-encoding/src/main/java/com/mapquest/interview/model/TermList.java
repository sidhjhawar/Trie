package com.mapquest.interview.model;

import java.util.ArrayList;
import java.util.List;

public class TermList {

    List<Term> termList;

    public TermList() {
    }

    public TermList(List<Term> termList) {
        this.termList = termList;
    }

    @Override
    public String toString() {
        return "TermList{" +
                "termList=" + termList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TermList termList1 = (TermList) o;

        return termList != null ? termList.equals(termList1.termList) : termList1.termList == null;

    }

    @Override
    public int hashCode() {
        return termList != null ? termList.hashCode() : 0;
    }

    public List<Term> getTermList() {
        return termList;
    }
}
