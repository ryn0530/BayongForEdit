package com.example.bayong;

import android.widget.Filter;

import com.example.bayong.adapters.AdapterOrderShop;
import com.example.bayong.adapters.AdapterProductSeller;
import com.example.bayong.models.ModelOrderShop;
import com.example.bayong.models.ModelOrderUser;
import com.example.bayong.models.ModelProduct;

import java.util.ArrayList;

public class FilterOrderShop extends Filter {

    private AdapterOrderShop adapter;
    private ArrayList<ModelOrderShop> filterList;

    public FilterOrderShop(AdapterOrderShop adapter, ArrayList<ModelOrderShop> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        //validate data for search query
        if (charSequence != null && charSequence.length() > 0){
            //change to upper case to make case insensitive
            charSequence = charSequence.toString().toUpperCase();
            //store our filtered list
            ArrayList<ModelOrderShop> filteredModels = new ArrayList<>();
            for (int i=0; i<filterList.size(); i++){
                //check, search by title and category
                if (filterList.get(i).getOrderStatus().toUpperCase().contains(charSequence)){
                    //add filtered data to list
                    filteredModels.add(filterList.get(i));

                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;

        }
        else{
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.orderShopArrayList = (ArrayList<ModelOrderShop>)filterResults.values;
        //refresh adapter
        adapter.notifyDataSetChanged();
    }
}
