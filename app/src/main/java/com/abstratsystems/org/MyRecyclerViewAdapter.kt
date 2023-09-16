package com.abstratsystems.org

import Member
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abstratsystems.org.utils.DataInit
import com.google.android.material.card.MaterialCardView

/**
 * RecyclerView Adapter for displaying a list of items of type T.
 *
 * @property context The context in which the adapter is used.
 * @property listData The list of items to display.
 * @property itemLayoutResId The layout resource ID for each item view.
 * @property onBind Function to bind data to the item views.
 */
class MyRecyclerViewAdapter<T>(
    private val context: Context,
    private var listData: ArrayList<T>,
    private val itemLayoutResId: Int,
    private val onBind: (View, T) -> Unit
) : RecyclerView.Adapter<MyRecyclerViewAdapter<T>.ViewHolder>() {

    /**
     * ViewHolder class responsible for holding references to the item views.
     *
     * @param itemView The root view of the item layout.
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Initialize The parent layout of the views to be shown
        private val itemParent: MaterialCardView = itemView.findViewById(R.id.membersCardView)

        init {
            // Set click listener for the item view
            itemParent.setOnClickListener {
                // Set modelObject to the current selected view in the recyclerview
                // representing the object model
                DataInit.modelObject = listData[position]!!

                if(DataInit.modelObject is Member) {
                    // Start an Activity to view Members profile
                    context.startActivity(Intent(context, MemberActivity::class.java))
                }
            }
        }

        /**
         * Bind data of type T to the item view.
         *
         * @param data The data to bind to the item view.
         */
        fun bind(data: T) {
            // Call the provided binding function to set data on the item view
            onBind(itemView, data)
        }
    }

    /**
     * Create a new ViewHolder by inflating the item view layout.
     *
     * @param parent The parent ViewGroup in which the ViewHolder will be created.
     * @param viewType The type of the view to be created.
     * @return A new ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item view layout
        val itemView = LayoutInflater.from(parent.context).inflate(itemLayoutResId, parent, false)
        return ViewHolder(itemView)
    }

    /**
     * Bind data to the item views at a given position.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Call the ViewHolder's bind method to bind data to the item view
        holder.bind(listData[position])



    }

    /**
     * Get the number of items in the data set.
     *
     * @return The number of items in the data set.
     */
    override fun getItemCount(): Int {
        return listData.size
    }

    /**
     * Update the list of items and notify the adapter of the data change.
     *
     * @param newData The new list of items to display.
     */
    fun updateData(newData: ArrayList<T>) {
        listData = newData
        notifyDataSetChanged()
    }
}
