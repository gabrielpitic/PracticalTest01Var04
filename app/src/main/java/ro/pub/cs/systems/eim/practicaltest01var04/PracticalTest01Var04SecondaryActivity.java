package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    private EditText topText, bottomText, infoText;
    private Button okBtn, cancelBtn;

    private ButtonListener buttonListener = new ButtonListener();

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.okBtn) {
                setResult(RESULT_OK);
            }

            if(view.getId() == R.id.cancelBtn) {
                setResult(RESULT_CANCELED);
            }

            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var04_secondary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        topText = findViewById(R.id.topText);
        bottomText = findViewById(R.id.bottomText);
        okBtn = findViewById(R.id.okBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        okBtn.setOnClickListener(buttonListener);
        cancelBtn.setOnClickListener(buttonListener);

        Intent intent = getIntent();
        topText.setText(intent.getStringExtra("TOP_TEXT"));
        bottomText.setText(intent.getStringExtra("BOTTOM_TEXT"));
    }
}