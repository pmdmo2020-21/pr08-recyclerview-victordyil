package es.iessaladillo.pedrojoya.pr06.ui.add_user

import android.app.Application
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User
import kotlin.random.Random

// TODO:
//  Crear la clase EditUserViewModel. Ten en cuenta que la url de la photo
//  deberá ser preservada por si la actividad es destruida por falta de recursos.

class AddUserViewModel() : ViewModel() {
    private val random: Random = Random(System.currentTimeMillis())
    lateinit var user:User
    var nombre: String? = null
    var email: String? = null
    var phone: String? = null
    var address: String? = null
    var web: String? = null
    val image: String = getRandomPhotoUrl()

    private fun getRandomPhotoUrl(): String =
            "https://picsum.photos/id/${random.nextInt(100)}/400/300"


}
