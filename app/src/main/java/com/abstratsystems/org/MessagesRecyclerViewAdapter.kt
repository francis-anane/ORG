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
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

class MessagesRecyclerViewAdapter( /* access modifiers changed from: private */
                                   val context: Context
) : RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder>() {
    private var selectedTownCustomers: ArrayList<Customer> = ArrayList<Customer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: called")
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.customer_name, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")
        holder.txtSelectedTownCustomers.setText(selectedTownCustomers[position].getName())
        Glide.with(context).asBitmap().load(selectedTownCustomers[position].getImageData())
            .into(holder.imageData)
    }

    override fun getItemCount(): Int {
        return selectedTownCustomers.size
    }

    fun setSelectedTownCustomers(selectedTownCustomers2: ArrayList<Customer>) {
        selectedTownCustomers = selectedTownCustomers2
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /* access modifiers changed from: private */
        val imageData: ImageView
        private val selectedTownCustomersParent: MaterialCardView

        /* access modifiers changed from: private */
        val txtSelectedTownCustomers: TextView

        init {
            Log.d(TAG, "ViewHolder: called")
            val materialCardView =
                itemView.findViewById<View>(R.id.customerParent) as MaterialCardView
            selectedTownCustomersParent = materialCardView
            txtSelectedTownCustomers = itemView.findViewById<View>(R.id.txtCustomerName) as TextView
            imageData = itemView.findViewById<View>(R.id.imageData) as ImageView
            materialCardView.setOnClickListener {
                Utils.initCustomerDetails(txtSelectedTownCustomers.text.toString(), context)
                context.startActivity(Intent(context, CustomerActivity::class.java))
            }
        }
    }

    companion object {
        private const val TAG = "TownRecyclerViewAdapter"
    }
}