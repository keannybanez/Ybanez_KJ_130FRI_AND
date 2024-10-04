package com.example.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HeadlineListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val headlines = listOf("Headline 1", "Headline 2", "Headline 3")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_headline_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        newsAdapter = NewsAdapter(headlines) { headline -> onHeadlineSelected(headline) }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = newsAdapter
        return view
    }

    private fun onHeadlineSelected(headline: String) {
        val contentFragment = NewsContentFragment.newInstance(headline)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, contentFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}