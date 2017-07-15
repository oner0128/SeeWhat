package com.onerous.kotlin.seewhat.InTheaters


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onerous.kotlin.seewhat.BaseFragment
import com.onerous.kotlin.seewhat.R
import com.onerous.kotlin.seewhat.data.MoviesBean
import kotlinx.android.synthetic.main.fragment_in_theaters.*

/**
 * Created by rrr on 2017/7/15.
 */
class InTheatersFragment : BaseFragment(), InTheatersContract.View {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private var mDatas: ArrayList<MoviesBean.Subjects> = ArrayList()
    private var mAdapter: InTheatersAdapter?= null
    private var mPresenter: InTheatersPresenter?= null

    fun init() {
        mAdapter=InTheatersAdapter(context,mDatas)
        mPresenter=InTheatersPresenter(this)
        recyclerView.setLayoutManager(GridLayoutManager(context,2))
        recyclerView.adapter=mAdapter
        mPresenter!!.getInTheatersMovies()
    }
    override fun showError(error: String?) {
        Log.e("intheater:",error)
    }

    override fun showMovies(moviesBean: MoviesBean) {
        mDatas.clear()
        //banner top stories
        mDatas.addAll(moviesBean.subjects)
        mAdapter?.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)
        swipeRefreshLayout.setRefreshing(false)
    }

    override fun setPresenter(presenter: InTheatersContract.Presenter) {
//        mPresenter=presenter
    }

    override fun showProgressDialog() {
        swipeRefreshLayout.isRefreshing=true
    }

    override fun hideProgressDialog() {
        swipeRefreshLayout.isRefreshing=false
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_in_theaters, container, false);
    }
}