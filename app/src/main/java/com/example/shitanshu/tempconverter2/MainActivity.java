package com.example.shitanshu.tempconverter2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.shitanshu.tempconverter2.R.id.seekbar;

public class MainActivity extends AppCompatActivity {
    Button button;
    SeekBar seekBar;  //declare seekbar object
    TextView textView;
    TextView textViewForFahren;

    //declare member variables for SeekBar
    int discrete=0;
    int start=50;
    int start_position=50; //progress tracker
    int temp=0;
    //declare objects for ViewStub
    ViewStub stub;
    CheckBox checkBox;
    //declare Listview object
    ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button handler
        button = (Button)findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        //declare viewstub object
        stub = (ViewStub) findViewById(R.id.viewStub1);
        @SuppressWarnings("unused")
        View inflated = stub.inflate();
        stub.setVisibility(View.INVISIBLE);

        //ViewStub logic

        checkBox=(CheckBox) findViewById(R.id.checkBox1);
        //handle checkbox click event
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked)
            {
                if ( isChecked )
                {
                    //remove objects from parent view to allow for child view
                    checkBox.setVisibility(View.GONE);
                    seekBar.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    stub.setVisibility(View.VISIBLE);

                }
            }
        });

        //seekbar logic
        textView = (TextView) findViewById(R.id.textView);
        textViewForFahren = (TextView)findViewById(R.id.textViewForFahrenheit);
        textView.setText("  Celsius at 0 degrees"); //set default view
        seekBar=(SeekBar) findViewById(seekbar);
        seekBar.setProgress(start_position);

        //create event handler for SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                if(temp==0) {
                    textViewForFahren.setText("Fahrenheit result 32 degrees");  //for initial view result
                    }
                else {
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean
                    fromUser) {

                // TODO Auto-generated method stub
                // To convert progress passed as discrete (Fahrenheit) value
                temp=progress-start;
                discrete=(int) Math.round((((temp * 9.0) / 5.0) + 32)); //convert C to F temp
                textViewForFahren.setText("Fahrenheit result " + String.valueOf(discrete) + " degrees");
                textView.setText("  Celsius at "+temp + " degrees");
            }
        });

        //Listview logic
        String[] wkTemps = new String[] { "Saturday: 47°F", "Sunday: 46°F", "Monday: 44°F", "Tuesday: 46°F", "Wednesday: 36°F"};

        lv=(ListView) findViewById(R.id.listView);
        @SuppressWarnings({ "unchecked", "rawtypes" })
   /*
   * To use a basic ArrayAdapter, you just need to initialize the adapter and
 * attach the adapter to the ListView. First, initialize the adapter...:
 *
 */
                ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, wkTemps);

        // Assign adapter to ListView
        lv.setAdapter(adapter);

    }//end onCreate method

    public void operation(View view) {
        //toggle appropriate views for controls, etc.
        stub.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        checkBox.setVisibility(View.VISIBLE);
        checkBox.setChecked(false);
        seekBar.setVisibility(View.VISIBLE);


    }
}
