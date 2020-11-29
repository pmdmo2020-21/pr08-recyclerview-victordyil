package es.iessaladillo.pedrojoya.pr06.ui.users

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.Database
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityBinding
import es.iessaladillo.pedrojoya.pr06.ui.add_user.AddUserActivity
import es.iessaladillo.pedrojoya.pr06.ui.edit_user.EditUserActivity

const val NAME_EXTRA = "NAME_EXTRA"
const val IMAGE_EXTRA = "IMAGE_EXTRA"
const val EMAIL_EXTRA = "EMAIL_EXTRA"
const val PHONE_EXTRA = "PHONE_EXTRA"
const val ADDRESS_EXTRA = "ADDRESS_EXTRA"
const val WEB_EXTRA = "WEB_EXTRA"
const val USER_EXTRA ="USER_EXTRA"

class UsersActivity : AppCompatActivity() {
    //Callers
    private val addUser: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultIntent = result.data
                if (result.resultCode == RESULT_OK && resultIntent != null) {
                    var name = resultIntent.getStringExtra(NAME_EXTRA)
                    var img = resultIntent.getStringExtra(IMAGE_EXTRA)
                    var email = resultIntent.getStringExtra(EMAIL_EXTRA)
                    var phone = resultIntent.getStringExtra(PHONE_EXTRA)
                    var address = resultIntent.getStringExtra(ADDRESS_EXTRA)
                    var web = resultIntent.getStringExtra(WEB_EXTRA)

                    viewModel.createUser(name, img, email, phone, address, web)
                }

            }
    private val editUser: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultIntent = result.data
                if (result.resultCode == RESULT_OK && resultIntent != null) {
                    var name = resultIntent.getStringExtra(NAME_EXTRA)
                    var img = resultIntent.getStringExtra(IMAGE_EXTRA)
                    var email = resultIntent.getStringExtra(EMAIL_EXTRA)
                    var phone = resultIntent.getStringExtra(PHONE_EXTRA)
                    var address = resultIntent.getStringExtra(ADDRESS_EXTRA)
                    var web = resultIntent.getStringExtra(WEB_EXTRA)
                    viewModel.editUser(viewModel.editingUser.copy(nombre = name!!, email = email!!, phoneNumber = phone!!, address = address
                            ?: "", web = web ?: "", photoUrl = img!!))
                }
            }

    //Variables
    private val binding: UsersActivityBinding by lazy {
        UsersActivityBinding.inflate(layoutInflater)
    }

    private val viewModel: UsersActivityViewModel by viewModels {
        UsersActivityViewModelFactory(Database, application)
    }
    private val listAdapter: UsersActivityAdapter = UsersActivityAdapter().also {
        it.onEditListener = { position ->
            editUser(position)
        }
        it.onDeleteListener = { position ->
            deleteUser(position)
        }
    }

    // TODO: Código de la actividad.
    //    //  Ten en cuenta que el recyclerview se muestra en forma de grid,
    //  donde el número de columnas está gestionado
    //  por R.integer.users_grid_columns
    //  ...

    // NO TOCAR: Estos métodos gestionan el menú y su gestión

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.users, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuAdd) {
            onAddUser()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun onAddUser() = addUser.launch(AddUserActivity.newIntent(this))


    private fun deleteUser(position: Int) {
        val user: User = listAdapter.currentList[position]
        viewModel.deleteUser(user)
    }

    private fun editUser(position: Int) {
        val user: User = listAdapter.currentList[position]
        viewModel.editingUser = user
        var r = EditUserActivity.newIntent(this).putExtra(USER_EXTRA,user)
        editUser.launch(r)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    private fun observeViewModelData() {
        viewModel.users.observe(this) {
            updateList(it)
        }
        viewModel.lblEmptyViewVisibility.observe(this) {
            binding.emptyUsersImageView.visibility = it
            binding.emptyUserText.visibility = it
        }
    }

    private fun updateList(list: List<User>) = listAdapter.submitList(list)

    private fun setupViews() {
        setupRecyclerView()
        observeViewModelData()
    }

    private fun setupRecyclerView() = binding.lstUsers.run {
        adapter = listAdapter
        itemAnimator = DefaultItemAnimator()
        layoutManager = LinearLayoutManager(context)
        //layoutManager = GridLayoutManager(context, R.integer.users_grid_columns, RecyclerView.HORIZONTAL, false)
    }
}
