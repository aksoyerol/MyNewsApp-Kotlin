package com.erolaksoy.mynewsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        activity?.title = viewModel.article.author
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
            item.setIcon(R.drawable.bookmarked_black_image)
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.savePost -> {
                viewModel.saveToBookmark()
                if (viewModel.article.isBookmarked) {
                    item.setIcon(R.drawable.bookmarked_black_image)
                } else {
                    item.setIcon(R.drawable.bookmark_black_bordered)
                }
            }
            R.id.sharePost -> {
                val shareIntent: Intent = Intent().apply() {
                    action = Intent.ACTION_SEND
//                    putExtra(Intent.EXTRA_TEXT,viewModel.article.content)
//                    putExtra(Intent.EXTRA_TEXT,viewModel.article.url)
                    putExtra(Intent.EXTRA_STREAM, viewModel.article.urlToImage?.toUri())
                    type = "image/jpg"
                }
                startActivity(Intent.createChooser(
                    shareIntent,
                    "Once Upon a News App sharing with you",
                ))
            }
        }


        return false
    }

}
