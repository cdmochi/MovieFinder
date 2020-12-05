package chayapam.s.moviefinder.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.viewpager2.widget.ViewPager2
import chayapam.s.moviefinder.MainActivity
import chayapam.s.moviefinder.R
import chayapam.s.moviefinder.model.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_view_pager.*

class ViewPagerFragment : Fragment() {

    private lateinit var viewP: ViewPager2
    private val list = arrayListOf(MainActivityFragment(),FavoriteMovieListFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewP.adapter = ViewPageAdapter(list,childFragmentManager,lifecycle)
        val activity = requireActivity() as MainActivity
        val tabLayoutFromMainAct = activity.ui_tabLayout
        TabLayoutMediator(tabLayoutFromMainAct,viewP) { tab, position ->
            viewP.setCurrentItem(tab.position,true)
            when(position) {
                0->{ tab.text = requireActivity().resources.getString(R.string.all_movies) }
                1->{ tab.text = requireActivity().resources.getString(R.string.favorite)}
            }
        }.attach()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showTabLayoutFromMainActivity()
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }

    private fun showTabLayoutFromMainActivity() {
        requireActivity().ui_tabLayout.visibility = View.VISIBLE
    }

    private fun initViews() {
        viewP = viewpager2
    }

}