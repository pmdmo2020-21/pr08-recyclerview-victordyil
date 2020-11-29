package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.model.User
import java.sql.Timestamp
import java.util.*
import java.util.concurrent.TimeUnit

// TODO:
//  Crear la clase EditUserViewModel. Ten en cuenta que la url de la photo
//  deberá ser preservada por si la actividad es destruida por falta de recursos.

class EditUserViewModel: ViewModel() {

    var name: String? = null
    var email: String? = null
    var phone: String? = null
    var address: String? = null
    var web: String? = null
    private val random: Random = Random()
    var image:String = getRandomPhotoUrl()
    // Para obtener un URL de foto de forma aleatoria (tendrás que definir
    // e inicializar el random a nivel de clase.
    private fun getRandomPhotoUrl(): String =
            "https://picsum.photos/id/${random.nextInt(100)}/400/300"

}
