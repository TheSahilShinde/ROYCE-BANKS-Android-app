package com.greetings.example.roycebanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Register extends AppCompatActivity {

    DataBase db;

    private EditText edt_name;
    private EditText edt_email;
    private EditText edt_account;
    private EditText edt_password;
    private EditText edt_confirm;
    private EditText edt_phone;
    private EditText edt_balance;

    private Button btn_back;
    private Button btn_submit;



    EditText name_e ,email_e,account_e,password_e,confirm_e,phone_e,balance_e;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        db = new DataBase(this);


        init_reg_views();
        RandomAcc();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
//                db.addUser("Sahil Shinde","sahilsshinde5@gmail.com","1001","sss","sss","9665502816","99999999");
//                Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_LONG).show();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_s = null;
                String email_s= null;
                String account_s= null;
                String password_s= null;
                String confirm_s= null;
                String phone_s= null;
                String balance_s= null;

                name_s = edt_name.getText().toString();
                email_s = edt_email.getText().toString();
                account_s = edt_account.getText().toString();
                password_s = edt_password.getText().toString();
                confirm_s = edt_confirm.getText().toString();
                phone_s = edt_phone.getText().toString();
                balance_s = edt_balance.getText().toString();

                if(name_s.equals("") || email_s.equals("") || account_s.equals("") || password_s.equals("") || confirm_s.equals("") ||  phone_s.equals("") || balance_s.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter all fields.",Toast.LENGTH_LONG).show();
                }
                else if(password_s.equals(confirm_s))
                {
                    db.addUser(name_s,email_s,account_s,password_s,confirm_s,phone_s,balance_s);
                    Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Register.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Password not matched",Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    private void init_reg_views() {

        edt_name = findViewById(R.id.r_e_name);
        edt_email = findViewById(R.id.r_e_email);
        edt_account = findViewById(R.id.r_e_account);
        edt_password = findViewById(R.id.r_e_password);
        edt_confirm = findViewById(R.id.r_e_confirm);
        edt_phone = findViewById(R.id.r_e_phone);
        edt_balance = findViewById(R.id.r_e_balance);

        btn_back = findViewById(R.id.r_b_back);
        btn_submit = findViewById(R.id.r_b_submit);

        edt_account.setEnabled(false);
        edt_account.setTextColor(Color.BLACK);
    }

    public void RandomAcc(){
        Random ra=new Random();

        edt_account.setText(""+ra.nextInt(100000+1));
    }
}