import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.R;

public class FirstActivity extends AppCompatActivity {

    private Button myButton;
    private TextView mytextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);

        myButton = (Button) findViewById(R.id.button);
        mytextField = (TextView) findViewById(R.id.textfield);

        myButton.setOnClickListener(myButtonClick);
    }

        View.OnClickListener myButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytextField.setText(mytextField.getText()+"n" + "Next line");
            }
        };
    }
