package com.example.actividad3a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.databinding.FragmentFavouriteBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = true
        var bottom_navigation = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favouriteProductsFragment -> {
                    goToFragment(favouriteProductsFragment())
                    true
                }
                R.id.favouriteProfileFragment -> {
                    goToFragment(favouriteProfileFragment())
                    true
                }
                else -> false
            }
        }
        bottom_navigation.selectedItemId = R.id.favouriteProductsFragment
        //fin botoners
    }

    fun goToFragment(fragment: Fragment) {
        activity?.let{
            it.supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
        }
    }
}