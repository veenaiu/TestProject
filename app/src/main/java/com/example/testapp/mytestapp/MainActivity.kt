package com.example.testapp.mytestapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.example.testapp.mytestapp.domain.Facts
import com.example.testapp.mytestapp.view.FactRecyclerViewAdapter
import com.example.testapp.mytestapp.service.FactsService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fact_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val facts: Facts = Facts("Facts", mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.title = title

        setupRecyclerView(fact_list)

        val swipeContainer : SwipeRefreshLayout = findViewById(R.id.swipeContainer)

      
        swipeContainer.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            loadFacts()
        })

        loadFacts()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = FactRecyclerViewAdapter(facts.rows)
    }

    fun loadFacts() {

        val call = getFactsService().listFacts()
        val swipeContainer : SwipeRefreshLayout = findViewById(R.id.swipeContainer)

       
        call.enqueue(object : Callback<Facts> {
            override fun onResponse(call: Call<Facts>, response: Response<Facts>) {
                factsLoaded(response.body())
                swipeContainer.isRefreshing = false
            }

            override fun onFailure(call: Call<Facts>, t: Throwable) {
                
                swipeContainer.isRefreshing = false
            }
        })
    }

	//adapter call
    fun factsLoaded(facts : Facts?) {
        toolbar.title = facts?.title
        (fact_list.adapter as FactRecyclerViewAdapter).facts = facts!!.rows
         (fact_list.adapter as FactRecyclerViewAdapter).notifyDataSetChanged()
    }

    /**
     * Get the Facts service. 
     */
    fun getFactsService() : FactsService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create<FactsService>(FactsService::class.java)
    }
}
