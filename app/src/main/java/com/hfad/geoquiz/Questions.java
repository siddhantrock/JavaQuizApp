package com.hfad.geoquiz;

public class Questions
{
    private int mTextResID;
    private boolean mAnswer;

    public Questions(int textResID, boolean answer) {
        mTextResID = textResID;
        mAnswer = answer;
    }

    public int getTextResID() {
        return mTextResID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setTextResID(int textResID) {
        mTextResID = textResID;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
