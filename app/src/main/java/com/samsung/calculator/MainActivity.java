package com.samsung.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButtonBack;


    LinearLayout _0 , _1 , _2 , _3 , _4 , _5 , _6 , _7 , _8 , _9 ;
    TextView _0_T , _1_T , _2_T , _3_T , _4_T , _5_T , _6_T , _7_T , _8_T , _9_T ;

    LinearLayout buttonClear;
    LinearLayout buttonParentheses;
    LinearLayout buttonPercent;
    LinearLayout buttonDivision;
    LinearLayout buttonMultiplication;
    LinearLayout buttonSubtraction;
    LinearLayout buttonAddition;
    LinearLayout buttonEqual;
    LinearLayout buttonDot;
    LinearLayout buttonPlusMin;

    TextView buttonClear_T;
    TextView buttonParentheses_T;
    TextView buttonPercent_T;
    TextView buttonDivision_T;
    ImageView buttonMultiplication_T;
    ImageView buttonSubtraction_T;
    TextView buttonAddition_T;
    TextView buttonEqual_T;
    TextView buttonDot_T;
    TextView plusMin_T;


    String statement = "";

    EditText editText;
    TextView textViewResult;

    String trickAns = "";
    boolean keyPadON = true;
    ImageButton trickButton1;
    ImageButton trickButton2;
    ImageButton trickButton3;


    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = findViewById(R.id.editText);
        textViewResult = findViewById(R.id.result);

        editText.setText("0");

        prefs = new Prefs(this);



        //trick
        trickButton1 = findViewById(R.id.trickButton1);
        trickButton2 = findViewById(R.id.trickButton2);
        trickButton3 = findViewById(R.id.trickButton3);

        DoubleTapListener trickListener1 = new DoubleTapListener(trickButton1);
        trickListener1.onDoubleClick(v -> keyPadON = false);

        DoubleTapListener trickListener2 = new DoubleTapListener(trickButton2);
        trickListener2.onDoubleClick(v -> {

            int trickANs = prefs.getPrefs("trick_ans" , 0);

            Log.i("TAG", "onCreate: trick ans " + trickAns);
            statement = statement + (trickANs - Integer.parseInt(trickAns));
            editText.setText(statement);
            keyPadON = true;

        });

        DoubleTapListener trickListener3 = new DoubleTapListener(trickButton3);
        trickListener3.onDoubleClick(v -> {
            if (!statement.equals("")) {
                prefs.setPrefs("trick_ans", Integer.parseInt(statement));
                Toast.makeText(this, "" + statement, Toast.LENGTH_SHORT).show();
                statement = "";
            }
        });




        imageButtonBack = findViewById(R.id.imageButtonBack);
        imageButtonBack.setOnClickListener(view -> buttonClick(null , 'm'));


        _0 = findViewById(R.id._0);
        _1 = findViewById(R.id._1);
        _2 = findViewById(R.id._2);
        _3 = findViewById(R.id._3);
        _4 = findViewById(R.id._4);
        _5 = findViewById(R.id._5);
        _6 = findViewById(R.id._6);
        _7 = findViewById(R.id._7);
        _8 = findViewById(R.id._8);
        _9 = findViewById(R.id._9);

        _0_T = findViewById(R.id._0_T);
        _1_T = findViewById(R.id._1_T);
        _2_T = findViewById(R.id._2_T);
        _3_T = findViewById(R.id._3_T);
        _4_T = findViewById(R.id._4_T);
        _5_T = findViewById(R.id._5_T);
        _6_T = findViewById(R.id._6_T);
        _7_T = findViewById(R.id._7_T);
        _8_T = findViewById(R.id._8_T);
        _9_T = findViewById(R.id._9_T);

        buttonClear = findViewById(R.id._c);
        buttonParentheses = findViewById(R.id._br);
        buttonPercent = findViewById(R.id._per);
        buttonDivision = findViewById(R.id._dev);
        buttonMultiplication = findViewById(R.id._mul);
        buttonSubtraction = findViewById(R.id._minus);
        buttonAddition = findViewById(R.id._plus);
        buttonEqual = findViewById(R.id._equal);
        buttonPlusMin = findViewById(R.id._plus_min);
        buttonDot = findViewById(R.id._dot);

        buttonClear_T = findViewById(R.id._c_T);
        buttonParentheses_T = findViewById(R.id._br_T);
        buttonPercent_T = findViewById(R.id._per_T);
        buttonDivision_T = findViewById(R.id._dev_T);
        buttonMultiplication_T = findViewById(R.id._mul_T);
        buttonSubtraction_T = findViewById(R.id._minus_T);
        buttonAddition_T = findViewById(R.id._plus_T);
        buttonEqual_T = findViewById(R.id._equal_T);
        buttonDot_T = findViewById(R.id._dot_T);
        plusMin_T = findViewById(R.id._plus_min_T);

        _0.setOnClickListener(view -> buttonClick(_0_T , '0'));
        _1.setOnClickListener(view -> buttonClick(_1_T , '1'));
        _2.setOnClickListener(view -> buttonClick(_2_T , '2'));
        _3.setOnClickListener(view -> buttonClick(_3_T , '3'));
        _4.setOnClickListener(view -> buttonClick(_4_T , '4'));
        _5.setOnClickListener(view -> buttonClick(_5_T , '5'));
        _6.setOnClickListener(view -> buttonClick(_6_T , '6'));
        _7.setOnClickListener(view -> buttonClick(_7_T , '7'));
        _8.setOnClickListener(view -> buttonClick(_8_T , '8'));
        _9.setOnClickListener(view -> buttonClick(_9_T , '9'));

        buttonClear.setOnClickListener(view ->          buttonClick(buttonClear_T , 'c'));
        buttonParentheses.setOnClickListener(view ->    buttonClick(buttonParentheses_T , '('));
        buttonPercent.setOnClickListener(view ->        buttonClick(buttonPercent_T , '%'));
        buttonDivision.setOnClickListener(view ->       buttonClick(buttonDivision_T , '÷'));
        buttonMultiplication.setOnClickListener(view -> buttonClick(buttonMultiplication_T , '×'));
        buttonSubtraction.setOnClickListener(view ->    buttonClick(buttonSubtraction_T , '-'));
        buttonAddition.setOnClickListener(view ->       buttonClick(buttonAddition_T , '+'));
        buttonEqual.setOnClickListener(view ->          buttonClick(buttonEqual_T , '='));
        buttonDot.setOnClickListener(view ->            buttonClick(buttonDot_T , '.'));
        buttonPlusMin.setOnClickListener(view ->        buttonClick(plusMin_T , 'P'));



    }


    void buttonClick(View viewA , char clickChar) {
        if (!keyPadON) return;

        if (viewA != null)viewA.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));


        //on back click
        if (String.valueOf(clickChar).equals("m")) {
            if (!statement.equals("")) statement = statement.substring(0, statement.length() - 1);

            if (statement.equals("")) {
                editText.setText("0");
                textViewResult.setText("");
                return;
            }

        }
        //on clear click
        else if (String.valueOf(clickChar).equals("c")) {
            statement = "";
            editText.setText("0");
            textViewResult.setText("");
            return;
        }
        //on equal click
        else if (String.valueOf(clickChar).equals("=")){
            calculate(statement);
            return;

        }//on ( ) click
        else if (String.valueOf(clickChar).equals("(")){
            if (statement.length() > 0 && statement.charAt(statement.length() - 1 ) == '('){
                statement = statement + "(";

                editText.setText(statement);
                return;
            }

            int count = 0;

            boolean b = false;
            for (int i = statement.length() - 1; i >= 0; i--) {
                char c = statement.charAt(i);

                if (c == ')') count++;

                if (c == '('){
                    if (count == 0) {
                        statement = statement + ")";
                        b = true;
                        break;
                    }
                    else count--;
                }
            }

            if (!b) {
                if (statement.equals("")) statement = "(";
                else statement = statement + "×(";
            }

            //set text;
            editText.setText(statement);

            return;

        }




        //if operator already present
        if (!isNumber("" + clickChar)){
            if(statement.isEmpty()) {
                if (textViewResult.getText().length() == 0) return;
                else {
                    statement = textViewResult.getText().toString();
                }
            }
            if (!isNumber("" + statement.charAt(statement.length() - 1))){
                if (statement.charAt(statement.length() - 1 ) != ')'){
                    statement = statement.substring(0 , statement.length() - 1);
                }
            }
        }


        if (statement.length() > 0 && statement.charAt(statement.length() - 1 ) == ')'){
            statement = statement + "×";
        }

        //add char at statement
        if (!String.valueOf(clickChar).equals("m"))
            statement = statement + clickChar;


        //set text;
        editText.setText(statement);


        textViewResult.setText("");
    }

    @SuppressLint("SetTextI18n")
    public void calculate(String s){
        if (s.isEmpty()) return;
        if (!isNumber("" + s.charAt(s.length() - 1))){
            Toast.makeText(this, "Invalid format used.", Toast.LENGTH_SHORT).show();
            return;
        }


        //check complete bracket
        int a = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') a++;
            if(ch == ')') a--;
        }

        for (int i = 0; i < a; i++) statement = statement.concat(")");
        editText.setText(statement);



        ArrayList<String> inToPost = infix_to_postfix(s);

        Log.i("TAG" , "Calculate:-");
        for (String i: inToPost) {
            Log.i("TAG" , "-- " + i);
        }

        if (!inToPost.isEmpty()) {
            String ansString = postFixAns(inToPost);
            Log.i("TAG", "calculate: ans " + ansString);

            int dotANs = -1;
            for (int i = 0; i < ansString.length(); i++) {
                if (ansString.charAt(i) == '.'){
                    dotANs = i;
                    break;
                }
            }

            if (dotANs != -1){
                if (ansString.length() > dotANs + 6){
                    ansString = ansString.substring(0, dotANs + 6);
                }
            }


            textViewResult.setText("" + ansString);



            //remove uses zero
            int ansDot = -1;
            int ans00 = -1;
            for (int i = ansString.length() - 1; i >= 0; i--) {
                if (ansString.charAt(i) == '0') {
                    ans00 = i;
                }
                if (ansString.charAt(i) == '.') {
                    ansDot = i;
                    break;
                }
            }

            if (ansDot != -1){
                if (ans00 != -1) {
                    if (ansDot + 1 == ans00) {
                        textViewResult.setText("" + ansString.substring(0, ansDot));
                    } else {
                        textViewResult.setText("" + ansString.substring(0, ans00));
                    }
                }
            }


            // set trick ans
            trickAns = (String) textViewResult.getText();

        }


        statement = "";
    }


    public ArrayList<String> infix_to_postfix(String string){
        if (string.equals("")) return null;

        ArrayList<String> ans = new ArrayList<>();
        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (isNumber("" + c)) {

                int numberEND = -1;
                for (int j = i + 1; j < string.length(); j++) {
                    char ca = string.charAt(j);

                    if(!isNumber("" + ca)) break;
                    numberEND = j;
                }
                if (numberEND == -1){
                    ans.add("" + c);
                }else {
                    ans.add("" + string.substring(i, numberEND + 1));
                    i = numberEND;
                }

            }

            else if(c == '(')  stack.add('(');
            else if(c == ')'){

                while (stack.peek() != '(' ) {
                    ans.add("" + stack.pop());
                }

                stack.pop();
            }

            else {
                if (!stack.empty() && !preference("" + stack.peek(), "" + c)) {
                    ans.add("" + stack.pop());
                }
                stack.add(c);
            }
        }

        while (!stack.isEmpty()) ans.add("" + stack.pop());

        return ans;
    }

    public String postFixAns(ArrayList<String> arrayList){

        Log.i("TAG", "postFixAns: " + arrayList.size());

        Stack<String> stack = new Stack<>();

        for (String c : arrayList){
            if (isNumber(c)) {
                stack.add(c);
            }
            else {
                String bString = stack.pop();
                String aString = stack.pop();

                float b = Float.parseFloat(bString);
                float a = Float.parseFloat(aString);

                if (c.equals("+")) stack.add(String.valueOf(a + b));
                if (c.equals("-")) stack.add(String.valueOf(a - b));
                if (c.equals("×")) stack.add(String.valueOf(a * b));
                if (c.equals("÷")) stack.add(String.valueOf(a / b));
            }
        }


        return stack.pop();

    }



    public boolean isNumber(String c){
        return !(c.equals("+") || c.equals("-") || c.equals("×") || c.equals("÷") || c.equals("(") || c.equals(")"));
    }

    public boolean preference(String a , String b){
        if (a.equals("(")) return true;
        if (a.equals(b)) return false;
        return !(
                (a.equals("+") && b.equals("-")) || (a.equals("×") && Objects.equals(b, "÷")) ||
                        (a.equals("-") && Objects.equals(b, "+")) || (a.equals("÷") && b.equals("×"))
        );
    }


}