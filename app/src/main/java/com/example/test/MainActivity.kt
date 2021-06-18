package com.example.test


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.adapter.SongsAdapter
import com.example.test.databinding.ActivityMainBinding
import com.example.test.models.Songs
import com.example.test.service.ForegroundService
import com.example.test.service.MusicService


class MainActivity : AppCompatActivity() {
companion object {


    var boolIconState = false
}
    lateinit var adapter: SongsAdapter
    lateinit var countryrv: RecyclerView



    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var people: ArrayList<Songs> = arrayListOf()
    private var matchedPeople: ArrayList<Songs> = arrayListOf()
    private var personAdapter: SongsAdapter = SongsAdapter(people)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val buttonStartService: Button? = findViewById<Button>(R.id.button_start_service)
        val buttonCloseService: Button? = findViewById<Button>(R.id.button_close_service)
        buttonStartService?.setOnClickListener() {
            boolIconState = true
            startservıce()

        }
        buttonCloseService?.setOnClickListener() {
            stopService()
            boolIconState = false
        }
        val layoutId = resources.getIdentifier(
            "layout_simulated_notification", "layout",
            packageName
        )

        this@MainActivity.runOnUiThread(java.lang.Runnable {


        })
        initRecyclerView()
        performSearch()
    }

    public fun startservıce() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", "test")
        ContextCompat.startForegroundService(this, serviceIntent)
    }


    public fun stopService() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        stopService(serviceIntent)
        val MusicserviceIntent = Intent(this, MusicService::class.java)
        stopService(MusicserviceIntent)
    }



    private fun initRecyclerView() {

        people = arrayListOf(
            Songs("ali", 19),
            Songs("veli", 19),
            Songs("Mehmet", 21),
            Songs("Bukalemun", 19),
            Songs("Ördek", 35),
            Songs("top", 23),
            Songs("Test", 20),
            Songs("Deneme", 24),

        )

        personAdapter = SongsAdapter(people).also {
            binding.recyclerView.adapter = it
            binding.recyclerView.adapter!!.notifyDataSetChanged()
        }
        binding.searchView.isSubmitButtonEnabled = true
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun search(text: String?) {
        matchedPeople = arrayListOf()

        text?.let {
            people.forEach { person ->
                if (person.songName.contains(text, true) ||
                    person.time.toString().contains(text, true)
                ) {
                    matchedPeople.add(person)
                    updateRecyclerView()
                }
            }
            if (matchedPeople.isEmpty()) {
                Toast.makeText(this, "No match found!", Toast.LENGTH_SHORT).show()
            }
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {
        binding.recyclerView.apply {
            personAdapter.list = matchedPeople
            personAdapter.notifyDataSetChanged()
        }
    }
}