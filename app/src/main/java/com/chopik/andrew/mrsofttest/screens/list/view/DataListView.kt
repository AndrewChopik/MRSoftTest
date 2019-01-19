package com.chopik.andrew.mrsofttest.screens.list.view

import com.chopik.andrew.mrsofttest.data.local.entity.News

interface DataListView {

    fun initToolbar()

    fun initRecyclerView()

    fun loadData()

    fun loadReversedData()

    fun refreshData()

    fun hidePlaceholder()

    fun showData(news: List<News>)
}