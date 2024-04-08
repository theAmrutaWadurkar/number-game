package com.example.numbersgame;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class CalciFragment extends Fragment {


    String Working="";
    String formula = "";
    String tempFormula = "";

    String input="",inputx ="",output="",newOutput="";

    TextView inputText;
    TextView outputText;
    Button ClearBtn;
    Button SingleClearBtn;
    Button BracketBtn;
    Button PowerOfBtn;
    Button DivisionBtn;

    Button SevenBtn;
    Button EightBtn;
    Button NineBtn;
    Button MultiplicationBtn;

    Button FourBtn;
    Button FiveBtn;
    Button SixBtn;
    Button SubtractionBtn;

    Button OneBtn;
    Button TwoBtn;
    Button ThreeBtn;
    Button AdditionBtn;

    Button PointBtn;
    Button ZeroBtn;
    Button EqualToBtn;
    boolean leftBracket=true;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calci, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        onbtnclick(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        /*
        * this OnActivityCreated Method is called to hide SoftKeyboard inside Calci Fragment
        * */
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager IMM=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        IMM.hideSoftInputFromWindow(getView().getWindowToken(),0);
    }

    private void init(View view) {
        /*
        init() method is being used to connect XML components with java initialised instances of components
         */
        inputText=view.findViewById(R.id.WorkingTxtVw);
        outputText=view.findViewById(R.id.ResultTxtVw);

        ClearBtn=view.findViewById(R.id.ClearBtn);
        BracketBtn=view.findViewById(R.id.BracketBtn);
        PowerOfBtn=view.findViewById(R.id.PowerOfBtn);
        DivisionBtn=view.findViewById(R.id.DivisionBtn);
        SingleClearBtn=view.findViewById(R.id.SingleClearBtn);

        SevenBtn=view.findViewById(R.id.SevenBtn);
        EightBtn=view.findViewById(R.id.EightBtn);
        NineBtn=view.findViewById(R.id.NineBtn);
        MultiplicationBtn=view.findViewById(R.id.MultiplicationBtn);

        FourBtn=view.findViewById(R.id.FourBtn);
        FiveBtn=view.findViewById(R.id.FiveBtn);
        SixBtn=view.findViewById(R.id.SixBtn);
        SubtractionBtn=view.findViewById(R.id.SubtractionBtn);

        OneBtn=view.findViewById(R.id.OneBtn);
        TwoBtn=view.findViewById(R.id.TwoBtn);
        ThreeBtn=view.findViewById(R.id.ThreeBtn);
        AdditionBtn=view.findViewById(R.id.AdditionBtn);

        PointBtn=view.findViewById(R.id.PointBtn);
        ZeroBtn=view.findViewById(R.id.ZeroBtn);
        EqualToBtn=view.findViewById(R.id.EqualToBtn);


    }
    public void onNewButtonClicked(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();

        switch (data) {
            case "C":
                if (input.length() != 0) {
                    input = input.substring(0, input.length() - 1);
                    inputx = inputx.substring(0, inputx.length() - 1);
                }
                break;
            case "AC":
                input = "";
                inputx = "";
                output = "";
                newOutput = "";
                outputText.setText("");
                break;

            case "^":
                if (input.length() == 0) {
                    Toast.makeText(getActivity(), "Provide Some Number before Power Operation", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(input.charAt(input.length()-1)=='.'){
                    input+=0;
                }
                if (!checkRepeat('^'))
                    break;
                solve();
                input += "^";
                inputx += "^";
                break;
            case "*":
                if (input.length() == 0) {
                    Toast.makeText(getActivity(), "Provide Some Number before Product Operation", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(input.charAt(input.length()-1)=='.'){
                    input+=0;
                }
                if (!checkRepeat('*'))
                    break;
                solve();
                input += "*";
                inputx += "*";
                break;

            case "=":
                solve();
                break;

            case "%":
                if (input.length() == 0) {
                    Toast.makeText(getActivity(), "Provide Some Number before Modulus Operation", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(input.charAt(input.length()-1)=='.'){
                    input+=0;
                }
                if (!checkRepeat('%')){
                    break;}
                solve();
                input += '%';
                inputx += '%';
                double d = Double.parseDouble(inputText.getText().toString()) / 100;
                outputText.setText(String.valueOf(d));
                break;
            case "/":
                if (input.length() == 0) {
                    Toast.makeText(getActivity(), "Provide Some Number before Division Operation", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(input.charAt(input.length()-1)=='.'){
                    input+=0;
                    inputx +=0;
                }
                if (!checkRepeat('/'))
                    break;
                solve();
                input += data;
                inputx += data;
                break;
            case "+":
                if (!checkRepeat('+'))
                    break;
                if(input.charAt(input.length()-1)=='.'){
                    input+=0;
                }
                solve();
                input+=data;
                inputx +=data;
                break;

            case "-":
                if (!checkRepeat('-'))
                    break;
                if(input.charAt(input.length()-1)=='.'){
                    input+=0;
                }
                solve();
                input+=data;
                inputx +=data;
                break;
            case ".":
                if (!checkRepeat('.'))
                    break;

                if(Fraction())
                    break;
                input+=data;
                inputx+=data;
                break;
            default:
               /* if (input == null) {
                    input = "";
                }*/
                input += data;
                inputx += data;
                }
         inputText.setText(inputx);
        }

    private boolean Fraction() {
        int length=input.length();
        if(input.charAt(length-1)=='+'
                || input.charAt(length-1)=='-'
                || input.charAt(length-1)=='*'
                || input.charAt(length-1)=='/'
                || input.charAt(length-1)=='^'
                || input.charAt(length-1)=='%')
        {
            Toast.makeText(getActivity(), "Un-appropriate Entry ", Toast.LENGTH_SHORT).show();
            return true;
        }
        int count=0;
        int posi=0;
        for (int i = input.length()-1; i > 0; i--)
        {
            if (input.charAt(i) == '+'
                    || input.charAt(i) == '-'
                    || input.charAt(i) == '*'
                    || input.charAt(i) == '/')
            {
                posi=i;
                System.out.println("position: "+posi);
                break;
            }
            System.out.println("count: "+count);
        }

        for (int i = input.length()-1; i > posi ; i--)
        {
            if (input.charAt(i) == '.')
            {
                count++;
            }
            System.out.println("count: "+count);
        }
        if(count!=0)
            return true;
        return  false;
    }


    private boolean checkRepeat(char Oprator)
    {

        int len=input.length();
        if(len==0)
        {   Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(input.charAt(len-1)==Oprator)
        { Toast.makeText(getActivity(), "Can Not Repeat Operator "+ input.charAt(len-1), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void solve()
    {
        if (input.split("\\+").length == 2)
        { String numbers[] = input.split("\\+");
            try
            { double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d + "";
            } catch (Exception e)
            { outputText.setText("");
            }
        }

        if (input.split("\\*").length == 2) {
            String numbers[] = input.split("\\*");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d + "";
            } catch (Exception e) {
                outputText.setText("");
            }
        }
        if (input.split("\\/").length == 2) {
            String numbers[] = input.split("\\/");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d + "";
            } catch (Exception e) {
                outputText.setText("");
            }
        }
        if (input.split("\\^").length == 2) {
            String numbers[] = input.split("\\^");
            try {
                double d = Math.pow(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d + "";
            } catch (Exception e) {
                outputText.setText("");
            }
        }
        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])) {
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText("-" + newOutput);
                    input = d + "";
                } else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = d + "";
                }
            } catch (Exception e) {
                outputText.setText("");
            }
        }
    }
    private String cutDecimal(String number) {
        String n [] = number.split("\\.");
        if (n.length >1){
            if (n[1].equals("0")){
                number = n[0];
            }
        }
        return number;
    }

   /* private void setWorking(String GivenValue){
        Working = Working + GivenValue;
        WorkingTxt.setText(Working);
    }*/
    private void onbtnclick(View view) {
        ClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*WorkingTxt.setText("");
                Working = "";
                ResultTxt.setText("");
                leftBracket = true;
                */
                onNewButtonClicked(view);

            }
        });
        BracketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onNewButtonClicked(view);
            }
        });
        /*BracketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(leftBracket){
                    setWorking("(");
                    leftBracket = false;
                }
                else {
                    setWorking(")");
                    leftBracket = true ;
                }

            }
        });*/
        SingleClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewButtonClicked(view);
            }
        });
        PowerOfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* setWorking("%");
                */
                onNewButtonClicked(view);
            }
        });
        DivisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("/"); */
                onNewButtonClicked(view);
            }
        });
       // ---------------------------------------------------------------------------(1)------------------------------------------------
        SevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("7"); */
                onNewButtonClicked(view);
            }
        });
        EightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("8");
                 */
                onNewButtonClicked(view);

            }
        });
        NineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("9");
                 */
                onNewButtonClicked(view);
            }
        });
        MultiplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("*");
                 */
                onNewButtonClicked(view);
            }
        });
        //---------------------------------------------------------------------------(2)------------------------------------------------

        FourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("4");
                 */
                onNewButtonClicked(view);
            }
        });
        FiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("5");
                 */
                onNewButtonClicked(view);
            }
        });
        SixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("6");
                 */
                onNewButtonClicked(view);
            }
        });
        SubtractionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("-");
                 */
                onNewButtonClicked(view);
            }
        });
        //*---------------------------------------------------------------------------(3)------------------------------------------------

        OneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("1");
                 */
                onNewButtonClicked(view);
            }
        });
        TwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("2");
                 */
                onNewButtonClicked(view);
            }
        });
        ThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("3");
                 */
                onNewButtonClicked(view);
            }
        });
        AdditionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("+");
                 */
                onNewButtonClicked(view);
            }
        });

        //*---------------------------------------------------------------------------(4)------------------------------------------------*
        PointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking(".");
                 */
                onNewButtonClicked(view);
            }
        });
        ZeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setWorking("0");
                 */
                onNewButtonClicked(view);
            }
        });
        EqualToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*double result = 0.0d;
                System.out.println(result);
                ScriptEngine engine=new ScriptEngineManager().getEngineByName("rhino");
                clickForPowerOf();
                try {
                    result=(Double) engine.eval(formula);
                    System.out.println(result);

                } catch (ScriptException e) {
                    Toast.makeText(getActivity(),"Invalid Input",Toast.LENGTH_SHORT).show();
                }
                catch (NullPointerException e){
                    Toast.makeText(getActivity(),"Result is null",Toast.LENGTH_SHORT).show();
                    System.out.println(result);


                }

                if(result!=0.0d){
                    ResultTxt.setText(String.valueOf((Double) result));
                }else {
                    Toast.makeText(getActivity(),"result is null",Toast.LENGTH_SHORT).show();
                } */
                onNewButtonClicked(view);
            }
        });

        //*---------------------------------------------------------------------------(5)------------------------------------------------*

    }

   /* private void clickForPowerOf()
    {
        ArrayList<Integer> IndexOfPowers= new ArrayList<>();
        for (int i =0;i< Working.length();i++)
        {
            if (Working.charAt(i) == '^' )
            {
                IndexOfPowers.add(i);
            }

        }
        formula = Working;
        tempFormula = Working;
        for (Integer index:IndexOfPowers)
        {
            changeFormula(index);
        }
        tempFormula=formula;
    }

    private void changeFormula(Integer index) {
        String numberLeft="";
        String numberRight="";

        for(int i=index+1;i<Working.length();i++)
        {
            if(isNumeric(Working.charAt(i)))
                numberRight=numberRight+Working.charAt(i);
            else
                break;
        }

        for(int i=index-1;i>0 ;i--)
        {
            if(isNumeric(Working.charAt(i)))
                numberLeft=numberLeft+Working.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula=tempFormula.replace(original,changed);

    }
    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;
        return false;
    }*/
}