package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private EditText topText, bottomText, infoText;
    private CheckBox topCheckbox, bottomCheckbox;
    private Button dispInfoBtn, navigateBtn;
    private ButtonListener buttonListener = new ButtonListener();
    private ActivityResultLauncher<Intent> activityResultLauncher;

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

            if(view.getId() == R.id.navigateBtn) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
                intent.putExtra("TOP_TEXT", topText.getText().toString());
                intent.putExtra("BOTTOM_TEXT", bottomText.getText().toString());

                activityResultLauncher.launch(intent);
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
        navigateBtn = findViewById(R.id.navigateBtn);

        dispInfoBtn.setOnClickListener(buttonListener);
        navigateBtn.setOnClickListener(buttonListener);


        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resCode = result.getResultCode();

                        if(resCode == RESULT_OK) {
                            Toast.makeText(PracticalTest01Var04MainActivity.this, "Result OK", Toast.LENGTH_LONG).show();
                        } else if (resCode == RESULT_CANCELED) {
                            Toast.makeText(PracticalTest01Var04MainActivity.this, "Result Cancel", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("TOP_TEXT", topText.getText().toString());
        outState.putString("BOTTOM_TEXT", bottomText.getText().toString());
        outState.putString("INFO_TEXT", infoText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("TOP_TEXT")) {
            String topTextRestore = savedInstanceState.getString("TOP_TEXT");
            topText.setText(topTextRestore);
        }
        if (savedInstanceState.containsKey("BOTTOM_TEXT")) {
            String bottomTextRestore = savedInstanceState.getString("BOTTOM_TEXT");
            bottomText.setText(bottomTextRestore);
        }
        if (savedInstanceState.containsKey("INFO_TEXT")) {
            String infoTextRestore = savedInstanceState.getString("INFO_TEXT");
            infoText.setText(infoTextRestore);
        }
    }
}