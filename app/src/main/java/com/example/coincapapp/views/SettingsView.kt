package com.example.coincapapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun Settings() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isAuth by remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))
        isAuth?.let {
            Text(if (it) "Autenticado correctamente" else "Error al iniciar sesión")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val auth = Firebase.auth

                if (email.isNotBlank() && password.isNotBlank()) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val usuario = auth.currentUser
                                isAuth = true
                                //log.d("Auth", "Sesión iniciada: ${usuario?.email}")
                            } else {
                                isAuth = false
                                //log("Auth", "Error al iniciar sesión", task.exception)
                            }
                        }
                } else {
                    //Log.e("Auth", "Email o contraseña vacíos")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
            }
        }
}