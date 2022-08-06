package com.example.commercial.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commercial.LogRegActivity;
import com.example.commercial.MainActivity;
import com.example.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment {
    public FirebaseAuth mAuth;
    Button getSignin  ;
    TextInputEditText enterMail , enterPass;
    TextView getSignup, forgotPassword , subtext , txt1 , regRePass;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login , container , false);
        enterMail =  view.findViewById(R.id.regMail);
        enterPass = view.findViewById(R.id.enterPass);
        regRePass = view.findViewById(R.id.regRePass);
        getSignin = view.findViewById(R.id.getSignin);
        getSignup = view.findViewById(R.id.getSignup);

        getSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputMail = enterMail.getText().toString();
                String inputPass = enterPass.getText().toString();

                if (TextUtils.isEmpty(inputMail) || TextUtils.isEmpty(inputPass)) {
                    Toast.makeText(getContext(), "Bütün xanaları doldurun", Toast.LENGTH_LONG).show();
                } else {
                    // Girish etmek uchun
                    mAuth.signInWithEmailAndPassword(inputMail, inputPass)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        DatabaseReference yol = FirebaseDatabase.getInstance().getReference().child("İstifadeciler").child(mAuth.getCurrentUser().getUid());
                                        yol.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    } else {
                                        Toast.makeText(getActivity(), "Giris ugursuz oldu", Toast.LENGTH_LONG).show();
                                    }

                                }

                            });

                }
            }

        });
        return view;
    }
}










