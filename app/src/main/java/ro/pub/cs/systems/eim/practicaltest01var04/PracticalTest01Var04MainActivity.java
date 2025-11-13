package ro.pub.cs.systems.eim.practicaltest01var04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private EditText topText, bottomText, infoText;
    private CheckBox topCheckbox, bottomCheckbox;
    private Button dispInfoBtn;
    private ButtonListener buttonListener = new ButtonListener();

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.displayInfoBtn) {
                String info = "";

                if (topCheckbox.isChecked()) {
                    info += topText.getText().toString();
                    info += " ";
                }

                if (bottomCheckbox.isChecked()) {
                    info += bottomText.getText().toString();
                }

                infoText.setText(info);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var04_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        topText = findViewById(R.id.topText);
        bottomText = findViewById(R.id.bottomText);
        infoText = findViewById(R.id.infoText);

        topCheckbox = findViewById(R.id.topCheckbox);
        bottomCheckbox = findViewById(R.id.bottomCheckbox);

        dispInfoBtn = findViewById(R.id.displayInfoBtn);

        dispInfoBtn.setOnClickListener(buttonListener);
    }
}