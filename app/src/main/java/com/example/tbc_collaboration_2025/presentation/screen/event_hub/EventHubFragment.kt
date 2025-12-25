package com.example.tbc_collaboration_2025.presentation.screen.event_hub

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_collaboration_2025.databinding.FragmentEventHubBinding as Binding
import com.example.tbc_collaboration_2025.presentation.common.BaseFragment
import com.example.tbc_collaboration_2025.presentation.screen.event_hub.EventHubContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventHubFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: EventHubViewModel by viewModels()


    override fun listeners() {
        binding.logoutButton.setOnClickListener {
            viewModel.onEvent(event = Event.OnLogOutClicked)
        }
    }

    override suspend fun collectObservers(): Unit = with(receiver = viewModel) {
        coroutineScope {
//            launch { state.collect { handleStates(state = it) } }
            launch { sideEffect.collectLatest { handleSideEffects(sideEffect = it) } }
        }
    }

    private fun handleSideEffects(sideEffect: SideEffect) {
        when (sideEffect) {
            SideEffect.NavigateToSignIn -> navigateToEventHubScreen()
        }
    }

    private fun navigateToEventHubScreen() {
        val direction = EventHubFragmentDirections.actionEventHubFragmentToSignInFragment()
        findNavController().navigate(directions = direction)
    }
}
