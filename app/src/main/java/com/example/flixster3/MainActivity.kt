package com.example.flixster3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import com.example.flixster3.databinding.ActivityMainBinding
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val SEARCH_URL = "https://api.themoviedb.org/3/person/popular?api_key=${API_KEY}"

class MainActivity : AppCompatActivity() {
    private val people = mutableListOf<Person>()
    private lateinit var peopleRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        peopleRecyclerView = findViewById(R.id.people)
        val personAdapter = PersonAdapter(this, people)
        peopleRecyclerView.adapter = personAdapter

        peopleRecyclerView.layoutManager = GridLayoutManager(this, 2).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            peopleRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        client.get(SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched people: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        Results.serializer(),
                        json.jsonObject.toString()
                    )
                    parsedJson.results?.let { list ->
                        people.addAll(list)
                    }
                    personAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }
        })
    }
}