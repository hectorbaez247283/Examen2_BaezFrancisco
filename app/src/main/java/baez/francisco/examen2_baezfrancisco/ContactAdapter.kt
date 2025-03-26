package baez.francisco.examen2_baezfrancisco

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val context: Context,
    private val contactList: MutableList<Contacto>,
    private val onDelete: (Contacto) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactIcon: ImageView = view.findViewById(R.id.contactIcon)
        val contactName: TextView = view.findViewById(R.id.contactName)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacto, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]

        holder.contactName.text = "${contact.firstName} ${contact.lastName}"
        holder.contactIcon.setColorFilter(contact.color)

        val goToDetail = View.OnClickListener {
            val intent = Intent(context, DetallesContacto::class.java)
            context.getSharedPreferences("ContactPrefs", Context.MODE_PRIVATE).edit().apply {
                putString("firstName", contact.firstName)
                putString("lastName", contact.lastName)
                putString("company", contact.company)
                putString("email", contact.email)
                putString("phone", contact.phone)
                putInt("color", contact.color)
                apply()
            }
            context.startActivity(intent)
        }

        holder.contactIcon.setOnClickListener(goToDetail)
        holder.contactName.setOnClickListener(goToDetail)

        holder.deleteButton.setOnClickListener {
            onDelete(contact)
        }
    }

    override fun getItemCount(): Int = contactList.size

    fun removeContact(contact: Contacto) {
        val position = contactList.indexOf(contact)
        if (position != -1) {
            contactList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
