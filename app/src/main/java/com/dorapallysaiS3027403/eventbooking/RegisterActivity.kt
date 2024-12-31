package com.dorapallysaiS3027403.eventbooking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dorapallysaiS3027403.eventbooking.events.BaseActivity
import com.dorapallysaiS3027403.eventbooking.ui.theme.EventBookingTheme
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EventBookingTheme {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen() {
    var errorMessage by remember { mutableStateOf("") }

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val genderOptions = listOf("Male", "Female", "Other")

    var selectedGender by remember { mutableStateOf("Male") }

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Hey,\nRegister Now!",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Enter Full Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle, // Replace with desired icon
                    contentDescription = "Email Icon"
                )
            },
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "Select Gender", style = MaterialTheme.typography.bodyMedium)

        // Gender options as radio buttons

        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            genderOptions.forEach { gender ->
                RadioButton(
                    selected = (gender == selectedGender),
                    onClick = { selectedGender = gender }
                )
                Text(
                    text = gender,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
        }


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter E-Mail") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, // Replace with desired icon
                    contentDescription = "Email Icon"
                )
            },
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, // Replace with desired icon
                    contentDescription = "Password Icon"
                )
            },
        )


        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, // Replace with desired icon
                    contentDescription = "Password Icon"
                )
            },
        )

        Spacer(modifier = Modifier.height(36.dp))
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }


        Button(
            onClick = {
                when {
                    fullName.isBlank() -> {
                        errorMessage = "Please enter your full name."
                    }
                    isValidUsername(fullName) ->{
                        errorMessage = "Please enter a valid full name."
                    }
                    email.isBlank() -> {
                        errorMessage = "Please enter your email."
                    }
                    isValidEmail(email) -> {
                        errorMessage = "Please enter a valid email."
                    }
                    password.isBlank() -> {
                        errorMessage = "Please enter your password."
                    }
                    confirmPassword.isBlank() -> {
                        errorMessage = "Please confirm your password."
                    }
                    password != confirmPassword -> {
                        errorMessage = "Passwords do not match"

                    }
                    else -> {
                        errorMessage = ""
                        val userData = UserData(
                            fullName = fullName,
                            gender = selectedGender,
                            email = email,
                            password = password
                        )
                        doesUserExits(userData,context)
                    }
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 0.dp),
            shape = RoundedCornerShape(8.dp)
        )
        {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = "I have an account / ",
                style = MaterialTheme.typography.bodyLarge,
            )

            Text(
                text = "Login Now",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Black),
                modifier = Modifier.clickable {
                    // Intent to open RegisterActivity
                    context.startActivity(Intent(context, LoginActivity::class.java))
                    context.finish()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}

private fun doesUserExits(userData1: UserData, context: Activity) {
    val db = FirebaseDatabase.getInstance()
    val sanitizedUid = userData1.email.replace(".", ",")
    val ref = db.getReference("Users").child(sanitizedUid)

    ref.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val userData = task.result?.getValue(UserData::class.java)
            if (userData != null) {
                Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show()
            } else {
                saveUserData(userData1,context)
            }
        } else {
            // Data retrieval failed
            Toast.makeText(
                context,
                "Failed to retrieve user data: ${task.exception?.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

private fun saveUserData(userData: UserData, context: Activity) {
    val db = FirebaseDatabase.getInstance()
    val ref = db.getReference("Users")

    ref.child(userData.email.replace(".", ",")).setValue(userData)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(context, LoginActivity::class.java))
                context.finish()

            } else {
                Toast.makeText(
                    context,
                    "Account Registration Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener { exception ->
            Toast.makeText(
                context,
                "Account Registration Failed",
                Toast.LENGTH_SHORT
            ).show()
        }
}

fun isValidUsername(username: String): Boolean {
    val regex = "^[a-zA-Z]*$".toRegex() // Matches only alphabets, excluding spaces and other characters
    return regex.matches(username)
}


fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return !emailRegex.matches(email)
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}