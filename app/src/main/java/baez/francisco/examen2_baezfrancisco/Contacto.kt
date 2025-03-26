package baez.francisco.examen2_baezfrancisco

import android.graphics.Color

data class Contacto(
    val firstName: String,
    val lastName: String,
    val company: String,
    val email: String,
    val phone: String,
    val color: Int = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA).random()
)
