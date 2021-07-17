package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.io.StringWriter;

public class MainActivity extends AppCompatActivity {

    TextView expression, answer;

    Button one, two, three, four, five, six, seven, eight, nine, zero, ac, backspace, equalto, percentage, div, mul, sub, add;

    String data = "";
    String ans = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Vibrator vibrate = (Vibrator) getSystemService(MainActivity.VIBRATOR_SERVICE);

        one = findViewById(R.id.button_one);
        two = findViewById(R.id.button_two);
        three = findViewById(R.id.button_three);
        four = findViewById(R.id.button_four);
        five = findViewById(R.id.button_five);
        six = findViewById(R.id.button_six);
        seven = findViewById(R.id.button_seven);
        eight = findViewById(R.id.button_eight);
        nine = findViewById(R.id.button_nine);
        zero = findViewById(R.id.button_zero);

        /////////////////////////////////////

//        ac = findViewById(R.id.button_ac);
        backspace = findViewById(R.id.button_back);
        equalto = findViewById(R.id.button_equal);
        div = findViewById(R.id.button_div);
        mul = findViewById(R.id.button_mul);
        add = findViewById(R.id.button_add);
        percentage = findViewById(R.id.button_percentage);
        sub = findViewById(R.id.button_sub);




        expression = findViewById(R.id.expression);
        answer = findViewById(R.id.answer);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+one.getText().toString();
                answer.setText(data);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+two.getText().toString();
                answer.setText(data);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+three.getText().toString();
                answer.setText(data);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+four.getText().toString();
                answer.setText(data);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+five.getText().toString();
                answer.setText(data);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+six.getText().toString();
                answer.setText(data);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+seven.getText().toString();
                answer.setText(data);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+eight.getText().toString();
                answer.setText(data);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+nine.getText().toString();
                answer.setText(data);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String data;
                data = answer.getText().toString()+zero.getText().toString();
                answer.setText(data);
            }
        });

        ///////////////////////////////////

//        ac.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                String data;
//                data = "";
//                ans = "";
//                expression.setText("");
//                answer.setText("");
//
//            }
//        });



        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.length() > 1) {
                    data = data.substring(0, data.length() - 1);
                    expression.setText(ans);
                    answer.setText(data);
                    vibrate.vibrate(10);
                }
                else if (data.length() <= 1) {
                    expression.setText("");
                    data = "";
                    answer.setText(data);
                }

            }
        });

        backspace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                data = "";
                ans = "";
                vibrate.vibrate(20);
                expression.setText("");
                answer.setText("");
                return false;
            }
        });

        equalto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.setText(data);
                data = data.replaceAll("×","*");
                data = data.replaceAll("÷","/");
//                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();

//                Scriptable scriptable = rhino.initStandardObjects();
//                result = rhino.evaluateString(scriptable, data,"javascript", 1, null).toString();
//                answer.setText(result);
                if (data != "") {
                    Context rhino = Context.enter();
                    rhino.setOptimizationLevel(-1);
                    String result;
                    Scriptable scriptable = rhino.initStandardObjects();
                    ans = rhino.evaluateString(scriptable, data,"javascript", 1, null).toString();
//                    ans = ans.replaceAll(".0","");
                    ans = ans.replace(".0", "");
                    if (ans == "Infinity") {
                        answer.setText("∞");
                        ans="0";
                        data="0";
                    }
                    else {
                        answer.setText(ans);
                    }
                }
                else if (data == ""){
                    answer.setText("");
                }
            }
        });

        ////////////////////////////////////

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = answer.getText().toString()+add.getText().toString();
                answer.setText(data);

//                if (ans != ""){
//                    data = ans+add.getText().toString();
//                    answer.setText(data);
//                }
//                else{
//                    data = answer.getText().toString()+add.getText().toString();
//                    answer.setText(data);
//                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = answer.getText().toString()+sub.getText().toString();
                answer.setText(data);
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = answer.getText().toString()+mul.getText().toString();
                answer.setText(data);
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = answer.getText().toString()+div.getText().toString();
                answer.setText(data);
            }
        });

        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (answer.getText().toString().contains("+") || answer.getText().toString().contains("-") || answer.getText().toString().contains("×") || answer.getText().toString().contains("÷")) {
//                    answer.setText("Invalid");
//                }
//                else {
//                    int num = Integer.parseInt(answer.getText().toString());
//                    answer.setText(String.format("", num%));
//                }

            }
        });



    }

    private class VIBRATOR_SERVICE {
    }
}