package com.aliozdemir.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.aliozdemir.kotlincountries.R
import com.aliozdemir.kotlincountries.databinding.FragmentCountryBinding
import com.aliozdemir.kotlincountries.util.downloadFromUrl
import com.aliozdemir.kotlincountries.util.placeholderProgressBar
import com.aliozdemir.kotlincountries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {

    private var countryUuid = 0

    private lateinit var viewModel : CountryViewModel

    private lateinit var dataBinding: FragmentCountryBinding


    //private var _binding: FragmentCountryBinding? = null
    //private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //_binding = FragmentCountryBinding.inflate(inflater, container, false)
        //val view = binding.root

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }


        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()

    }


    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {

                dataBinding.selectedCountry = country

                /*
                binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryRegion.text = country.countryRegion
                binding.countryCurrency.text = country.countryCurrency
                binding.countryLanguage.text = country.countryLanguage

                context?.let {
                    binding.countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
                }
                */

            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }


}