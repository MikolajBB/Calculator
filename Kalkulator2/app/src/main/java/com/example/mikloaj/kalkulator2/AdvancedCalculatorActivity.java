package com.example.mikloaj.kalkulator2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdvancedCalculatorActivity extends BaseActivity {


    protected static final char SIN = 's';
    protected static final char COS = 'c';
    protected static final char TAN = 't';
    protected static final char PERCENT = '%';
    protected static final char LN = 'n';
    protected static final char LOG = 'l';
    protected static final char SQUARED = '^';
    protected static final char POWER_TO_NUM = 'y';
    protected static final char SQRT = 'q';

    protected Button sin;
    protected Button cos;
    protected Button tan;
    protected Button percent;
    protected Button ln;
    protected Button log;
    protected Button squared;
    protected Button powerToNum;
    protected Button sqrt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = SIN;
                    compute(sin);
                }
            }
        });

        squared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = SQUARED;
                    compute(squared);
                }
            }
        });

        powerToNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = POWER_TO_NUM;
                    compute(powerToNum);
                }
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = COS;
                    compute(cos);
                }
            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = TAN;
                    compute(tan);
                }
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = LOG;
                    compute(log);
                }
            }
        });

        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = LN;
                    compute(ln);
                }
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = PERCENT;
                    compute(percent);
                }
            }
        });

        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = resultView.getText().toString();
                if (!content.isEmpty()) {
                    currentOperation = SQRT;
                    compute(sqrt);
                }
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.advanced_calculator_activity;
    }

    protected void initialize() {
        super.initialize();
        sin = findViewById(R.id.sin);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);
        percent = findViewById(R.id.percent);
        ln = findViewById(R.id.ln);
        log = findViewById(R.id.log);
        squared = findViewById(R.id.squared);
        powerToNum = findViewById(R.id.powerToNum);
        sqrt = findViewById(R.id.sqrt);
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
        super.chooseOperation();
        switch (currentOperation) {

            case SIN:
                valueOne = Math.sin(valueOne);
                break;

            case COS:
                valueOne = Math.cos(valueOne);
                break;

            case TAN:
                valueOne = Math.tan(valueOne);
                break;

            case LN:
                if(valueOne > 0)
                    valueOne = Math.log10(valueOne);
                else
                    displayToast(R.string.negative_number);
                break;

            case LOG:
                if(valueOne > 0)
                    valueOne = Math.log(valueOne);
                else
                    displayToast(R.string.negative_number);
                break;

            case SQUARED:
                valueOne = Math.pow(valueOne,2);
                break;

            case POWER_TO_NUM:
                valueOne = Math.pow(valueOne,valueTwo);
                break;

            case PERCENT:
                valueOne = valueOne / 100;
                break;

            case SQRT:
                if(valueOne > 0)
                    valueOne = Math.sqrt(valueOne);
                else
                    displayToast(R.string.negative_number);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
