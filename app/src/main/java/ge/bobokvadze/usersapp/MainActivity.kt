package ge.bobokvadze.usersapp

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ge.bobokvadze.usersapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currencyPages = arrayOf("MainFragment", "EditFragment")

        val adapter = CustomViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = currencyPages[position]
        }.attach()
    }
}

class CustomViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        private const val NUM_TABS = 2
    }

    override fun getItemCount() = NUM_TABS

    override fun createFragment(
        position: Int
    ) = if (position == 0) MainFragment() else EditFragment()
}