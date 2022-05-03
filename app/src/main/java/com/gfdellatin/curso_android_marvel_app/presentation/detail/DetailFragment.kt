package com.gfdellatin.curso_android_marvel_app.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.gfdellatin.curso_android_marvel_app.databinding.FragmentDetailBinding
import com.gfdellatin.curso_android_marvel_app.framework.imageloader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailViewArg = args.detailViewArg
        binding.imageCharacter.run {
            transitionName = detailViewArg.name
            imageLoader.load(this, detailViewArg.imageUrl)
        }
        setSharedElementTransitionOnEnter()

        observeUiState(detailViewArg)
        observeFavoriteUiState()

        viewModel.getCharacterCategories(detailViewArg.characterId)

        binding.imageFavoriteIcon.setOnClickListener {
            viewModel.updateFavorite(detailViewArg)
        }
    }

    private fun observeUiState(detailViewArg: DetailViewArg) {

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.flipperDetail.displayedChild = when (uiState) {
                DetailViewModel.UiState.Loading -> FLIPPER_CHILD_POSITION_LOADING
                is DetailViewModel.UiState.Success -> {
                    binding.recyclerParentDetail.run {
                        setHasFixedSize(true)
                        adapter = DetailParentAdapter(uiState.detailParentList, imageLoader)
                    }

                    FLIPPER_CHILD_POSITION_DETAIL

                }
                DetailViewModel.UiState.Error -> {
                    binding.includeErrorView.buttonRetry.setOnClickListener {
                        viewModel.getCharacterCategories(detailViewArg.characterId)
                    }
                    FLIPPER_CHILD_POSITION_ERROR
                }
                DetailViewModel.UiState.Empty -> FLIPPER_CHILD_POSITION_EMPTY
            }
        }

    }

    private fun observeFavoriteUiState() {
        viewModel.favoriteUiState.observe(viewLifecycleOwner) { favoriteUiState ->
            binding.flipperFavorite.displayedChild = when (favoriteUiState) {
                DetailViewModel.FavoriteUiState.Loading -> FLIPPER_FAVORITE_CHILD_POSITION_LOADING
                is DetailViewModel.FavoriteUiState.FavoriteIcon -> {
                    binding.imageFavoriteIcon.setImageResource(favoriteUiState.icon)
                    FLIPPER_FAVORITE_CHILD_POSITION_SUCCESS
                }
            }
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
            sharedElementEnterTransition = this
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_POSITION_LOADING = 0
        private const val FLIPPER_CHILD_POSITION_DETAIL = 1
        private const val FLIPPER_CHILD_POSITION_ERROR = 2
        private const val FLIPPER_CHILD_POSITION_EMPTY = 3
        private const val FLIPPER_FAVORITE_CHILD_POSITION_SUCCESS = 0
        private const val FLIPPER_FAVORITE_CHILD_POSITION_LOADING = 1
    }

}