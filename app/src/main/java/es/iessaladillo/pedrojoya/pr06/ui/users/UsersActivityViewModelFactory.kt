package es.iessaladillo.pedrojoya.pr06.ui.users

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr06.data.Database

class UsersActivityViewModelFactory(val database: Database, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = UsersActivityViewModel(database, application) as T
}
