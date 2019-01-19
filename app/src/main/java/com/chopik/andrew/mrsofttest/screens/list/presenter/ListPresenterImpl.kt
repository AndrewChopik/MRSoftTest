package com.chopik.andrew.mrsofttest.screens.list.presenter

import androidx.appcompat.widget.SearchView
import com.chopik.andrew.mrsofttest.data.AppDataSource
import com.chopik.andrew.mrsofttest.screens.list.view.DataListView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListPresenterImpl<V : DataListView>
@Inject constructor(private val repository: AppDataSource) : ListPresenter<V> {

    private var view: V? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        compositeDisposable.dispose()
    }

    override fun getView(): V? {
        return view
    }

    override fun loadData() {
        compositeDisposable.add(
            repository.getDataFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { news -> view?.showData(news) },
                    { t -> t.message })
        )
    }

    override fun loadReversedData() {
        compositeDisposable.add(
            repository.getReversedFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { news -> view?.showData(news) },
                    { t -> t.message })
        )
    }

    override fun refreshData() {
        compositeDisposable.add(repository.refreshData()
            .subscribeOn(Schedulers.io())
            .doOnSuccess { news -> repository.insertData(news) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { news -> view?.showData(news) },
                { t -> t.message })
        )
    }

    override fun onReadySearch(searchView: SearchView) {
        compositeDisposable.add(
            Observable.create(ObservableOnSubscribe<String> { subscriber ->
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        subscriber.onNext(newText!!)
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        subscriber.onNext(query!!)
                        return false
                    }
                })
            })
                .subscribeOn(Schedulers.io())
                .map { text -> text.toLowerCase().trim() }
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinct()
                .filter { text -> text.isNotBlank() }
                .flatMapSingle {
                    repository.findByQuery(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { news ->
                    view?.showData(news)
                }
        )
    }
}