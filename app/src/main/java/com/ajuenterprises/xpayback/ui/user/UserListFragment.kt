package com.ajuenterprises.xpayback.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajuenterprises.xpayback.R
import com.ajuenterprises.xpayback.databinding.FragmentUserListBinding
import com.ajuenterprises.xpayback.model.User

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private val viewModel: UserListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var binding: FragmentUserListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserRecyclerView()
        observeViewModel()
        viewModel.fetchUsers()
        setUserRecyclerScrollListner()
    }

    private fun setUserRecyclerView() {
        recyclerView = binding.recyclerUser
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter(viewModel.users.value!!) { selectedItem ->
            navigateToDetailFragment(selectedItem)
        }
        recyclerView.adapter = adapter
    }

    private fun setUserRecyclerScrollListner() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading.value!! && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    viewModel.fetchUsers()
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.users.observe(viewLifecycleOwner) { users ->
            adapter!!.updateUserList(users)
        }

        viewModel.toastMsg.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

    }


    private fun navigateToDetailFragment(data: User) {
        val detailFragment = UserDetailFragment().apply {
            arguments = Bundle().apply {
                putInt("user_id", data.id)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}