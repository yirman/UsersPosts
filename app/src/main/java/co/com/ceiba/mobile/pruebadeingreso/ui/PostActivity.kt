package co.com.ceiba.mobile.pruebadeingreso.ui

import android.os.Bundle
import android.widget.Toast
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.ui.adapters.PostAdapter
import co.com.ceiba.mobile.pruebadeingreso.utils.DialogUtils
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PostActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPostBinding
    private lateinit var adapter: PostAdapter
    private var loadingDialog: AlertDialog? = null

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver(intent.extras?.getInt(EXTRA_USER_ID, -1)!!)
    }

    private fun setupObserver(userId: Int){

        viewModel.postsUser.observe(this, {
            binding.name.text = it.name
            binding.phone.text = it.phone
            binding.email.text = it.email
        }).also { viewModel.queryPostsUser(userId) }

        viewModel.postList.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    loadingDialog?.dismiss()
                    adapter.setItems(it.data!!)
                }
                Resource.Status.ERROR -> {
                    loadingDialog?.dismiss()
                    Toast.makeText(this, "Error, intente más tarde", Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    loadingDialog = DialogUtils.showLoadingDialog( this, false)
                }

            }
        }).also { viewModel.getPosts(userId) }
    }

    private fun setupUI() {
        adapter = PostAdapter()
        binding.recyclerViewPostsResults.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPostsResults.adapter = adapter
    }

    companion object{
        const val EXTRA_USER_ID = "EXTRA_USER_ID"
    }
}