package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.databinding.FragmentDetailBinding
import com.erolaksoy.mynewsapp.viewmodels.DetailViewModel
import com.erolaksoy.mynewsapp.viewmodels.DetailViewModelFactory

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private val arguments: DetailFragmentArgs by lazy {
        DetailFragmentArgs.fromBundle(requireArguments())
    }
    private val viewModelFactory: DetailViewModelFactory by lazy {
        DetailViewModelFactory(arguments.articledb, requireActivity().application)
    }
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //setHasOptionsMenu(true)

        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())
        val application = requireActivity().application
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }



    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.savePost)
        if (viewModel.article.isBookmarked) {
            item.setIcon(R.drawable.ic_baseline_bookmarkwhite_24)
        }
        super.onPrepareOptionsMenu(menu)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.savePost) {
            viewModel.saveToBookmark()
            if (viewModel.article.isBookmarked) {
                item.setIcon(R.drawable.ic_baseline_bookmarkwhite_24)
            } else {
                item.setIcon(R.drawable.ic_baseline_bookmark_border_24)
            }
        }
        return false
    }

}
