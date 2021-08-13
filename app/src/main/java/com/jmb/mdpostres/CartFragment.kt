package com.jmb.mdpostres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.jmb.mdpostres.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)

    }

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

        binding.btnPay.setOnClickListener {
            findNavController().navigate(R.id.action_cart_to_confirmation)
        }

        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_confirmation).isVisible = false
        menu.findItem(R.id.confirmationFragment).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private fun configAdapter() {
        adapter =
            arguments?.let { CartFragmentArgs.fromBundle(it).products }?.let { CartAdapter(it) }!!
        binding.rvCart.adapter = adapter
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())

        binding.tvSum.text = getString(R.string.cart_sum, adapter.itemCount.toFloat())
    }

}