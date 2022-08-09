package com.example.commercial.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterFragment extends Fragment {
    EditText regName, regMail, regPass, regRePass;
    TextInputEditText regUsername;
    TextView txt_account;
    Button bRegister;
    ProgressDialog pd;
    private FirebaseAuth mAuth;
    DatabaseReference yol;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mAuth = FirebaseAuth.getInstance();
        regUsername = view.findViewById(R.id.regName);
        regMail = view.findViewById(R.id.regMail);
        regPass = view.findViewById(R.id.regPass);
        regRePass = view.findViewById(R.id.regPass);
        bRegister = view.findViewById(R.id.regSignUp);
        mAuth = FirebaseAuth.getInstance();
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
        return view;
    }

    private void registerNewUser() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Xahiş olunur gözləyin");
        pd.show();
        // Register penceresinde edit textlere yazilan melumati bazaya gondermek ucun asagidakilari qeyd edirem
        String str_username = regUsername.getText().toString();
        String str_name = regName.getText().toString();
        String str_mail = regMail.getText().toString();
        String str_pass = regPass.getText().toString();
        String str_repass = regRePass.getText().toString();

        if (TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_mail) || TextUtils.isEmpty(str_pass) || TextUtils.isEmpty(str_repass)) {
            Toast.makeText(getActivity(), "Bütün sətirləri doldurun", Toast.LENGTH_SHORT).show();
        } else if (str_pass.length() < 6) {
            Toast.makeText(getActivity(), "Şifrə 6 simvoldan böyük olmalıdır.", Toast.LENGTH_SHORT).show();
        } else if (!str_pass.equals(str_repass)) {
            Toast.makeText(getActivity(), "Şifrə eyni olmalıdır.", Toast.LENGTH_SHORT);
        } else {
            // yeni user qeyd etme kodlari burda çağrılacaq
            save(str_username, str_name, str_mail, str_pass);

        }
    }
    private void save (String username , String name , String mail , String pass) {
        // yeni user qeyd etme kodlari burda yazilacaq
        mAuth.createUserWithEmailAndPassword(mail , pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userID = firebaseUser.getUid();
                            yol = FirebaseDatabase.getInstance().getReference().child("İstifadeciler").child(userID);
                            // Birden cox data gondermek uchun HashMap istifade etmeliyik
                            HashMap<String , Object> hashMap = new HashMap<>();
                            hashMap.put("id" , userID);
                            hashMap.put("username" , username.toLowerCase());
                            hashMap.put("name" , name);
                            hashMap.put("mail" , mail);
                            hashMap.put("pass" , pass);
                            hashMap.put("bio" , "");
                            hashMap.put("photourl" ,
                   "https://firebasestorage.googleapis.com/v0/b/android-c01c1.appspot.com/o/placeholder.jpg?alt=media&token=5131ce0a-75ec-4644-8928-d6b2aff04509");

                            yol.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                   public void onComplete(@NonNull Task<Void> task) {
                      if (task.isSuccessful()) {
                    pd.dismiss();
                   /*  Intent intent = new Intent(RegisterFragment.class , LoginFragment.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent);*/
                          }
                                 }
                               }
                            );
                        }
                        else {
                            pd.dismiss();
                            Toast.makeText(getContext(), "Bu mail və ya şifrə ilə qeydiyyat mümkün olmadı.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
