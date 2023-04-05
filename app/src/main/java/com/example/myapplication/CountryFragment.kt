package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentCountryBinding

class CountryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCountryBinding.inflate(inflater,container,false)
        val list = mutableListOf<Item>()
        list.add(Item("Uzbekistan", "447,400 km²",R.drawable.uzb))
        list.add(Item("Armenia", "29,740 km²",R.drawable.arm))
        list.add(Item("Azerbaijan", "86,600 km²",R.drawable.aze))
        list.add(Item("Bahrain", "778 km²",R.drawable.bhr))
        list.add(Item("Cyprus", "\n" +
                "9,250 km²",R.drawable.cyp))
        list.add(Item("Georgia", "69,700 km²",R.drawable.geo))
        list.add(Item("Iraq", "435,052 km²",R.drawable.irq))
        list.add(Item("Israel", "22,070 km²",R.drawable.isr))
        list.add(Item("Jordan", "89,320 km²",R.drawable.jor))
        list.add(Item("Kuwait", "17,820 km²",R.drawable.kwt))

        val adapter = ItemAdapter(list,object : ItemAdapter.ItemInterface{
            override fun onClick(item: Item) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main,InformationFragment())
                    .addToBackStack("CountryFragment")
                    .commit()
            }

        })

        val touchHelper = object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.onItemDismiss(viewHolder.adapterPosition)
            }

        }

        val itemTouchHelper = ItemTouchHelper(touchHelper)
        itemTouchHelper.attachToRecyclerView(binding.rv)
        binding.rv.adapter = adapter
        return binding.root
    }


}