package baez.francisco.examen2_baezfrancisco

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private val contactList = mutableListOf<Contacto>()

    private val defaultContacts = listOf(
        Contacto("Juan", "Pérez", "Tech Solutions", "juan@tech.com", "555-123-4567"),
        Contacto("María", "Gómez", "Innovatech", "maria@innova.com", "555-987-6543"),
        Contacto("Carlos", "López", "Digital Systems", "carlos@digital.com", "555-456-7890"),
        Contacto("Ana", "Martínez", "SoftCorp", "ana@softcorp.com", "555-789-0123"),
        Contacto("Pedro", "Rodríguez", "WebMasters", "pedro@webmasters.com", "555-234-5678")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val addButton: Button = findViewById(R.id.addButton)

        // Configurar el RecyclerView
        contactAdapter = ContactAdapter(this, contactList) { contact ->
            removeContact(contact)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contactAdapter

        // Cargar contactos guardados o usar predeterminados
        val savedContact = loadContact()
        if (savedContact != null) {
            contactList.add(savedContact)
        } else {
            // Solo agregar contactos predeterminados si no hay contactos guardados
            if (contactList.isEmpty()) {
                contactList.addAll(defaultContacts)
            }
        }
        contactAdapter.notifyDataSetChanged()

        // Ir a la actividad para agregar contactos
        addButton.setOnClickListener {
            val intent = Intent(this, AgregarContacto::class.java)
            startActivity(intent)
        }
    }

    private fun removeContact(contact: Contacto) {
        contactAdapter.removeContact(contact)
        clearStoredContact(contact)
    }

    private fun loadContact(): Contacto? {
        val sharedPrefs = getSharedPreferences("ContactPrefs", Context.MODE_PRIVATE)

        val firstName = sharedPrefs.getString("firstName", null) ?: return null
        val lastName = sharedPrefs.getString("lastName", null) ?: return null
        val company = sharedPrefs.getString("company", null) ?: return null
        val email = sharedPrefs.getString("email", null) ?: return null
        val phone = sharedPrefs.getString("phone", null) ?: return null
        val color = sharedPrefs.getInt("color", listOf(Color.RED, Color.BLUE, Color.GREEN).random())

        return Contacto(firstName, lastName, company, email, phone, color)
    }

    private fun clearStoredContact(contact: Contacto) {
        val sharedPrefs = getSharedPreferences("ContactPrefs", Context.MODE_PRIVATE).edit()
        sharedPrefs.clear()
        sharedPrefs.apply()
    }
}
