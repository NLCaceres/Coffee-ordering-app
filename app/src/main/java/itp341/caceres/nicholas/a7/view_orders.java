package itp341.caceres.nicholas.a7;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class view_orders extends AppCompatActivity {

    public static final String EXTRA_COFFEE_ORDER = "itp.341.caceres.nicholas.a7.coffee_order";

    private TextView orderTextView;

    private Button confirmButton;
    private Button cancelButton;
    private confirmCancelListener cCListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        orderTextView = (TextView) findViewById(R.id.coffeeOrderTextView);

        cCListener = new confirmCancelListener();
        confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(cCListener);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(cCListener);

        Intent i = getIntent();
        String coffeeOrder = i.getStringExtra(EXTRA_COFFEE_ORDER);
        orderTextView.setText(coffeeOrder);

    }

    private class confirmCancelListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirmButton: {
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                case R.id.cancelButton: {
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
            }
        }
    }
}
