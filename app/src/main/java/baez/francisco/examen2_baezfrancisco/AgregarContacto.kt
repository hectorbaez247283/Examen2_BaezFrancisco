package baez.francisco.examen2_baezfrancisco

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_contacto)

        val firstNameInput = findViewById<EditText>(R.id.firstName)
        val lastNameInput = findViewById<EditText>(R.id.lastName)
        val companyInput = findViewById<EditText>(R.id.company)
        val emailInput = findViewById<EditText>(R.id.email)
        val phoneInput = findViewById<EditText>(R.id.phone)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val firstName = firstNameInput.text.toString()
            val lastName = lastNameInput.text.toString()
            val company = companyInput.text.toString()
            val email = emailInput.text.toString()
            val phone = phoneInput.text.toString()

            // Validar que los campos no estén vacíos
            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || phone.isBlank()) {
                firstNameInput.error = "Requerido"
                lastNameInput.error = "Requerido"
                emailInput.error = "Requerido"
                phoneInput.error = "Requerido"
                return@setOnClickListener
            }

            // Crear el contacto
            val newContacto = Contacto(firstName, lastName, company, email, phone)

            // Guardar el contacto usando SharedPreferences
            saveContact(newContacto)

            // Regresar a la actividad principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun saveContact(contacto: Contacto) {
        val sharedPrefs = getSharedPreferences("ContactPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        // Guardar los datos del contacto
        editor.putString("firstName", contacto.firstName)
        editor.putString("lastName", contacto.lastName)
        editor.putString("company", contacto.company)
        editor.putString("email", contacto.email)
        editor.putString("phone", contacto.phone)
        editor.putInt("color", contacto.color)

        editor.apply()
    }
}