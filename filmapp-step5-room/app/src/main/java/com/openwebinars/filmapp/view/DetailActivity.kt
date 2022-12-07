package com.openwebinars.filmapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.openwebinars.filmapp.R
import com.openwebinars.filmapp.view.DetailFragment.Companion.EXTRA
import com.openwebinars.filmapp.model.Film
import com.openwebinars.filmapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtras()?.let {
            setFragment(DetailFragment.newInstance(it))
        } ?: finish()
    }

    private fun getExtras(): Film? = intent.extras?.getParcelable(EXTRA)

    private fun setFragment(fragmentToChange: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutFragmentHolder, fragmentToChange)
            .commit()
    }
}