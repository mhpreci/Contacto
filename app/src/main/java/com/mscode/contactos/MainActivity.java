package com.mscode.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    TextView mTextView;
    Button mBtnNext;
    TextInputEditText mTextInputEditTextName;
    TextView mTextViewBirth;
    TextInputEditText mTextInputEditTextPhone;
    TextInputEditText mTextInputEditTextEmail;
    TextInputEditText mTextInputEditTextDescription;

    String nameEdit;
    String phoneEdit;
    String emailEdit;
    String descriptionEdit;
    String BirthEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton =(Button) findViewById(R.id.btnBirth);
        mTextView = (TextView) findViewById(R.id.lblBirthText);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        final long today = MaterialDatePicker.todayInUtcMilliseconds();
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Fecha Nacimiento");
        builder.setSelection(today);
        final MaterialDatePicker materialDatePicker = builder.build();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                String inputFormat = "dd MMM yyyy";
                String outputFormat = "dd/MM/yyyy";

                String inputDate = materialDatePicker.getHeaderText();
                String outputDate = inputDate;
                try {
                    outputDate = new SimpleDateFormat(outputFormat, Locale.US).format(new SimpleDateFormat(inputFormat, Locale.US).parse(inputDate));
                } catch (Exception e) {
                    System.out.println("formateDateFromstring(): " + e.getMessage());
                    outputDate = "";
                }
                //mTextView.setText(outputDate);
                mTextView.setText(materialDatePicker.getHeaderText());

            }
        });

        mBtnNext = (Button) findViewById(R.id.Btnnext);
        mTextInputEditTextName =(TextInputEditText) findViewById(R.id.txtName);
        mTextInputEditTextPhone  =(TextInputEditText) findViewById(R.id.txtPhone);
        mTextInputEditTextEmail =(TextInputEditText) findViewById(R.id.txtEmail);
        mTextInputEditTextDescription=(TextInputEditText) findViewById(R.id.txtDescription);


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario contacto = new Usuario(mTextInputEditTextName.getText().toString(),
                        mTextView.getText().toString(),
                        mTextInputEditTextPhone.getText().toString(),
                        mTextInputEditTextEmail.getText().toString(),
                        mTextInputEditTextDescription.getText().toString()
                );

                Intent intent = new Intent (MainActivity.this, ConfirmActiviy.class);

                intent.putExtra("name",contacto.getNombre());
                intent.putExtra("birth", contacto.getFechaNacimiento());
                intent.putExtra("phone",contacto.getTelefono());
                intent.putExtra("Email",contacto.getEmail());
                intent.putExtra("description",contacto.getDescripcion());

                startActivity(intent);
            }
        });

        Bundle parametros = getIntent().getExtras();
        if (parametros!=null) {
            nameEdit = parametros.getString("nameEdit");
            phoneEdit = parametros.getString("phoneEdit");
            emailEdit = parametros.getString("emailEdit");
            descriptionEdit = parametros.getString("descriptionEdit");
            BirthEdit = parametros.getString("BirthEdit");

            mTextInputEditTextName=(TextInputEditText) findViewById(R.id.txtName);
            mTextInputEditTextPhone=(TextInputEditText) findViewById(R.id.txtPhone);
            mTextInputEditTextEmail=(TextInputEditText) findViewById(R.id.txtEmail);
            mTextInputEditTextDescription=(TextInputEditText) findViewById(R.id.txtDescription);
            mTextViewBirth=(TextView) findViewById(R.id.lblBirthText);

            mTextInputEditTextName.setText(nameEdit);
            mTextInputEditTextPhone.setText(phoneEdit);
            mTextInputEditTextEmail.setText(emailEdit);
            mTextInputEditTextDescription.setText(descriptionEdit);
            mTextViewBirth.setText(BirthEdit);
        }
    }
}