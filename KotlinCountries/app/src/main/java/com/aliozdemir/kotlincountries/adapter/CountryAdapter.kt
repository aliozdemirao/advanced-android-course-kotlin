package com.aliozdemir.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.kotlincountries.R
import com.aliozdemir.kotlincountries.databinding.ItemCountryBinding
import com.aliozdemir.kotlincountries.model.Country
import com.aliozdemir.kotlincountries.util.downloadFromUrl
import com.aliozdemir.kotlincountries.util.placeholderProgressBar
import com.aliozdemir.kotlincountries.view.FeedFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener{

    class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val binding = ItemCountryBinding.inflate(inflater, parent, false)
        val binding = DataBindingUtil.inflate<ItemCountryBinding>(inflater, R.layout.item_country, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.binding.country = countryList[position]
        holder.binding.listener = this

        /*
        holder.binding.name.text = countryList[position].countryName
        holder.binding.region.text = countryList[position].countryRegion

        holder.binding.root.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            //action.countryUuid
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageView.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.binding.root.context))
        */

    }

    override fun getItemCount(): Int {
        return countryList.size
    }


    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val binding = DataBindingUtil.bind<ItemCountryBinding>(v)
        val uuid = binding?.countryUuidText?.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}