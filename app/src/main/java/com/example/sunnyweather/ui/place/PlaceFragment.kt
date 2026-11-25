package com.example.sunnyweather.ui.place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.R
import kotlin.collections.emptyList
import kotlin.getValue

class PlaceFragment: Fragment() {
    val viewModel by lazy { ViewModelProvider(this)[PlaceViewModel::class.java] }
    private lateinit var adapter: PlaceAdapter
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_place,container,false)
    }

    // ✅ 用 onViewCreated 替代废弃的 onActivityCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        // ✅ 必须先初始化 adapter，才能使用
        adapter = PlaceAdapter(this,emptyList())
        recyclerView.adapter = adapter
        val searchPlaceEdit: EditText = view.findViewById(R.id.searchPlaceEdit)
        val bgImageView: ImageView = view.findViewById(R.id.bgImageView)
        searchPlaceEdit.addTextChangedListener{ editable ->
            val content = editable.toString().trim()
            Log.d("SEARCH_LOG","输入内容：$content")
            if (content.isNotEmpty()){
                viewModel.searchPlaces(content)
            }else{
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                adapter.setList(emptyList())
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.placeLiveData.observe(viewLifecycleOwner){result ->
            val places = result.getOrNull()
            if (places != null && places.isNotEmpty()){
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                adapter.setList(places)
                adapter.notifyDataSetChanged()
            }else{
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }
}