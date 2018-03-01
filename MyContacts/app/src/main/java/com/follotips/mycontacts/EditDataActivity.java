package com.follotips.mycontacts;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class EditDataActivity extends AppCompatActivity {

    //private static final String TAG = "EditDataActivity";


    private Button btnSave,btnDelete, btnAddContact;
    private EditText edit_contact_name, edit_contact_surname,edit_contact_phone_number,edit_contact_birthday;

    DBHelper mdbHelper;
    private int selectedID;
    private String selectedName , selectedSurname, selectedPhoneNumber, selectedBirthdayDate;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);




        btnSave = findViewById(R.id.saveConnBtn);
        btnDelete = findViewById(R.id.deleteConBtn);
        btnAddContact = findViewById(R.id.createConnBtn);
     //   btnAddContact.setVisibility(View.GONE);

        edit_contact_name = findViewById(R.id.editConName);
        edit_contact_surname = findViewById(R.id.editConSurname);
        edit_contact_phone_number = findViewById(R.id.editConNumber);
        edit_contact_birthday = findViewById(R.id.editConBirthday);
        mdbHelper = new DBHelper(this);

        //
        Intent receivedIntent = getIntent();

        //
        selectedID = receivedIntent.getIntExtra("id", -1);

        //
        selectedName = receivedIntent.getStringExtra("name");
        selectedSurname = receivedIntent.getStringExtra("surname");
        selectedPhoneNumber = receivedIntent.getStringExtra("phoneNumber");
        selectedBirthdayDate = receivedIntent.getStringExtra("birthday");

        //
        edit_contact_name.setText(selectedName);
        edit_contact_surname.setText(selectedSurname);
        edit_contact_phone_number.setText(selectedPhoneNumber);
        edit_contact_birthday.setText(selectedBirthdayDate);

        //
        String contactName = edit_contact_name.getText().toString();
        String contactSurname = edit_contact_surname.getText().toString();
        String contactPhoneNumber = edit_contact_phone_number.getText().toString();
        String contactBirthday = edit_contact_birthday.getText().toString();

//        final DataConstructor dataConstructor = new DataConstructor(selectedID ,contactName,
//                contactSurname,
//                contactPhoneNumber,
//                contactBirthday);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edit_contact_name.getText().toString();
                String surname = edit_contact_surname.getText().toString();
                String phoneNumber = edit_contact_phone_number.getText().toString();
                String birthday = edit_contact_birthday.getText().toString();

//                mdbHelper.UpdateDataParam(dataConstructor.getContactID(), dataConstructor.getContactName(), dataConstructor.getContactSurname(), dataConstructor.getContactPhoneNumber(),
//                        dataConstructor.getContactBirthday());

                mdbHelper.UpdateDataParam(selectedID,name,surname,phoneNumber,birthday);

                toastMessage("Successful");

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdbHelper.deleteData(selectedID);

                toastMessage("removed from database");
                Intent intent = new Intent(EditDataActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String name = edit_contact_name.getText().toString();
                String surname = edit_contact_surname.getText().toString();
                String phoneNumber = edit_contact_phone_number.getText().toString();
                String birthday = edit_contact_birthday.getText().toString();

                mdbHelper.addData(name,surname,phoneNumber,birthday);


//                mdbHelper.addData( dataConstructor.getContactName(), dataConstructor.getContactSurname(), dataConstructor.getContactPhoneNumber(),
//                        dataConstructor.getContactBirthday());

                Intent intent = new Intent(EditDataActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


//Toastmessage

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

