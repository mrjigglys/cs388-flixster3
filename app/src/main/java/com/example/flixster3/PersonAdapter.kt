package com.example.flixster3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "PersonAdapter"

class PersonAdapter(private val context: Context, private val people: List<Person>) :
        RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false)
                return ViewHolder(view)
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val person = people[position]
                holder.bind(person)
            }

        override fun getItemCount() = people.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
            private val personImageView = itemView.findViewById<ImageView>(R.id.personImage)
            private val nameTextView = itemView.findViewById<TextView>(R.id.personName)

            init {
                itemView.setOnClickListener(this)
            }

            fun bind(person: Person) {
                nameTextView.text = person.name

                Glide.with(context)
                    .load(person.personImageUrl)
                    .into(personImageView)
            }

            override fun onClick(v: View?) {
                val movie = people[adapterPosition].knownFor?.get(0)
                val intent = Intent(context, DetailPerson::class.java)
                intent.putExtra(MOVIE_EXTRA, movie)
                context.startActivity(intent)
            }
            }
}