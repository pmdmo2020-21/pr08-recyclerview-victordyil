package es.iessaladillo.pedrojoya.pr06.ui.add_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.Database
import es.iessaladillo.pedrojoya.pr06.databinding.UserActivityBinding
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityBinding
import es.iessaladillo.pedrojoya.pr06.ui.users.*
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl
import kotlin.random.Random


class AddUserActivity : AppCompatActivity() {

    //Var
    private val binding: UserActivityBinding by lazy {
        UserActivityBinding.inflate(layoutInflater)
    }

    private val viewModel: AddUserViewModel by viewModels()
    // TODO: Código de la actividad.
    //  ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()

    }

    private fun setupViews() {
        binding.imageProfile?.loadUrl(viewModel.image)
        observarCambios()
    }

    private fun observarCambios() {
        binding.editTextTextPersonName?.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.nombre = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
        binding.editTextTextEmailAddress?.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.email = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
        binding.editTextPhone?.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.phone = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
        binding.editTextTextPostalAddress?.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.address = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
        binding.txtWeb?.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.web = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        }))
    }

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
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddUserActivity::class.java)
        }
    }

    private fun onSave() {
        // TODO: Acciones a realizar al querer salvar
        if (viewModel.nombre != null && viewModel.nombre != "" && viewModel.email != null && viewModel.email != "" && viewModel.phone != null && viewModel.phone != "") {
            var r = Intent().putExtra(NAME_EXTRA, viewModel.nombre)
                    .putExtra(EMAIL_EXTRA, viewModel.email)
                    .putExtra(PHONE_EXTRA, viewModel.phone.toString())
                    .putExtra(IMAGE_EXTRA, viewModel.image)
            if (viewModel.address != null) r.putExtra(ADDRESS_EXTRA, viewModel.address)
            if (viewModel.web != null) r.putExtra(WEB_EXTRA, viewModel.web)
            setResult(RESULT_OK, r)
            Toast.makeText(application, R.string.user_save, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(application, R.string.user_invalid_data, Toast.LENGTH_LONG).show()
        }
    }

}