package com.example.numbersgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.numbersgame.NumbersGamefunctions.NumbersGameFunctionsfile;

import java.util.Arrays;


public class fragmentOne extends Fragment {

    int lookingnumber;
    String text;Double dlookingnumber;
   Button buttonArm;
   Button Buttonprim;
   Button Buttonfibo;
   Button Buttonpalin;
   Button Buttonperf;
   Button ButtonFact;
   Button BtnCube;
   Button BtnSqr;
   Button BtnSqrt;
   Button BtnCbrt;
   EditText editText;

   TextView textView;


    @Override
    public View onCreateView(@org.jetbrains.annotations.NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }


    public void init(View view) {

        editText=view.findViewById(R.id.reciever);
        textView =view.findViewById(R.id.Discriptive_answer_txt);
        buttonArm=view.findViewById(R.id.buttonARM);
        Buttonprim=view.findViewById(R.id.buttonPrime);
        Buttonfibo=view.findViewById(R.id.buttonfibo);
        Buttonpalin=view.findViewById(R.id.buttonPali);
        Buttonperf=view.findViewById(R.id.buttonPerfect);
        ButtonFact=view.findViewById(R.id.buttonfacto);
        BtnSqr=view.findViewById(R.id.buttonSquare);
        BtnCube=view.findViewById(R.id.buttoncube);
        BtnSqrt=view.findViewById(R.id.buttonSquareRoot);
        BtnCbrt=view.findViewById(R.id.buttonCubeRoot);


        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                lookingnumber= 0;
            }
        });

        buttonArm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(check() && parseInt()) {

                    System.out.println("entered in armstrong");
                    NumbersGameFunctionsfile result = new NumbersGameFunctionsfile(lookingnumber);
                    int boo = NumbersGameFunctionsfile.arm();
                    if (boo == 1) {
                        textView.setText(lookingnumber + " is An Armstrong number");
                    } else {
                        textView.setText(lookingnumber + " is not An Armstrong number");
                    }
                }
            }
        });
        Buttonprim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check() && parseInt()) {
                NumbersGameFunctionsfile result = new NumbersGameFunctionsfile(lookingnumber);
                String boo = NumbersGameFunctionsfile.prime();
                textView.setText(boo);

            }
            }
        });

        Buttonfibo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(check() && parseInt()) {
                NumbersGameFunctionsfile result = new NumbersGameFunctionsfile(lookingnumber);
                int[] myArray = new int[lookingnumber];
                myArray = result.Fibonacci();
                textView.setText("The Fibonacci Series of " + lookingnumber + " :\n " + Arrays.toString(myArray));


            }
            }
        });

        Buttonpalin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check() && parseInt()) {
                NumbersGameFunctionsfile result=new NumbersGameFunctionsfile(lookingnumber);
                String res=result.Palindrome();
                textView.setText(res);}


            }
        });

        Buttonperf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check() && parseInt()) {
                NumbersGameFunctionsfile result=new NumbersGameFunctionsfile(lookingnumber);
                String res =result.Perfect();
                textView.setText(res);}


            }
        });

        ButtonFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check() && parsedouble()) {
                NumbersGameFunctionsfile result=new NumbersGameFunctionsfile(lookingnumber);
                String res=result.Factorial(dlookingnumber);
                textView.setText(res);}


            }
        });

        BtnCube.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(check() && parseInt()) {
                    int cube = lookingnumber * lookingnumber * lookingnumber;
                    textView.setText("The Cube of " + lookingnumber + " is : " + cube);
                }


            }
        });

        BtnSqr.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(check() && parseInt()) {
                    int sqr = lookingnumber * lookingnumber;
                    textView.setText("The Square of " + lookingnumber + " is : " + sqr);
                }


            }
        });

        BtnSqrt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                if(check() && parsedouble()){
                    Double sqrt=Math.sqrt(dlookingnumber);
                    textView.setText("The SquareRoot of " + dlookingnumber + " is : " + sqrt);

                }
            }
        });

        BtnCbrt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(check() && parsedouble()){
                    Double cbrt=Math.cbrt(dlookingnumber);
                    textView.setText("The SquareRoot of " + dlookingnumber + " is : " + cbrt);

                }

            }
        });
    }

    public boolean check(){
        boolean permision= true;
        text= editText.getText().toString();
        //editText.clearFocus();
        if(TextUtils.isEmpty(text)){
            Toast.makeText(getActivity(), "Please Provide Number", Toast.LENGTH_SHORT).show();
            permision=false;
        }
        System.out.println("if passed");
        return permision;
    }
    public boolean parseInt(){
        boolean permision=true;
        try {
            lookingnumber=Integer.parseInt(text);
        }catch (NumberFormatException e){
            Toast.makeText(getContext(), "Enter an appropriate Number", Toast.LENGTH_SHORT).show();
            permision=false;
        }
    return permision;
    }

    public boolean parsedouble(){
        boolean permision=true;
        try {
            dlookingnumber=Double.parseDouble(text);
        }catch (NumberFormatException e){
            Toast.makeText(getContext(), "Enter an appropriate Number", Toast.LENGTH_SHORT).show();
            permision=false;
        }
        return permision;
    }
}