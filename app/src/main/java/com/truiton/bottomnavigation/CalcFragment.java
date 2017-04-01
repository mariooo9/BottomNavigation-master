/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.truiton.bottomnavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class CalcFragment extends Fragment {
    public static CalcFragment newInstance() {
        CalcFragment fragment = new CalcFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public double Grade(String value){
        double val=0.00;
        if(value.equalsIgnoreCase("A+"))
            val= 10.00;
        else if(value.equalsIgnoreCase("A"))
            val =  9.00;
        else if(value.equalsIgnoreCase("B+"))
            val =  8.00;
        else if(value.equalsIgnoreCase("B"))
            val =  7.00;
        else if(value.equalsIgnoreCase("C"))
            val = 6.00;
        else if(value.equalsIgnoreCase("D"))
            val = 5.00;
        else if(value.equalsIgnoreCase("F"))
            val = 0.00;
        else
            val = -1.00;

        return val;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        getContext().getTheme().applyStyle(R.style.Light, true);

        RelativeLayout mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_calc, container, false);

        Button b,clr;
        final EditText theory_boxes[]= new EditText[6];
        final EditText lab_boxes[]=new EditText[6];
        final EditText result_output;
        final double theory_grades[] = new double[6];
        final double lab_grades[] = new double[6];
        final String[] sgpiString = new String[1];
        final double[] sgpi = new double[1];
        final int[] total = {0};
        final int[] theoryTotalSum = {0};
        final int[] labTotalSum = {0};

        b=(Button) mRelativeLayout.findViewById(R.id.button1);
        clr=(Button) mRelativeLayout.findViewById(R.id.button2);
        theory_boxes[0]=(EditText) mRelativeLayout.findViewById(R.id.editText7);
        theory_boxes[1]=(EditText) mRelativeLayout.findViewById(R.id.editText8);
        theory_boxes[2]=(EditText) mRelativeLayout.findViewById(R.id.editText9);
        theory_boxes[3]=(EditText) mRelativeLayout.findViewById(R.id.editText10);
        theory_boxes[4]=(EditText) mRelativeLayout.findViewById(R.id.editText11);
        theory_boxes[5]=(EditText) mRelativeLayout.findViewById(R.id.EditText01);

        lab_boxes[0]=(EditText) mRelativeLayout.findViewById(R.id.editText12);
        lab_boxes[1]=(EditText) mRelativeLayout.findViewById(R.id.editText13);
        lab_boxes[2]=(EditText) mRelativeLayout.findViewById(R.id.editText14);
        lab_boxes[3]=(EditText) mRelativeLayout.findViewById(R.id.editText15);
        lab_boxes[4]=(EditText) mRelativeLayout.findViewById(R.id.editText16);
        lab_boxes[5]=(EditText) mRelativeLayout.findViewById(R.id.EditText02);

        //project input

        result_output = (EditText) mRelativeLayout.findViewById(R.id.editText6);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for (int i=0;i<6;i++){
                    String temp;
                    temp = theory_boxes[i].getText().toString();
                    double gradeReturn = Grade(temp);
                    if (gradeReturn != -1) {
                        theory_grades[i] = gradeReturn;
                        total[0] += 3;
                        theoryTotalSum[0] += gradeReturn*3;
                    }
                    else {
                        theory_grades[i] = 0.00;
                        theory_boxes[i].setText("");
                    }
                }

                for (int i=0;i<6;i++) {
                    String temp;
                    temp = lab_boxes[i].getText().toString();
                    double gradeReturn = Grade(temp);
                    if (gradeReturn != -1) {
                        lab_grades[i] = gradeReturn;
                        total[0] += 2;
                        labTotalSum[0] += gradeReturn * 2;
                    } else {
                        lab_boxes[i].setText("");
                        lab_grades[i] = 0.00;
                    }
                }

                // project


                //


                sgpi[0] = (theoryTotalSum[0] + labTotalSum[0])/(double)(total[0]);
                if(sgpi[0] >10)
                {return;}
                sgpiString[0] =String.valueOf(sgpi[0]);
                result_output.setText(sgpiString[0]);
                total[0] =0;
                theoryTotalSum[0] =0;
                labTotalSum[0] =0;
            }
        });

        clr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                for (int i=0;i<6;i++) {
                    theory_boxes[i].setText("");
                    lab_boxes[i].setText("");
                }
                total[0] =0;
                sgpi[0] =0.00;
                result_output.setText("");
            }
        });


        return mRelativeLayout;
    }
}
