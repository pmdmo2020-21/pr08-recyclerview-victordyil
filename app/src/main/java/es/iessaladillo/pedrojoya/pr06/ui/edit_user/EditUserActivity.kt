package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.databinding.UserActivityBinding
import es.iessaladillo.pedrojoya.pr06.ui.add_user.AddUserActivity
import es.iessaladillo.pedrojoya.pr06.ui.add_user.AddUserViewModel
import es.iessaladillo.pedrojoya.pr06.ui.users.*
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

            if (intent.getStringExtra(IMAGE_EXTRA) != null) {
                viewModel.image = intent.getStringExtra(IMAGE_EXTRA)!!
            }
            viewModel.name = if (intent.getStringExtra(NAME_EXTRA) != null) intent.getStringExtra(NAME_EXTRA) else null
            viewModel.email = if (intent.getStringExtra(EMAIL_EXTRA) != null) intent.getStringExtra(EMAIL_EXTRA) else null
            viewModel.phone = if (intent.getStringExtra(PHONE_EXTRA) != null) intent.getStringExtra(PHONE_EXTRA) else null
            viewModel.address = if (intent.getStringExtra(ADDRESS_EXTRA) != null) intent.getStringExtra(ADDRESS_EXTRA) else null
            viewModel.web = if (intent.getStringExtra(WEB_EXTRA) != null) intent.getStringExtra(WEB_EXTRA) else null

        }
        setupViews()
    }

    private fun setupViews() {
        binding.imageProfile.loadUrl(viewModel.image)
        binding.editTextTextPersonName!!.setText(viewModel.name)
        binding.editTextTextEmailAddress!!.setText(viewModel.email)
        binding.editTextPhone!!.setText(viewModel.phone)
        binding.editTextTextPostalAddress!!.setText(viewModel.address)
        binding.txtWeb!!.setText(viewModel.web)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, EditUserActivity::class.java)
        }
    }

    private fun observarCambios() {
        binding.editTextTextPersonName?.addTextChangedListener((object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.name = s.toString()
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

    private fun onSave() {
        if (viewModel.name != null && viewModel.name != "" && viewModel.email != null && viewModel.email != "" && viewModel.phone != null && viewModel.phone != "") {
            var r = Intent().putExtra(NAME_EXTRA, viewModel.name)
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