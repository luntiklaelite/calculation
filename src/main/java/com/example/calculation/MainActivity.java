package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    static sign nowSign = null;
    boolean isSecond = false;
    EditText textbox;
    private float nowint = 0, oldint = 0;

    public void getItog(View view) {
        if(nowSign == null)
            return;
        float itog = 0;
        switch(nowSign) {
            case plus:
                itog = oldint + nowint;
                break;
            case minus:
                itog = oldint - nowint;
                break;
            case division:
                if(nowint == 0) { textbox.setText("ERROR. DIVISION BY 0"); return; }
                itog = oldint / nowint;
                break;
            case multiply:
                itog = oldint * nowint;
                break;
        }
        textbox.setText(Float.toString(itog));
        oldint = 0;
        nowSign = null;
        isSecond = false;
        nowint = itog;
        itog = 0;
    }

    enum sign {
        plus,
        multiply,
        minus,
        division
        ;
        char getChar() {
            switch(nowSign) {
                case plus:
                    return '+';
                case minus:
                    return '-';
                case multiply:
                    return '*';
                case division:
                    return '/';
                default:
                    return '!';
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void inputNumber(View view) {
        int number;
        Button pressed = (Button) view;
        switch(pressed.getText().toString()) {
            case "1":
                number = 1;
                break;
            case "2":
                number = 2;
                break;
            case "3":
                number = 3;
                break;
            case "4":
                number = 4;
                break;
            case "5":
                number = 5;
                break;
            case "6":
                number = 6;
                break;
            case "7":
                number = 7;
                break;
            case "8":
                number = 8;
                break;
            case "9":
                number = 9;
                break;
            default:
                number = 0;
                break;
        }
        nowint *= 10;
        nowint += number;
        refreshTextBox();
    }

    public void refreshTextBox() {
        textbox  = findViewById(R.id.textbox1);
        if(isSecond) {
            textbox.setText(Float.toString(oldint) + nowSign.getChar() + Float.toString(nowint));
        }
        else if(nowSign != null) {
            isSecond = true;
            textbox.setText(Float.toString(oldint) + nowSign.getChar());
        }
        else {
            textbox.setText(Float.toString(nowint));
        }

    }

    public void inputSign(View view) {
        Button pressed = (Button) view;
        switch(pressed.getText().toString()) {
            case "+":
                nowSign = sign.plus;
                break;
            case "*":
                nowSign = sign.multiply;
                break;
            case "-":
                nowSign = sign.minus;
                break;
            case "/":
                nowSign = sign.division;
                break;
        }
        if(!isSecond) {
            oldint = nowint;
            nowint = 0;
        }
        refreshTextBox();
    }
}