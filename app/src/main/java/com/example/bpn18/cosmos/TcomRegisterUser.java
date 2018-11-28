package com.example.bpn18.cosmos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class TcomRegisterUser extends AppCompatActivity {

    EditText name,username,password,email,phone,address;
    RadioGroup gender;
    Button register,cancel;
    SharedPreferences preferences;
    AQuery aquery;
    String postUrl = "https://sastopustak.000webhostapp.com/Registration.php";
    //String postUrl= "http://192.168.0.102/HamroHostel/Registration.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        aquery = new AQuery(this);
        preferences = getSharedPreferences("userinfo",0);
        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.address);
        register=(Button)findViewById(R.id.rbutton);
        cancel=(Button)findViewById(R.id.rcancel);
        gender=(RadioGroup)findViewById(R.id.gender);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue=name.getText().toString();
                String usernameValue = username.getText().toString();
                String passwordValue=password.getText().toString();
                String addressValue=address.getText().toString();
                String emailValue=email.getText().toString();
                String phoneValue=phone.getText().toString();
                RadioButton checkedbtn=(RadioButton)findViewById(gender.getCheckedRadioButtonId());
                String genderValue=checkedbtn.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name",nameValue);
                editor.putString("username", usernameValue);
                editor.putString("password",passwordValue);
                editor.putString("address", addressValue);
                editor.putString("email", emailValue);
                editor.putString("phone", phoneValue);
                editor.putString("gender", genderValue);
                editor.apply();

                Map<String, Object> param = new HashMap<String, Object>();
                param.put("id","");
                param.put("name",nameValue);
                param.put("username", usernameValue);
                param.put("password",passwordValue);
                param.put("address", addressValue);
                param.put("email", emailValue);
                param.put("phone", phoneValue);
                param.put("gender", genderValue);




                aquery.ajax(postUrl, param, JSONArray.class, new AjaxCallback<JSONArray>() {
                    @Override
                    public void callback(String url, JSONArray array, AjaxStatus status) {
                        super.callback(url, array, status);

                        Log.i("response", url + "respone:" + array);

                    }
                });





                if (isFieldEmpty(name) && isFieldEmpty(username) && isFieldEmpty(password) && isValidEmail(emailValue) && isValidMobile(phoneValue)) {
                    Toast.makeText(TcomRegisterUser.this, "user inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TcomRegisterUser.this, loginActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(TcomRegisterUser.this, "Enter Valid Value", Toast.LENGTH_SHORT).show();
                }


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                username.setText("");
                password.setText("");
                email.setText("");
                address.setText("");
                phone.setText("");
            }
        });


    }
    public boolean isFieldEmpty(EditText view){
        String value = view.getText().toString();
        if(value.length()>0){
            return true;

        }else{
            view.setError("Enter value");
            return false;
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;

        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();

    }
}