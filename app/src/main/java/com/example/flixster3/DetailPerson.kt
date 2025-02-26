package com.example.flixster3

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailPerson"
class DetailPerson : AppCompatActivity(){
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.movieImage)
        titleTextView = findViewById(R.id.movieTitle)
        overviewTextView = findViewById(R.id.movieOverview)

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        titleTextView.text = movie.movieName
        overviewTextView.text = movie.movieDescription
        Glide.with(this)
            .load(movie.movieImageUrl)
            .into(mediaImageView)
    }
}