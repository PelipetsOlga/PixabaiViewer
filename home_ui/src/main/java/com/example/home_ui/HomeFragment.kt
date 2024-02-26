package com.example.home_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.AppTheme
import com.example.domain.models.ImageModel
import com.example.domain.models.imageModelArg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    Scaffold(topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Pixabay Viewer",
                                )
                            },
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )
                    }, content = { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            HomeComposable(
                                viewModel = viewModel,
                                itemClick = { navigateToPhoto(it) })
                        }
                    })
                }
            }
        }
    }

    private fun navigateToPhoto(pixImage: ImageModel) {
        findNavController().navigate(R.id.action_to_photo, bundleOf(imageModelArg to pixImage))
    }
}
