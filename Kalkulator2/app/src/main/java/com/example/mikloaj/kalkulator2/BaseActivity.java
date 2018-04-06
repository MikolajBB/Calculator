package com.example.mikloaj.kalkulator2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    protected boolean isEqualClickedAgain = false;
    protected String lastButton = "";
    protected boolean canClear = false;
    protected static final char ADDITION = '+';
    protected static final char SUBTRACTION = '-';
    protected static final char MULTIPLICATION = '*';
    protected static final char DIVISION = '/';
    protected static final char NUllOPERATION = '0';
    protected char currentOperation = NUllOPERATION;
    protected double valueOne = Double.NaN;
    protected double valueTwo;
    protected double tempValue;


    protected List<Button> numberButtons;
    protected Button addition;
    protected Button subtraction;
    protected Button multiplication;
    protected Button division;
    protected Button plusMinus;
    protected Button bksp;
    protected Button clear;
    protected Button equals;
    protected Button comma;
    protected TextView resultView;
    protected String textContent;
    protected int id[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        initialize();
        if (savedInstanceState != null) {
            textContent = (savedInstanceState.getCharSequence("textContent")).toString();
            resultView.setText(textContent);

            valueOne = savedInstanceState.getDouble("valueOne");
            tempValue = savedInstanceState.getDouble("tempValue");
            valueTwo = savedInstanceState.getDouble("valueTwo");
            isEqualClickedAgain = savedInstanceState.getBoolean("isEqualClickedAgain");
            currentOperation = savedInstanceState.getChar("currentOperation");
            canClear = savedInstanceState.getBoolean("canClear");
            lastButton = savedInstanceState.getString("lastButton");
        }


        for (final Button num : numberButtons) {
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastButton.equals("=")) {
                        clearValues();
                    }
                    if (!canClear) {
                        resultView.append(num.getText());
                    } else {
                        resultView.setText(num.getText());
                        canClear = false;

                    }
                    lastButton = "num";
                }
            });
        }

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = ADDITION;
                    compute(addition);
                }
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = SUBTRACTION;
                    compute(subtraction);
                }
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = DIVISION;
                    compute(division);
                }
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = MULTIPLICATION;
                    compute(multiplication);
                }
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    compute(equals);
                    isEqualClickedAgain = true;
                    lastButton = equals.getText().toString();
                }
            }
        });

        comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!lastButton.equals("=") || !content.isEmpty()) {
                    if (!content.contains(".") && !content.isEmpty())
                        resultView.append(comma.getText());
                    else if(!content.contains(".") && content.isEmpty()){
                        resultView.setText("0.");
                    }
                    lastButton = comma.getText().toString();
                }
            }
        });

        bksp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!lastButton.equals("=") || !content.isEmpty()) {
                    if (content.length() >= 1) {
                        resultView.setText(resultView.getText().subSequence(0, content.length() - 1));
                        if(content.length() == 2 && content.startsWith("-"))
                            resultView.setText("0");
                    }
                    lastButton = bksp.getText().toString();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(null);
                clearValues();
                lastButton = clear.getText().toString();
            }
        });

        plusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    if (content.startsWith("-")) {
                        resultView.setText(content.substring(1));
                    } else {
                        resultView.setText(new StringBuilder(content).insert(0, '-'));
                    }
                    lastButton = plusMinus.getText().toString();
                }
            }
        });

    }

    protected void clearValues() {
        valueOne = Double.NaN;
        tempValue = Double.NaN;
        isEqualClickedAgain = false;
        currentOperation = NUllOPERATION;
    }

    protected abstract int getLayoutResourceId();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("textContent", resultView.getText());
        outState.putDouble("valueOne", valueOne);
        outState.putDouble("tempValue", tempValue);
        outState.putDouble("valueTwo", valueTwo);
        outState.putBoolean("isEqualClickedAgain",isEqualClickedAgain);
        outState.putChar("currentOperation",currentOperation);
        outState.putBoolean("canClear",canClear);
        outState.putString("lastButton",lastButton);
}

    protected void initialize() {
        numberButtons = new ArrayList<>();
        id = new int[]{
                R.id.num0,
                R.id.num1,
                R.id.num2,
                R.id.num3,
                R.id.num4,
                R.id.num5,
                R.id.num6,
                R.id.num7,
                R.id.num8,
                R.id.num9,
        };

        for (int i : id) {
            Button button = findViewById(i);
            numberButtons.add(button);
        }
        textContent = "";
        resultView = findViewById(R.id.result);
        resultView.setText(textContent);
        addition = findViewById(R.id.addition);
        subtraction = findViewById(R.id.subtraction);
        multiplication = findViewById(R.id.multiplication);
        division = findViewById(R.id.division);
        plusMinus = findViewById(R.id.plusMinus);
        bksp = findViewById(R.id.bksp);
        clear = findViewById(R.id.cancel);
        equals = findViewById(R.id.equals);
        comma = findViewById(R.id.comma);
    }

    protected void compute(Button button) {

        if (!button.equals(equals)) {
            isEqualClickedAgain = false;
            valueOne = Double.NaN;
        }

        if (!Double.valueOf(valueOne).isNaN()) {
            if (!isEqualClickedAgain) {
                valueTwo = Double.parseDouble(resultView.getText().toString());
                tempValue = valueTwo;
            } else {
                valueTwo = tempValue;
            }
            chooseOperation();

        } else {
            valueOne = Double.parseDouble(resultView.getText().toString());
        }
        canClear = true;
        resultView.setText(String.valueOf(valueOne));
        lastButton = button.getText().toString();
    }

    protected void chooseOperation() {
        switch (currentOperation) {

            case ADDITION:
                valueOne += valueTwo;
                break;

            case SUBTRACTION:
                valueOne -= valueTwo;
                break;

            case MULTIPLICATION:
                valueOne *= valueTwo;
                break;

            case DIVISION:
                if (valueTwo == 0) {
                    displayToast(R.string.err_devide_zero);
                } else
                    valueOne /= valueTwo;
                break;
        }
    }

    protected void displayToast(int error) {
        Toast.makeText(BaseActivity.this,
                error, Toast.LENGTH_SHORT).show();
    }
}