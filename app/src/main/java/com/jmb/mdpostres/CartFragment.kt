package com.jmb.mdpostres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmb.mdpostres.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configAdapter()
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_cart_to_products)
        }
    }

    private fun configAdapter() {
        binding.rvCart.adapter = arguments?.let {
            CartFragmentArgs.fromBundle(it).products?.let {
                CartAdapter(
                    it
                )
            }
        }
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
    }

}