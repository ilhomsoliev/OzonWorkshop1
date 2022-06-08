package ru.ozon.route256.workshop1.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.di.ServiceLocator
import ru.ozon.route256.workshop1.presentation.viewModel.ProductsViewModel
import ru.ozon.route256.workshop1.sources.viewModelCreator

class PDPFragment : Fragment() {
    private val vm: ProductsViewModel by viewModelCreator {
        ProductsViewModel(ServiceLocator().productsInteractor)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_p_d_p, container, false)
        // Inflate the layout for this fragment
        val bundle = this.arguments
        if (bundle != null) {
            val guid = bundle.getString("guid", "Default")
            val item = vm.getProductById(guid)
            view.findViewById<TextView>(R.id.nameTV).text = item.name
            view.findViewById<TextView>(R.id.priceTV).text = item.price
            view.findViewById<RatingBar>(R.id.ratingView).rating = item.rating.toFloat()
            Glide.with(requireContext()).load(item.image)
                .into(view.findViewById<ImageView>(R.id.productIV))
        }

        return view
    }
}