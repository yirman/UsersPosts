package co.com.ceiba.mobile.pruebadeingreso.ui


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.ui.adapters.UserAdapter
import co.com.ceiba.mobile.pruebadeingreso.utils.DialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserAdapter.OnUserClickListener, TextWatcher {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private var loadingDialog: AlertDialog? = null

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupObserver(){
        viewModel.users.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    loadingDialog?.dismiss()
                    adapter.setItems(it.data!!)
                    when {
                        !it.data.isNullOrEmpty() -> {
                            binding.tvEmptyList.visibility = View.GONE
                        }
                        else -> {
                            binding.tvEmptyList.visibility = View.VISIBLE
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog?.dismiss()
                    Toast.makeText(this, "Error, intente más tarde", Toast.LENGTH_SHORT).show()
                }


                Resource.Status.LOADING -> {
                    loadingDialog = DialogUtils.showLoadingDialog( this, false)
                }

            }
        })
    }

    private fun setupUI() {
        adapter = UserAdapter(this)
        binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSearchResults.adapter = adapter
        binding.editTextSearch.addTextChangedListener(this)
    }

    override fun onClickUser(id: Int) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra(PostActivity.EXTRA_USER_ID, id)
        startActivity(intent)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

    override fun afterTextChanged(s: Editable?) {
        viewModel.searchUsers(s.toString())
    }
}