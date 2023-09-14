package com.example.rede_presidente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rede_presidente.databinding.ActivityCadastroBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.btnCriarConta.setOnClickListener(v -> validaDados());
    }

    private void validaDados() {
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editSenha.getText().toString().trim();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {

                criarContaFirebase(email, senha);

            } else {
                Toast.makeText(this, "Informe sua Senha.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Informe seu e-mail.", Toast.LENGTH_SHORT).show();
        }
    }

    private void criarContaFirebase(String email, String Senha) {
        mAuth.createUserWithEmailAndPassword(
                email, Senha
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                startActivity(new Intent(this, MainActivity.class));

            } else {
                Toast.makeText(this, "Ocorreu um Erro.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}