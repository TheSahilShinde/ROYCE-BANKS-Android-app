package com.greetings.example.roycebanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Menu extends AppCompatActivity {

    String main_acc;
    String main_pass;

    DataBase db;

    private ImageButton ib;

    private Button btn_withdraw;
    private Button btn_deposit;
    private Button btn_view;
    private Button btn_transfer;
    private Button btn_change;
    private Button btn_profile;
    private Button btn_about;
    private Button btn_logout;

    private LinearLayout layout_withdraw;
    private LinearLayout layout_deposit;
    private LinearLayout layout_view;
    private LinearLayout layout_transfer;
    private LinearLayout layout_change;
    private LinearLayout layout_profile;
    private LinearLayout layout_about;
    private LinearLayout layout_logout;
    private EditText edt_w_amount;
    private EditText edt_w_password;
    private Button btn_w_refresh;
    private Button btn_w_submit;
    private EditText edt_d_amount;
    private Button btn_d_refresh;
    private Button btn_d_submit;
    private TextView txt_v_balance;
    private EditText edt_t_account;
    private EditText edt_t_amount;
    private EditText edt_t_password;
    private Button btn_t_refresh;
    private Button btn_t_submit;
    private EditText edt_c_old;
    private EditText edt_c_new;
    private EditText edt_c_confirm;
    private Button btn_c_refresh;
    private Button btn_c_submit;
    private TextView txt_p_name;
    private TextView txt_p_email;
    private TextView txt_p_account;
    private TextView txt_p_phone;
    private TextView txt_p_balance;
    private Button btn_l_back;
    private Button btn_l_yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        db = new DataBase(this);
        initcomp();
        get_info();

        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_withdraw.setVisibility(View.VISIBLE);
                layout_deposit.setVisibility(View.INVISIBLE);
                layout_view.setVisibility(View.INVISIBLE);
                layout_transfer.setVisibility(View.INVISIBLE);
                layout_change.setVisibility(View.INVISIBLE);
                layout_profile.setVisibility(View.INVISIBLE);
                layout_about.setVisibility(View.INVISIBLE);
                layout_logout.setVisibility(View.INVISIBLE);

            }
        });

        btn_deposit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout_withdraw.setVisibility(View.INVISIBLE);
                layout_deposit.setVisibility(View.VISIBLE);
                layout_view.setVisibility(View.INVISIBLE);
                layout_transfer.setVisibility(View.INVISIBLE);
                layout_change.setVisibility(View.INVISIBLE);
                layout_profile.setVisibility(View.INVISIBLE);
                layout_about.setVisibility(View.INVISIBLE);
                layout_logout.setVisibility(View.INVISIBLE);
