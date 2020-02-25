package com.example.pp_lab4_poved;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InterpreterActivity extends AppCompatActivity {


    public interface Expression {

        int interpret();
    }

    public class Number implements Expression {

        private final String mNumber;

        public Number(String number) {
            mNumber = number;
        }

        @Override
        public int interpret() {
            return Integer.parseInt(mNumber);
        }
    }

    public static class Plus implements Expression {

        private final Expression mLeft;
        private final Expression mRight;

        public Plus(Expression left, Expression right) {
            mLeft = left;
            mRight = right;
        }

        @Override
        public int interpret() {
            return mLeft.interpret() + mRight.interpret();
        }
    }

    public static class Minus implements Expression {

        private final Expression mLeft;
        private final Expression mRight;

        public Minus(Expression left, Expression right) {
            mLeft = left;
            mRight = right;
        }

        @Override
        public int interpret() {
            return mLeft.interpret() - mRight.interpret();
        }
    }

    public static class Utils {

        public static boolean isOperator(String source) {
            return "+".equals(source) || "-".equals(source);
        }

        public static Expression getOperator(String source, Expression left, Expression right) {
            Expression expression = null;

            if ("+".equals(source)) {
                expression = new Plus(left, right);
            } else if ("-".equals(source)) {
                expression = new Minus(left, right);
            }

            return expression;
        }
    }



    private EditText mEditText;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpreter);
        mEditText =(EditText) findViewById(R.id.edit_text);
        mTextView = (TextView) findViewById(R.id.text_view);
    }

    public void interpret(View view) {
        String source = mEditText.getText().toString();
        int interpreted = getInterpretedText(source);
        mTextView.setText(String.valueOf(interpreted));
    }

    private int getInterpretedText(String source) {
        int result = 0;

        String[] items = source.split(" ");
        Expression[] expressions = new Expression[items.length];

        for (int i = 0; i < items.length; i++) {
            if (!Utils.isOperator(items[i])) {
                expressions[i] = new Number(items[i]);
            }
        }

        for (int i = 0; i < items.length; i++) {
            if (Utils.isOperator(items[i])) {
                expressions[i] = Utils.getOperator(items[i], expressions[i - 1], expressions[i + 1]);
                result = expressions[i].interpret();
            }
        }

        return result;
    }

}
