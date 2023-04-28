package com.aliozdemir.koincryptocrazy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliozdemir.koincryptocrazy.databinding.FragmentListBinding
import com.aliozdemir.koincryptocrazy.model.CryptoModel
import com.aliozdemir.koincryptocrazy.service.CryptoAPI
import com.aliozdemir.koincryptocrazy.viewmodel.CryptoViewModel
import kotlinx.coroutines.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment(), RecyclerViewAdapter.Listener, AndroidScopeComponent {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var cryptoAdapter = RecyclerViewAdapter(arrayListOf(), this)

    //with di inject vm
    private val viewModel by viewModel<CryptoViewModel>()

    //if i needed to inject any class here it would have gone like this
    //this is not lazy injection, this will be created once fragment is created
    //private val api = get<CryptoAPI>()

    //this is lazy injection, this is only injected when it is first used time
    //private val apilazy by inject<CryptoAPI>()


    //if we want to inject scoped providing modules, it is not different
    // we need to define the scope by adding AndroidScopeComponent to Fragment implementation
    //then we can override the scope
    // if we have more than two same objects such as strings we can use named parameter
    //change hello to hi and you will see other one will be injected
    override val scope: Scope by fragmentScope()
    private val hello by inject<String>(qualifier = named("hello"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        viewModel.getDataFromAPI()

        observeLiveData()

        println(hello)
    }


    fun observeLiveData() {
        viewModel.cryptoList.observe(viewLifecycleOwner, Observer {cryptos ->
            cryptos?.let {
                binding.recyclerView.visibility = View.VISIBLE
                cryptoAdapter = RecyclerViewAdapter(ArrayList(cryptos.data ?: arrayListOf()),this@ListFragment)
                binding.recyclerView.adapter = cryptoAdapter
            }

        })

        viewModel.cryptoError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it.data == true) {
                    binding.cryptoErrorText.visibility = View.VISIBLE
                } else {
                    binding.cryptoErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.cryptoLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it.data == true) {
                    binding.cryptoProgressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.cryptoErrorText.visibility = View.GONE
                } else {
                    binding.cryptoProgressBar.visibility = View.GONE
                }
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(requireContext(), "Clicked on: ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()
    }
}