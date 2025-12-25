package com.example.tbc_collaboration_2025.presentation.screen.forgot_password

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_collaboration_2025.databinding.FragmentForgotPasswordBinding as Binding
import com.example.tbc_collaboration_2025.presentation.common.BaseFragment
import com.example.tbc_collaboration_2025.presentation.screen.forgot_password.ForgotPasswordContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: ForgotPasswordViewModel by viewModels()


    override fun listeners() {
        setListenerOnEmailEditText()
        setListenerOnBackToSignInTextView()
    }

    override suspend fun collectObservers(): Unit = with(receiver = viewModel) {
        coroutineScope {
//            launch { state.collect { handleStates(state = it) } }
            launch { sideEffect.collectLatest { handleSideEffects(sideEffect = it) } }
        }
    }

    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnEmailEditText() = binding.emailEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnEmailChanged(value = it.toString()))
    }

    private fun setListenerOnBackToSignInTextView() =
        binding.backToSignInTextView.setOnClickListener { viewModel.onEvent(event = Event.OnBackToSignInClicked) }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
//    private fun handleStates(state: State) = with(receiver = binding) {
//        sendResetLinkButton.isEnabled = state.isLoading.not()
//        backToSignInTextView.isEnabled = state.isLoading.not()
//    }

    private fun handleSideEffects(sideEffect: SideEffect) = with(receiver = sideEffect) {
        when (this) {
            SideEffect.NavigateToSignIn -> navigateToSignInScreen()
        }
    }
    /** ========================================================================================= */


    /** ====================================== NAVIGATIONS ====================================== */
    private fun navigateToSignInScreen() {
        val direction =
            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToSignInFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
