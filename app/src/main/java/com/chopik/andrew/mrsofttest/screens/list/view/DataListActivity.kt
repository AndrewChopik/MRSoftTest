package com.chopik.andrew.mrsofttest.screens.list.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.chopik.andrew.mrsofttest.App.Companion.appComponent
import com.chopik.andrew.mrsofttest.R
import com.chopik.andrew.mrsofttest.data.local.entity.News
import com.chopik.andrew.mrsofttest.screens.list.NewsListAdapter
import com.chopik.andrew.mrsofttest.screens.list.presenter.ListPresenter
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class DataListActivity : AppCompatActivity(), DataListView {

    @Inject
    lateinit var presenter: ListPresenter<DataListView>
    @Inject
    lateinit var newsAdapter: NewsListAdapter

    private var isDescSort = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        appComponent.inject(this)
        presenter.onAttach(this)
        initToolbar()
        initRecyclerView()
    }

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        title = getString(R.string.app_title)
    }

    override fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
        loadData()
    }

    override fun loadData() {
        presenter.loadData()
    }

    override fun loadReversedData() {
        presenter.loadReversedData()
    }

    override fun refreshData() {
        presenter.refreshData()
    }

    override fun showData(news: List<News>) {
        if (news.isNotEmpty()) {
            hidePlaceholder()
        }
        newsAdapter.updateAdapter(news)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val refreshButton = menu.findItem(R.id.refreshButton).setOnMenuItemClickListener {
            refreshData()
            true
        }

        val sortButton = menu.findItem(R.id.sortButton).setOnMenuItemClickListener {
            if (isDescSort) loadData() else loadReversedData()
            isDescSort = isDescSort.not()
            true
        }

        val searchView = menu.findItem(R.id.liveSearch).actionView as SearchView
        presenter.onReadySearch(searchView)

        return true
    }

    override fun hidePlaceholder() {
        placeholder.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
