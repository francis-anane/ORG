package com.abstratsystems.org

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class AnnouncementsRecyclerViewAdapter( /* access modifiers changed from: private */
                                        val context: Context
) : RecyclerView.Adapter<AnnouncementsRecyclerViewAdapter.ViewHolder>() {
    private var towns: ArrayList<Town> = ArrayList<Town>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: called")
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.town_name, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")
        holder.txtTown.setText(towns[position].getTownName())
    }

    override fun getItemCount(): Int {
        return towns.size
    }

    fun setTowns(towns2: ArrayList<Town>) {
        towns = towns2
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val customerParent: MaterialCardView
        private val imageData: ImageView
        private val townParent: MaterialCardView
        private val txtCustomerName: TextView

        /* access modifiers changed from: private */
        val txtTown: TextView

        init {
            Log.d(TAG, "ViewHolder: called")
            townParent = itemView.findViewById<View>(R.id.townParent) as MaterialCardView
            customerParent = itemView.findViewById<View>(R.id.customerParent) as MaterialCardView
            imageData = itemView.findViewById<View>(R.id.imageData) as ImageView
            val textView = itemView.findViewById<View>(R.id.txtTown) as TextView
            txtTown = textView
            txtCustomerName = itemView.findViewById<View>(R.id.txtCustomerName) as TextView
            textView.setOnClickListener {
                Utils.emptyList()
                Utils.initTownCustomers(txtTown.text.toString(), context)
                context.startActivity(Intent(context, SelectedTownActivity::class.java))
            }
        }
    }

    companion object {
        private const val TAG = "TownRecyclerViewAdapter"
    }
}