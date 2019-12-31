package com.example.flowtext;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void flowText(View view) {

        int count;
        EditText input = (EditText)findViewById(R.id.editText);
        TextView textview2 = (TextView)findViewById(R.id.textView2);
        EditText output = (EditText)findViewById(R.id.editText3);

        String text = input.getText().toString();
        String   header;
        String[] words = null;
        char[] chars= null;

        chars = text.toCharArray();

        int countchar = 0;
        double riverflow = 0;
        int width = 50;
        double max = 0;
        int pos = 0;
        int len = 0;

        ArrayList<flow_Text> flow = new ArrayList<flow_Text>();

        for(int i = 0; i < text.length(); i++){
            countchar++;
        }
        if (countchar < width) {
            riverflow = 1;
            flow_Text f = new flow_Text(countchar, width, riverflow);
            flow.add(f);
        } else {
            for(int j = width; j > 10; j--) {

                riverflow = countchar / j;
                riverflow = Math.floor(riverflow);
                flow_Text f = new flow_Text(countchar, width, riverflow);
                flow.add(f);
            }
        }
        for(int x = 0; x < flow.size(); x++){
             max = flow.get(0).flow_text;
            if (flow.get(x).flow_text > max) {
                max = flow.get(x).flow_text;
                pos = x;
            }
            len = flow.get(pos).width;
            System.out.println(max);
        }


        if (text == null || text.isEmpty()) {
            count = 0;
            header = "The number of words are " + count + " and River Flow is 0";
        }
        else{
            words = text.split("\\s+");
            count = words.length;
            header = "Then number of words are " + count + " and River Flow is " + max;
        }
        textview2.setText(header);
        output.setText(chars,0,len);
    }
}
