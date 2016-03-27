package com.mapquest.interview.model;

import java.util.ArrayList;

/**
 * Created by owner on 3/19/2016.
 */
public class Term {
    int backRef;
    String suffix;

    public Term(int backRef, String suffix) {
        this.backRef = backRef;
        this.suffix = suffix;
    }

    public Term(){

    }
    public int getBackRef() {
        return backRef;
    }

    public void setBackRef(int backRef) {
        this.backRef = backRef;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
