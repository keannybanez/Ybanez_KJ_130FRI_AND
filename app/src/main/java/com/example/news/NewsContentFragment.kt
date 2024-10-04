package com.example.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsContentFragment : Fragment() {

    companion object {
        private const val ARG_HEADLINE = "headline"

        fun newInstance(headline: String) = NewsContentFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_HEADLINE, headline)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_content, container, false)
        val newsContent = view.findViewById<TextView>(R.id.newsContent)

        val headline = arguments?.getString(ARG_HEADLINE)
        newsContent.text = "Content for: $headline"
        return view
    }
}
