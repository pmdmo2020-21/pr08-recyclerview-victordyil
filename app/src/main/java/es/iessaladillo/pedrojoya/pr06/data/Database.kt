package es.iessaladillo.pedrojoya.pr06.data

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
    private val userList = mutableListOf<User>()
    private val _userLive: MutableLiveData<List<User>> = MutableLiveData(userList.toList())
    private val userLive: LiveData<List<User>>
        get() = _userLive

    override fun getAllUsersOrderedByName(): LiveData<List<User>> {
        return userLive
    }

    override fun insertUser(user: User) {
        userList.add(user)
    }

    override fun updateUserOrInsert(user: User) {
        var ind = userList.indexOfFirst { it.id.equals(user.id) }
        if (ind != -1) {
            userList[ind] = user
        } else {
            insertUser(user)
        }
    }

    override fun deleteUser(user: User) {
        userList.remove(user)
    }

}