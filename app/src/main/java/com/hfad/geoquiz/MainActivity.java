package com.hfad.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
    private Button mTrueBtn, mFalseBtn, mNextBtn,mPrevioousBtn;
    private TextView mQuestionTextView;
    private Questions[] mQuestions = {new Questions ( R.string.first,true ),
                                        new Questions ( R.string.second,false ),
                                        new Questions ( R.string.third,true ),
                                        new Questions ( R.string.fourth,true ),
                                        new Questions ( R.string.fifth,true )};

    private int count=0;

    private ArrayList<Integer> mAnswerGived = new ArrayList <Integer> ();
    private int marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        mFalseBtn = findViewById ( R.id.false_btn_id );
        mTrueBtn = findViewById ( R.id.true_btn_id );
        mNextBtn = findViewById ( R.id.next_btn_id );
        mQuestionTextView = findViewById ( R.id.question_tv );
        mPrevioousBtn = findViewById ( R.id.previous_btn_id );

        if(savedInstanceState != null)
        {
            count = savedInstanceState.getInt ( "count" );
        }

        mFalseBtn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                mAnswerGived.add ( count );
                checkAnswer ( false );
            }
        } );

        mTrueBtn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                mAnswerGived.add ( count );
                checkAnswer ( true );
            }
        } );

        mQuestionTextView.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                count = (count + 1) % mQuestions.length;
                mQuestionTextView.setText ( mQuestions[count].getTextResID () );
            }
        } );

        mNextBtn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                count = count + 1;

                if(count>4)
                {
                    Toast.makeText ( MainActivity.this,"Score = " + marks,Toast.LENGTH_LONG ).show ();
                }

                count = count % mQuestions.length;

                mQuestionTextView.setText ( mQuestions[count].getTextResID () );

                if(mAnswerGived.contains ( count ))
                {
                    mTrueBtn.setEnabled ( false );
                    mFalseBtn.setEnabled ( false );
                }
                else
                {
                    mTrueBtn.setEnabled ( true );
                    mFalseBtn.setEnabled ( true );
                }
            }
        } );
        mQuestionTextView.setText ( mQuestions[count].getTextResID () );

        mPrevioousBtn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                count = count - 1;

                if(count<0)
                {
                    count=4;
                }



                mQuestionTextView.setText ( mQuestions[count].getTextResID () );

                if(mAnswerGived.contains ( count ))
                {
                    mTrueBtn.setEnabled ( false );
                    mFalseBtn.setEnabled ( false );
                }
                else
                {
                    mTrueBtn.setEnabled ( true );
                    mFalseBtn.setEnabled ( true );
                }
            }
        } );
    }

    private void checkAnswer(boolean userAnswer)
    {
        boolean actualAnswer = mQuestions[count].isAnswer ();

        if(actualAnswer == userAnswer)
        {
            Toast.makeText ( MainActivity.this,"Correct",Toast.LENGTH_SHORT ).show ();
            marks+=4;
        }
        else
        {
            Toast.makeText ( MainActivity.this,"Incorrect",Toast.LENGTH_SHORT ).show ();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState ( outState );
        outState.putInt ( "count",count );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState ( savedInstanceState );
        Toast.makeText ( MainActivity.this,"onrestore method",Toast.LENGTH_SHORT ).show ();
    }
}
