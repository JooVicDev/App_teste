package com.example.rede_presidente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rede_presidente.databinding.ActivityRecuperaContaBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperaContaActivity extends AppCompatActivity {

    private ActivityRecuperaContaBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperaContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        iniciaToobar();

        mAuth = FirebaseAuth.getInstance();

        binding.btnRecuperaConta.setOnClickListener(v -> validaDados());
    }

    private void iniciaToobar() {
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }
    private void validaDados() {
        String email = binding.editEmail.getText().toString().trim();


        if (!email.isEmpty()) {

            binding.progressBar.setVisibility(View.VISIBLE);
            RecuperaContaFirebase(email);
        } else {
            Toast.makeText(this, "Informe seu e-mail.", Toast.LENGTH_SHORT).show();
        }
    }

    private void RecuperaContaFirebase(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                Toast.makeText(this, "já pode verificar seu email", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, MainActivity.class));

            } else {
                Toast.makeText(this, "Ocorreu um Erro.", Toast.LENGTH_SHORT).show();
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }
}