package com.chopik.andrew.mrsofttest.screens.list.presenter

import androidx.appcompat.widget.SearchView
import com.chopik.andrew.mrsofttest.screens.list.view.DataListView

interface ListPresenter<V : DataListView> {

    fun onAttach(view : V)

    fun onDetach()

    fun getView(): V?

    fun loadData()

    fun loadReversedData()

    fun refreshData()

    fun onReadySearch(searchView: SearchView)
}