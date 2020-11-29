package es.iessaladillo.pedrojoya.pr06.ui.users

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User

// TODO:
//  Crear clase UsersActivityViewModel
class UsersActivityViewModel(private val database: DataSource, private val application: Application) : ViewModel() {

    var users: LiveData<List<User>> = database.getAllUsersOrderedByName()

    val lblEmptyViewVisibility: LiveData<Int> = users.map { if (it.isEmpty()) View.VISIBLE else View.INVISIBLE }

    fun deleteUser(user: User) = database.deleteUser(user)

    fun editUser(user: User) = database.updateUserOrInsert(user)

    fun createUser(user: User) = database.updateUserOrInsert(user)


}