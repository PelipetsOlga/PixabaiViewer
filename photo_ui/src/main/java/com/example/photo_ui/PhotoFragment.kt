package com.example.photo_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.AppTheme
import com.example.domain.models.ImageModel
import com.example.domain.models.imageModelArg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment() {
    private val viewModel: PhotoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val image = arguments?.getParcelable<ImageModel>(imageModelArg)
        if (image == null) {
            findNavController().popBackStack()
        } else {
            viewModel.setImage(image)
        }
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    PhotoComposable(viewModel = viewModel, backClick = { onBackButtonClick() })
                }
            }
        }
    }

    private fun onBackButtonClick() {
        findNavController().popBackStack()
    }
}
