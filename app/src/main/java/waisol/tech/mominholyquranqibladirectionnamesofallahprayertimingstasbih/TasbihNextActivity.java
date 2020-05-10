package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TasbihNextActivity extends AppCompatActivity {
    Toolbar toolbar;
    private ImageView decrease, increase, reset;

    private TextView counterTxt;
    private int counter;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.decrease :
                    decreaseCounter();
                    break;

                case R.id.increase :
                    increaseCounter();
                    break;

                case R.id.reset :
                    initCounter();
                    break;
            }

            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbih_next);

        toolbar = findViewById(R.id.tasbihToolbar);
        toolbar.setVisibility(View.VISIBLE);

        decrease = findViewById(R.id.decrease);
        decrease.setOnClickListener(clickListener);
        increase = findViewById(R.id.increase);
        increase.setOnClickListener(clickListener);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(clickListener);
        counterTxt = findViewById(R.id.counterTxt);

        initCounter();

    }
    private void initCounter(){
        counter = 0;
        counterTxt.setText(counter + "");
    }

    private void decreaseCounter(){
        counter --;
        counterTxt.setText(counter + "");
    }

    private void increaseCounter(){
        counter ++;
        counterTxt.setText(counter + "");
    }

    public void back(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
