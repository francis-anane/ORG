package com.abstratsystems.org

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abstratsystems.org.models.Message
import com.abstratsystems.org.utils.CreateObJect
import com.abstratsystems.org.utils.DataInit
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject

class MessageActivity : AppCompatActivity() {
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyRecyclerViewAdapter<Message>
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var webSocket: WebSocket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        // Initialize UI elements
        messageEditText = findViewById(R.id.messageEditText)
        sendButton = findViewById(R.id.sendButton)
        recyclerView = findViewById(R.id.messageRecyclerView)


        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // Display messages from database
        adapter = MyRecyclerViewAdapter(
            context = this@MessageActivity,
            listData = DataInit.allMessages,
            itemLayoutResId = R.layout.communications_view
        ) { itemView, message ->
            val messageTextView = itemView.findViewById<TextView>(R.id.communicationTextView)

            // Bind data to the item views
            messageTextView.text = message.content
        }
        recyclerView.adapter = adapter

        // Set up WebSocket connection
        initWebSocket()

        // Set a click listener for the Send button
        sendButton.setOnClickListener { sendMessage() }
    }

    private fun initWebSocket() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("ws://abstrat22.tech/messages") // Replace with your WebSocket server URL
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                // WebSocket connection is established
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // Handle incoming messages from the server
                runOnUiThread {
                    // Update the UI with received messages
                    displayReceivedMessage(text)
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                // WebSocket connection is closed
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                // Handle WebSocket connection failure
            }
        })
    }

    private fun sendMessage() {
        val messageText = messageEditText.text.toString().trim()
        if (messageText.isNotEmpty()) {
            try {
                val jsonData = JSONObject()
                jsonData.put("content", messageText) // set message content
                val gson = Gson()
                val message: Message = gson.fromJson(jsonData.toString(), Message::class.java)

                DataInit.allMessages.add(message) // Add member object to current local data
                CreateObJect.message(message) // add message object to database

                // Send the message to the WebSocket server
                webSocket.send(jsonData.toString())

                // Clear the message input field
                messageEditText.text.clear()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    private fun displayReceivedMessage(message: String) {
        // Parse and display the received message in your messageRecyclerView
        // You need to implement this part based on your UI requirements
    }
}
