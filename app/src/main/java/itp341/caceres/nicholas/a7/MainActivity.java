package itp341.caceres.nicholas.a7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import itp341.caceres.nicholas.a7.Model.Coffee_Order;

public class MainActivity extends AppCompatActivity {

    private EditText customerNameEditText;
    private String customerName;

    private RadioGroup sizeRG;
    private String coffeeSize;

    private Spinner brewSpinner;
    private String[] brews;
    private String brew;
    private Switch sugarSwitch;
    private Boolean sugar;
    private CheckBox milkCheckBox;
    private Boolean milk;
    private EditText specialInstructionsET;
    private String specialInstruction;

    private Button loadFavoriteButton;
    private Button saveFavoriteButton;
    private Button orderButton;
    private Button clearButton;
    private buttonListener coffeeListener;

    private Coffee_Order userOrder;
    private Toast orderToast;

    static final String FAVORITE_COFFEE_ORDER = "itp341.caceres.nicholas.a7.favorite_coffee_order";
    static final String PREFERENCE_NAME = "itp341.caceres.nicholas.a7.user_name";
    static final String PREFERENCE_SIZE = "itp341.caceres.nicholas.a7.coffee_size";
    static final String PREFERENCE_BREW = "itp341.caceres.nicholas.a7.brew";
    static final String PREFERENCE_SUGAR = "itp341.caceres.nicholas.a7.sugar";
    static final String PREFERENCE_MILK = "itp341.caceres.nicholas.a7.milk";
    static final String PREFERENCE_SPECIAL_INSTRUCTIONS = "itp341.caceres.nicholas.a7.special_instructions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerNameEditText = (EditText) findViewById(R.id.customerNameEditText);
        customerNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                customerName = customerNameEditText.getText().toString();
                userOrder.setCustomerName(customerName);
                return true;
            }
        });

        sizeRG = (RadioGroup) findViewById(R.id.sizeRadioGroup);
        sizeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.smallRadioButton) {
                    coffeeSize = getResources().getString(R.string.small_Radio_Button);
                    userOrder.setSize(coffeeSize);
                }
                else if (checkedId == R.id.mediumRadioButton) {
                    coffeeSize = getResources().getString(R.string.medium_Radio_Button);
                    userOrder.setSize(coffeeSize);
                }
                else {
                    coffeeSize = getResources().getString(R.string.large_Radio_Button);
                    userOrder.setSize(coffeeSize);
                }
            }
        });

        brewSpinner = (Spinner) findViewById(R.id.brewSpinner);
        brews = getResources().getStringArray(R.array.brew_Array);
        brewSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brew = brews[position];
                userOrder.setBrew(brew);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sugar = false;
        sugarSwitch = (Switch) findViewById(R.id.sugarSwitch);
        sugarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sugar = isChecked;
                userOrder.setSugar(sugar);
            }
        });
        milk = false;
        milkCheckBox = (CheckBox) findViewById(R.id.milkCheckBox);
        milkCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                milk = isChecked;
                userOrder.setMilk(milk);
            }
        });

        specialInstruction = "";
        specialInstructionsET = (EditText) findViewById(R.id.specialInstructionsEditText);
        specialInstructionsET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                specialInstruction = specialInstructionsET.getText().toString();
                userOrder.setSpecialInstructions(specialInstruction);
                return true;
            }
        });

        coffeeListener = new buttonListener();
        loadFavoriteButton = (Button) findViewById(R.id.loadFavoriteButton);
        loadFavoriteButton.setOnClickListener(coffeeListener);
        saveFavoriteButton = (Button) findViewById(R.id.saveFavoriteButton);
        saveFavoriteButton.setOnClickListener(coffeeListener);
        orderButton = (Button) findViewById(R.id.orderButton);
        orderButton.setOnClickListener(coffeeListener);
        clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(coffeeListener);

        userOrder = new Coffee_Order();
    }

    private class buttonListener implements View.OnClickListener {
        @Override
        public void onClick (View v) {
            switch (v.getId()) {
                case R.id.loadFavoriteButton: {
                    SharedPreferences pref = getSharedPreferences(FAVORITE_COFFEE_ORDER, MODE_PRIVATE);

                    customerName = pref.getString(PREFERENCE_NAME, "");
                    customerNameEditText.setText(customerName);

                    coffeeSize = pref.getString(PREFERENCE_SIZE, "Small");
                    if (coffeeSize == "Small") {
                        sizeRG.check(R.id.smallRadioButton);
                    }
                    else if (coffeeSize == "Medium") {
                        sizeRG.check(R.id.mediumRadioButton);
                    }
                    else {
                        sizeRG.check(R.id.largeRadioButton);
                    }

                    brew = pref.getString(PREFERENCE_BREW, "Kona");
                    if (brew == brews[0]) {
                        brewSpinner.setSelection(0);
                    }
                    else if (brew == brews[1]) {
                        brewSpinner.setSelection(1);
                    }
                    else if (brew == brews[2]) {
                        brewSpinner.setSelection(2);
                    }
                    else if (brew == brews[3]) {
                        brewSpinner.setSelection(3);
                    }
                    else {
                        brewSpinner.setSelection(4);
                    }

                    sugar = pref.getBoolean(PREFERENCE_SUGAR, false);
                    if (sugar) {
                        sugarSwitch.setChecked(true);
                    }
                    else {
                        sugarSwitch.setChecked(false);
                    }

                    milk = pref.getBoolean(PREFERENCE_MILK, false);
                    if (milk) {
                        milkCheckBox.setChecked(true);
                    }
                    else {
                        milkCheckBox.setChecked(false);
                    }

                    specialInstruction = pref.getString(PREFERENCE_SPECIAL_INSTRUCTIONS, "");
                    specialInstructionsET.setText(specialInstruction);

                    orderToast.makeText(MainActivity.this, R.string.favorite_loaded, orderToast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.saveFavoriteButton: {
                    SharedPreferences pref = getSharedPreferences(FAVORITE_COFFEE_ORDER, MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putString(PREFERENCE_NAME, customerName);
                    prefEditor.putString(PREFERENCE_SIZE, coffeeSize);
                    prefEditor.putString(PREFERENCE_BREW, brew);
                    prefEditor.putBoolean(PREFERENCE_SUGAR, sugar);
                    prefEditor.putBoolean(PREFERENCE_MILK, milk);
                    prefEditor.putString(PREFERENCE_SPECIAL_INSTRUCTIONS, specialInstruction);
                    prefEditor.commit();
                    orderToast.makeText(MainActivity.this, R.string.favorite_saved, orderToast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.orderButton: {
                    String orderPassed = userOrder.toString();

                    Intent i = new Intent(getApplicationContext(), view_orders.class);
                    i.putExtra(view_orders.EXTRA_COFFEE_ORDER, orderPassed);
                    startActivityForResult(i, 0);
                    break;
                }
                case R.id.clearButton: {
                    customerNameEditText.setText("");
                    sizeRG.clearCheck();
                    brewSpinner.setSelection(0);
                    sugarSwitch.setChecked(false);
                    milkCheckBox.setChecked(false);
                    specialInstructionsET.setText("");
                    break;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                customerNameEditText.setText("");
                sizeRG.clearCheck();
                brewSpinner.setSelection(0);
                sugarSwitch.setChecked(false);
                milkCheckBox.setChecked(false);
                specialInstructionsET.setText("");

                orderToast.makeText(MainActivity.this, R.string.confirm_toast, orderToast.LENGTH_SHORT).show();
            }
            else {

                if (userOrder.getSize() == "Small") {
                    sizeRG.check(R.id.smallRadioButton);
                }
                else if (userOrder.getSize() == "Medium") {
                    sizeRG.check(R.id.mediumRadioButton);
                }
                else {
                    sizeRG.check(R.id.largeRadioButton);
                }


                for (int i = 0; i > brews.length; i++) {
                    if (userOrder.getBrew() == brews[i]) {
                        brewSpinner.setSelection(i);
                        break;
                    }
                }

                customerNameEditText.setText(userOrder.getCustomerName());
                sugarSwitch.setChecked(userOrder.getSugar());
                milkCheckBox.setChecked(userOrder.getMilk());
                specialInstructionsET.setText(userOrder.getSpecialInstructions());

                orderToast.makeText(MainActivity.this, R.string.cancel_toast, orderToast.LENGTH_SHORT).show();
            }
        }
    }
}
