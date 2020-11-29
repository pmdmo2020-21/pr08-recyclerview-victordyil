package es.iessaladillo.pedrojoya.pr06.data

import android.util.Log
import android.util.LogPrinter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import es.iessaladillo.pedrojoya.pr06.data.model.User

// TODO:
//  Crear una singleton Database que implemente la interfaz DataSource.
//  Al insertar un usuario, se le asignará un id autonumérico
//  (primer valor válido será el 1) que deberá ser gestionado por la Database.
//  La base de datos debe ser reactiva, de manera que cuando se agrege,
//  actualice o borre un usuario, los observadores de la lista deberán
//  recibir la nueva lista.
//  Al consultar los usuario se deberá retornar un LiveData con la lista
//  de usuarios ordenada por nombre

object Database : DataSource {
    val url = "https://picsum.photos/id/200/400/300"
    private val users: MutableList<User> = emptyList<User>().toMutableList()
    private val usersLiveData: MutableLiveData<List<User>> = MutableLiveData(users.toList())

    override fun getAllUsersOrderedByName(): LiveData<List<User>> {
        updateLiveData()
        return usersLiveData
    }

    override fun insertUser(user: User) {
        var id = users.size.toLong() + 1
        var u = user.copy(id = id)
        users.add(u)
        updateLiveData()
    }

    override fun updateUserOrInsert(user: User) {

        if (user.id != -1L) {
            var ind = users.indexOfFirst { it.id == user.id }
            if (ind != -1) {
                users[ind] = user
            } else {
                insertUser(user)
            }
        } else {
            insertUser(user)
        }
        updateLiveData()
    }

    override fun deleteUser(user: User) {
        val position = users.indexOfFirst { it.id == user.id }
        if (position >= 0) {
            users.removeAt(position)
            updateLiveData()
        }
    }

    private fun updateLiveData() {
        usersLiveData.value = users.sortedBy {
            it.nombre
        }
    }


}