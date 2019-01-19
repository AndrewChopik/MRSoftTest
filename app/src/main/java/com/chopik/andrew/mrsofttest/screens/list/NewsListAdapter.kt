package com.chopik.andrew.mrsofttest.screens.list

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chopik.andrew.mrsofttest.DATE_FORMAT_SERVER
import com.chopik.andrew.mrsofttest.R
import com.chopik.andrew.mrsofttest.data.local.entity.News
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*
import java.text.SimpleDateFormat
import java.util.*

class NewsListAdapter(context: Context) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    private var news: List<News> = emptyList()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun updateAdapter(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = if (news.isEmpty()) 0 else news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindNews(news[position])
    }

    inner class NewsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindNews(news: News) {

            listAuthor.text = news.author
            listTitle.text = news.title
            listDescription.text = news.description
            listDate.text = news.publishedAt.let {
                DateUtils.formatDateTime(
                    containerView.context,
                    SimpleDateFormat(DATE_FORMAT_SERVER, Locale.getDefault()).parse(it).time,
                    DateUtils.FORMAT_SHOW_DATE + DateUtils.FORMAT_SHOW_TIME
                )
            }
            if (news.urlToImage.isNotEmpty()) {
                containerView.context?.let {
                    Glide.with(it)
                        .load(news.urlToImage)
                        .into(listImage)
                }
            }
        }
    }
}