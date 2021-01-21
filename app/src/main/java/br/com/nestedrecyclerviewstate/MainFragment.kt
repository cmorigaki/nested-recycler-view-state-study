package br.com.nestedrecyclerviewstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.nestedrecyclerviewstate.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    private val contentAdapter by lazy { ContentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MainFragmentBinding.inflate(layoutInflater).apply {
            initComponents(this)
            observeState()
        }.root
    }

    private fun initComponents(binding: MainFragmentBinding) {
        binding.content.adapter = contentAdapter
        binding.openFragment.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.container, OtherFragment.newInstance())
                addToBackStack(OtherFragment::class.java.name)
            }.commit()
        }
    }

    private fun observeState() {
        viewModel.content.observe(viewLifecycleOwner) {
            contentAdapter.submitList(it)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}