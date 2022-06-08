package ru.ozon.route256.workshop1.presentation.view

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.di.ServiceLocator
import ru.ozon.route256.workshop1.presentation.view.adapter.Adapter
import ru.ozon.route256.workshop1.presentation.viewModel.ProductsViewModel
import ru.ozon.route256.workshop1.sources.viewModelCreator


class ProductsFragment : Fragment() {

    private val vm: ProductsViewModel by viewModelCreator {
        ProductsViewModel(ServiceLocator().productsInteractor)
    }
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: Adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        adapter = Adapter(requireContext()){

            val arguments = Bundle()
            arguments.putString("guid", it)

            val anotherFragment = instantiate(
                requireContext(),
                PDPFragment::class.java.name
            )
            anotherFragment.arguments = arguments
            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragmentContainer, anotherFragment)
            ft.addToBackStack(null)
            ft.commit()
        }
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        vm.productLD.observe(viewLifecycleOwner) {
            adapter.listOfProducts = it
        }
        return view
    }
}