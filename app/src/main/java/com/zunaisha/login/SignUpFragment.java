package com.zunaisha.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {

    private EditText SignupEmail, SignupPassword , SignupConfirm;
    private Button SignupButton;
    private TextView loginRedirectText;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        // Initialize views
        SignupEmail = view.findViewById(R.id.signup_email);
        SignupPassword = view.findViewById(R.id.signup_password);
        SignupConfirm = view.findViewById(R.id.signup_confirm);
        SignupButton = view.findViewById(R.id.signup_button);
        loginRedirectText = view.findViewById(R.id.loginRedirectText);

        databaseHelper = new DatabaseHelper(requireContext());

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = SignupEmail.getText().toString();
                String password = SignupPassword.getText().toString();
                String confirmPassword = SignupConfirm.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(requireContext(), "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirmPassword)) {
                        boolean checkUserEmail = databaseHelper.checkEmail(email);
                        if (!checkUserEmail) {
                            boolean insert = databaseHelper.insertData(email, password);
                            if (insert) {
                                Toast.makeText(requireContext(), "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                // Navigate to LoginActivity or another fragment/activity
                                startActivity(new Intent(requireContext(), MainActivity.class));
                                requireActivity().finish(); // Finish SignUpActivity if necessary
                            } else {
                                Toast.makeText(requireContext(), "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(requireContext(), "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireContext(), "Password and Confirm Password do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Navigate to SignUpFragment
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