//                Toast.makeText(Menu.this, main_acc+" "+main_pass, Toast.LENGTH_SHORT).show();


            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amt;
                layout_withdraw.setVisibility(View.INVISIBLE);
                layout_deposit.setVisibility(View.INVISIBLE);
                layout_view.setVisibility(View.VISIBLE);
                layout_transfer.setVisibility(View.INVISIBLE);
                layout_change.setVisibility(View.INVISIBLE);
                layout_profile.setVisibility(View.INVISIBLE);
                layout_about.setVisibility(View.INVISIBLE);
                layout_logout.setVisibility(View.INVISIBLE);

                Cursor cur = db.get_data();

                while (cur.moveToNext())
                {
                    String acc = cur.getString(3);
                    String psd = cur.getString(4);

                    if(acc.equals(main_acc) && psd.equals(main_pass))
                    {
                        amt=cur.getString(7);
                        txt_v_balance.setText(amt);
                        break;
                    }
                }

            }
        });

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_withdraw.setVisibility(View.INVISIBLE);
                layout_deposit.setVisibility(View.INVISIBLE);
                layout_view.setVisibility(View.INVISIBLE);
                layout_transfer.setVisibility(View.VISIBLE);
                layout_change.setVisibility(View.INVISIBLE);
                layout_profile.setVisibility(View.INVISIBLE);
                layout_about.setVisibility(View.INVISIBLE);
                layout_logout.setVisibility(View.INVISIBLE);

            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_withdraw.setVisibility(View.INVISIBLE);
                layout_deposit.setVisibility(View.INVISIBLE);
                layout_view.setVisibility(View.INVISIBLE);
                layout_transfer.setVisibility(View.INVISIBLE);
                layout_change.setVisibility(View.VISIBLE);
                layout_profile.setVisibility(View.INVISIBLE);
                layout_about.setVisibility(View.INVISIBLE);
                layout_logout.setVisibility(View.INVISIBLE);

            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_d,email_d,account_d,phone_d,balance_d;
                layout_withdraw.setVisibility(View.INVISIBLE);
                layout_deposit.setVisibility(View.INVISIBLE);
                layout_view.setVisibility(View.INVISIBLE);
                layout_transfer.setVisibility(View.INVISIBLE);
                layout_change.setVisibility(View.INVISIBLE);
                layout_profile.setVisibility(View.VISIBLE);
                layout_about.setVisibility(View.INVISIBLE);
                layout_logout.setVisibility(View.INVISIBLE);

                Cursor cur = db.get_data();

                while (cur.moveToNext())
                {
                    String acc = cur.getString(3);
                    String psd = cur.getString(4);

                    if(acc.equals(main_acc) && psd.equals(main_pass))
                    {
                        name_d=cur.getString(1);
                        email_d=cur.getString(2);
                        account_d=cur.getString(3);
                        phone_d=cur.getString(6);
                        balance_d=cur.getString(7);

                        txt_p_name.setText(name_d);
                        txt_p_email.setText(email_d);
                        txt_p_account.setText(account_d);
                        txt_p_phone.setText(phone_d);
                        txt_p_balance.setText(balance_d);
                        break;
                    }
                }

            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_withdraw.setVisibility(View.INVISIBLE);
                layout_deposit.setVisibility(View.INVISIBLE);
                layout_view.setVisibility(View.INVISIBLE);
                layout_transfer.setVisibility(View.INVISIBLE);
                layout_change.setVisibility(View.INVISIBLE);
                layout_profile.setVisibility(View.INVISIBLE);
                layout_about.setVisibility(View.VISIBLE);
                layout_logout.setVisibility(View.INVISIBLE);

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_withdraw.setVisibility(View.INVISIBLE);
                layout_deposit.setVisibility(View.INVISIBLE);
                layout_view.setVisibility(View.INVISIBLE);
                layout_transfer.setVisibility(View.INVISIBLE);
                layout_change.setVisibility(View.INVISIBLE);
                layout_profile.setVisibility(View.INVISIBLE);
                layout_about.setVisibility(View.INVISIBLE);
                layout_logout.setVisibility(View.VISIBLE);

            }
        });

        btn_l_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_w_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.get_data();

                String amt_i = edt_w_amount.getText().toString();
                String psd_i = edt_w_password.getText().toString();

                String balance_d;

                double amt_d = Double.parseDouble(amt_i);
                double balance_dd;

                while (cur.moveToNext())
                {
                    String acc = cur.getString(3);
                    String psd = cur.getString(4);
                    String id = cur.getString(0);

                    if(acc.equals(main_acc) && psd.equals(main_pass))
                    {
                        balance_d=cur.getString(7);
                        balance_dd = Double.parseDouble(balance_d);
                        try{
                            if(amt_d<0){
                                Toast.makeText(Menu.this, "Negative cannot be negative", Toast.LENGTH_LONG).show();
                            }
                            if(amt_d>balance_dd){
                                Toast.makeText(Menu.this, "Not enough Balance", Toast.LENGTH_LONG).show();
                            }
                            if(psd_i.equals(psd)==false)
                            {
                                Toast.makeText(Menu.this, "Wrong Password.", Toast.LENGTH_SHORT).show();
                            }
                            if(psd.equals(psd_i) && amt_d<=balance_dd){
                                String rem_bal;
                                balance_dd = (balance_dd - amt_d);
                                rem_bal = String.valueOf(balance_dd);
                                boolean b = db.update_withdraw(rem_bal,id);
                                if(b==true){
                                    Toast.makeText(Menu.this, "WITHDRAW SUCCESSFULL\n\nRemaining Balance = "+rem_bal, Toast.LENGTH_LONG).show();
                                    edt_w_amount.setText("");
                                    edt_w_password.setText("");
                                }
                                else{
                                    Toast.makeText(Menu.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }catch (Exception e)
                        {
                            Toast.makeText(Menu.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            }
        });

        btn_w_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_w_password.setText("");
                edt_w_amount.setText("");
            }
        });

        btn_d_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_d_amount.setText("");
            }
        });

        btn_d_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.get_data();

                String amt_i = edt_d_amount.getText().toString();

                String balance_d;

                double amt_d = Double.parseDouble(amt_i);
                double balance_dd;

                while (cur.moveToNext())
                {
                    String acc = cur.getString(3);
                    String psd = cur.getString(4);
                    String id = cur.getString(0);

                    if(acc.equals(main_acc) && psd.equals(main_pass))
                    {
                        balance_d=cur.getString(7);
                        balance_dd = Double.parseDouble(balance_d);
                        try{
                            if(amt_d<0){
                                Toast.makeText(Menu.this, "Negative amount.", Toast.LENGTH_LONG).show();
                            }
                            else{
                                String rem_bal;
                                balance_dd = (balance_dd + amt_d);
                                rem_bal = String.valueOf(balance_dd);
                                boolean b = db.update_deposit(rem_bal,id);
                                if(b==true){
                                    Toast.makeText(Menu.this, "DEPOSIT SUCCESSFULL\n\nRemaining Balance = "+rem_bal, Toast.LENGTH_LONG).show();
                                    edt_d_amount.setText("");
                                }
                                else{
                                    Toast.makeText(Menu.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }catch (Exception e)
                        {
                            Toast.makeText(Menu.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            }
        });

        btn_l_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_withdraw.setVisibility(View.VISIBLE);
                layout_deposit.setVisibility(View.INVISIBLE);
                layout_view.setVisibility(View.INVISIBLE);
                layout_transfer.setVisibility(View.INVISIBLE);
                layout_change.setVisibility(View.INVISIBLE);
                layout_profile.setVisibility(View.INVISIBLE);
                layout_about.setVisibility(View.INVISIBLE);
                layout_logout.setVisibility(View.INVISIBLE);
            }
        });

        btn_c_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_c_confirm.setText("");
                edt_c_new.setText("");
                edt_c_old.setText("");
            }
        });

        btn_c_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.get_data();

                String password_d;

                String old_i = edt_c_old.getText().toString();
                String new_i = edt_c_new.getText().toString();
                String confirm_i = edt_c_confirm.getText().toString();

                while (cur.moveToNext())
                {
                    String acc = cur.getString(3);
                    String psd = cur.getString(4);
                    String id = cur.getString(0);

                    if(acc.equals(main_acc) && psd.equals(main_pass))
                    {
                        password_d=cur.getString(4);
                        try{
                            if(old_i.equals(psd)==false){
                                Toast.makeText(Menu.this, "Old Password is wrong", Toast.LENGTH_SHORT).show();
                            }
                            if(new_i.equals(confirm_i)==false){
                                Toast.makeText(Menu.this, "Password not match.", Toast.LENGTH_SHORT).show();
                            }
                            if(old_i.equals("")||new_i.equals("")||confirm_i.equals(""))
                            {
                                Toast.makeText(Menu.this, "Enter all fields.", Toast.LENGTH_SHORT).show();
                            }
                            if(old_i.equals(password_d)&& new_i.equals(confirm_i)){

                                boolean b = db.update_password(new_i,id);
                                if(b==true){
                                    Toast.makeText(Menu.this, "PASSWORD CHANGED SUCESSFULLY", Toast.LENGTH_LONG).show();
                                    edt_c_confirm.setText("");
                                    edt_c_new.setText("");
                                    edt_c_old.setText("");
                                    Intent intent = new Intent(Menu.this,MainActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(Menu.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }catch (Exception e)
                        {
                            Toast.makeText(Menu.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }

            }
        });

        btn_t_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_t_account.setText("");
                edt_t_amount.setText("");
                edt_t_password.setText("");
            }
        });

        btn_t_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.get_data();

                String amt_i = edt_t_amount.getText().toString();
                String psd_i = edt_t_password.getText().toString();
                String to_i = edt_t_account.getText().toString();


                String balance_d;

                double amt_d = Double.parseDouble(amt_i);
                double balance_dd;

                while (cur.moveToNext())
                {
                    String acc = cur.getString(3);
                    String psd = cur.getString(4);
                    String id = cur.getString(0);

                    if(acc.equals(main_acc) && psd.equals(main_pass))
                    {
                        balance_d=cur.getString(7);
                        balance_dd = Double.parseDouble(balance_d);
                        try{
                            if(amt_d<0){
                                Toast.makeText(Menu.this, "Negative cannot be negative", Toast.LENGTH_LONG).show();
                            }
                            if(amt_d>balance_dd){
                                Toast.makeText(Menu.this, "Not enough Balance", Toast.LENGTH_LONG).show();
                            }
                            if(psd_i.equals(psd)==false)
                            {
                                Toast.makeText(Menu.this, "Wrong Password.", Toast.LENGTH_SHORT).show();
                            }
                            if(psd.equals(psd_i) && amt_d<=balance_dd){
                                String rem_bal;
                                balance_dd = (balance_dd - amt_d);
                                rem_bal = String.valueOf(balance_dd);
                                boolean b = db.transfer_remove(rem_bal,id);
                                if(b==true){
                                    Toast.makeText(Menu.this, "TRANSFER SUCCESSFULL\n\nRemaining Balance = "+rem_bal, Toast.LENGTH_LONG).show();

                                    Cursor c = db.get_data();

                                    while(c.moveToNext())
                                    {
                                        String acc_n = c.getString(3);
                                        if(to_i.equals(acc_n))
                                        {
                                            String idxx = c.getString(0);
                                            String balxx = c.getString(7);
                                            Double balx = Double.parseDouble(balxx);
                                            balx = (balx + amt_d);

                                            String balxxx=String.valueOf(balx);

                                            db.transfer_add(balxxx,idxx);

                                            edt_t_amount.setText("");
                                            edt_t_password.setText("");
                                            edt_t_account.setText("");

                                        }
                                    }


                                }
                                else{
                                    Toast.makeText(Menu.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }catch (Exception e)
                        {
                            Toast.makeText(Menu.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }

            }
        });



    }

    private void get_info() {
        main_acc = getIntent().getStringExtra("acc_p");
        main_pass = getIntent().getStringExtra("pass_p");
    }


    private void initcomp() {

        //initializing buttons

        btn_withdraw = findViewById(R.id.m_b_withdraw);
        btn_deposit = findViewById(R.id.m_b_deposit);
        btn_view = findViewById(R.id.m_b_view);
        btn_transfer = findViewById(R.id.m_b_transfer);
        btn_change = findViewById(R.id.m_b_change);
        btn_profile = findViewById(R.id.m_b_profile);
        btn_about = findViewById(R.id.m_b_about);
        btn_logout = findViewById(R.id.m_b_logout);

        // initializing layouts

        layout_withdraw = findViewById(R.id.m_l_withdraw);
        layout_deposit = findViewById(R.id.m_l_deposit);
        layout_view = findViewById(R.id.m_l_view);
        layout_transfer = findViewById(R.id.m_l_transfer);
        layout_change = findViewById(R.id.m_l_change);
        layout_profile = findViewById(R.id.m_l_profile);
        layout_about = findViewById(R.id.m_l_about);
        layout_logout = findViewById(R.id.m_l_logout);



        // initializing Withdraw views

        edt_w_amount = findViewById(R.id.w_e_amount);
        edt_w_password = findViewById(R.id.w_e_password);

        btn_w_refresh = findViewById(R.id.w_b_refresh);
        btn_w_submit = findViewById(R.id.w_b_submit);



        // initializing Deposit views

        edt_d_amount = findViewById(R.id.d_e_amount);

        btn_d_refresh = findViewById(R.id.d_b_refresh);
        btn_d_submit = findViewById(R.id.d_b_submit);



        // initializing View Balance views

        txt_v_balance = findViewById(R.id.v_t_balance);



        // initializing Transfer views

        edt_t_account = findViewById(R.id.t_e_account);
        edt_t_amount = findViewById(R.id.t_e_amount);
        edt_t_password = findViewById(R.id.t_e_password);

        btn_t_refresh = findViewById(R.id.t_b_refresh);
        btn_t_submit = findViewById(R.id.t_b_submit);


        // initializing Change Password views

        edt_c_old = findViewById(R.id.c_e_old);
        edt_c_new = findViewById(R.id.c_e_new);
        edt_c_confirm = findViewById(R.id.c_e_confirm);

        btn_c_refresh = findViewById(R.id.c_b_refresh);
        btn_c_submit = findViewById(R.id.c_b_submit);

        // initializing Profile views

        txt_p_name = findViewById(R.id.p_t_name);
        txt_p_email = findViewById(R.id.p_t_email);
        txt_p_account = findViewById(R.id.p_t_account);
        txt_p_phone = findViewById(R.id.p_t_phone);
        txt_p_balance = findViewById(R.id.p_t_balance);


        // initializing Logout views

        btn_l_back = findViewById(R.id.l_b_back);
        btn_l_yes = findViewById(R.id.l_b_yes);


    }



}