package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.databinding.UserActivityBinding
import es.iessaladillo.pedrojoya.pr06.ui.users.USER_EXTRA
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl

class EditUserActivity : AppCompatActivity() {
    // TODO: Código de la actividad.
    //  Ten en cuenta que la actividad debe recibir a través del intent
    //  con el que es llamado el objeto User corresondiente
    //  ...

    // NO TOCAR: Estos métodos gestionan el menú y su gestión


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.user, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSave) {
            onSave()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // FIN NO TOCAR

    private val binding: UserActivityBinding by lazy {
        UserActivityBinding.inflate(layoutInflater)
    }
    private val viewModel: EditUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getIntentData()
        observarCambios()

    }

    private fun getIntentData() {

        if (intent != null) {
            viewModel.user = intent.getParcelableExtra(USER_EXTRA)!!
        }
        setupViews()
    }

    private fun setupViews() {
        binding.imageProfile.loadUrl(viewModel.user.photoUrl)
        binding.editTextTextPersonName.setText(viewModel.user.nombre)
        binding.editTextTextEmailAddress.setText(viewModel.user.email)
        binding.editTextPhone.setText(viewModel.user.phoneNumber)
        binding.editTextTextPostalAddress.setText(viewModel.user.address)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, EditUserActivity::class.java)
        }
    }

    private fun observarCambios() {
        binding.editTextTextPersonName.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.user.nombre = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
        binding.editTextTextEmailAddress.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.user.email = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
        binding.editTextPhone.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.user.phoneNumber = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
        binding.editTextTextPostalAddress.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.user.address = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
        binding.txtWeb.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.user.web = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
    }

    private fun onSave() {
        if (viewModel.user.nombre != "" && viewModel.user.email != "" && viewModel.user.phoneNumber != "") {
            setResult(RESULT_OK, Intent().putExtra(USER_EXTRA, viewModel.user))
            Toast.makeText(application, R.string.user_save, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(application, R.string.user_invalid_data, Toast.LENGTH_LONG).show()
        }

    }

}