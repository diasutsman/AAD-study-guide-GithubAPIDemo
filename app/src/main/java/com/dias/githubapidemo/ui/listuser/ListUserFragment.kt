package com.dias.githubapidemo.ui.listuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dias.githubapidemo.databinding.FragmentListUserBinding
import com.dias.githubapidemo.ui.UserAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListUserFragment : Fragment() {

    private var _binding: FragmentListUserBinding? = null
    private val binding get() = _binding as FragmentListUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[ListUserViewModel::class.java]
        val adapter = UserAdapter()
        binding.rvListUser.adapter = adapter
        lifecycleScope.launch {
            viewModel.listUser.collectLatest(adapter::submitData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}