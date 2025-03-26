package baez.francisco.examen2_baezfrancisco

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallesContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_contacto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPrefs = getSharedPreferences("ContactPrefs", MODE_PRIVATE)
        val firstName = sharedPrefs.getString("firstName", "")
        val lastName = sharedPrefs.getString("lastName", "")
        val company = sharedPrefs.getString("company", "")
        val email = sharedPrefs.getString("email", "")
        val phone = sharedPrefs.getString("phone", "")
        val color = sharedPrefs.getInt("color", Color.GRAY)

        val contactIcon = findViewById<ImageView>(R.id.imgContactIcon)
        val contactName = findViewById<TextView>(R.id.tvContactName)
        val contactCompany = findViewById<TextView>(R.id.tvCompany)
        val contactEmail = findViewById<TextView>(R.id.tvEmail)
        val contactPhone = findViewById<TextView>(R.id.tvPhone)
        val callButton = findViewById<Button>(R.id.btnCall)

        contactIcon.setColorFilter(color)
        contactName.text = "$firstName $lastName"
        contactCompany.text = company
        contactEmail.text = email
        contactPhone.text = phone
        callButton.text = "Llamar a"
        }
    }
