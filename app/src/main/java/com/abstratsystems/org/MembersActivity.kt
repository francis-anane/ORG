package com.abstratsystems.org

import Member
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abstratsystems.org.utils.DataInit
import com.abstratsystems.org.utils.SetColor
import com.bumptech.glide.Glide

/**
 * An activity that displays a list of members using a RecyclerView.
 */
class MembersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyRecyclerViewAdapter<Member>
    private lateinit var layoutManager: LinearLayoutManager
    /**
     * Called when the activity is created.
     *
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)
        SetColor.actionBar(this, "#2a6099")

        recyclerView = findViewById(R.id.membersRecyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        adapter = MyRecyclerViewAdapter(
                        context = this@MembersActivity,
                        listData = DataInit.allMembers,
                        itemLayoutResId = R.layout.members_view
                    ) { itemView, member ->
                        val txtMemberName = itemView.findViewById<TextView>(R.id.communicationTextView)
                        val imageData = itemView.findViewById<ImageView>(R.id.membersImageView)

                        // Bind data to the item views
                        txtMemberName.text = member.name
                        Glide.with(this@MembersActivity).asBitmap().load(member.image).into(imageData)
                    }
        recyclerView.adapter = adapter

    }
}