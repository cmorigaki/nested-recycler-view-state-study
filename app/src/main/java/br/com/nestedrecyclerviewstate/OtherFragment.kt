package br.com.nestedrecyclerviewstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.nestedrecyclerviewstate.databinding.OtherFragmentBinding

class OtherFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return OtherFragmentBinding.inflate(layoutInflater).root
    }

    companion object {
        fun newInstance() : Fragment {
            return OtherFragment()
        }
    }
}