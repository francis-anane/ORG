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

class MembersRecyclerViewAdapter( /* access modifiers changed from: private */
                                  val context: Context
) : RecyclerView.Adapter<MembersRecyclerViewAdapter.ViewHolder>() {
    private var customers: ArrayList<Customer> = ArrayList<Customer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: called")
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.customer_name, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")
        holder.txtCustomerName.setText(customers[position].getName())
        Glide.with(context).asBitmap().load(customers[position].getImageData())
            .into(holder.imageData)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    fun setCustomers(customers2: ArrayList<Customer>) {
        customers = customers2
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val customerParent: MaterialCardView

        /* access modifiers changed from: private */
        val imageData: ImageView

        /* access modifiers changed from: private */
        val txtCustomerName: TextView
        private val txtTown: TextView

        init {
            Log.d(TAG, "ViewHolder: called")
            val materialCardView =
                itemView.findViewById<View>(R.id.customerParent) as MaterialCardView
            customerParent = materialCardView
            imageData = itemView.findViewById<View>(R.id.imageData) as ImageView
            txtTown = itemView.findViewById<View>(R.id.txtTown) as TextView
            txtCustomerName = itemView.findViewById<View>(R.id.txtCustomerName) as TextView
            materialCardView.setOnClickListener {
                currentDetail = txtCustomerName.text.toString()
                Utils.initCustomerDetails(currentDetail, context)
                context.startActivity(Intent(context, CustomerActivity::class.java))
            }
        }
    }

    companion object {
        private const val TAG = "MyRecyclerViewAdapter"
        var currentDetail: String? = null
    }
}