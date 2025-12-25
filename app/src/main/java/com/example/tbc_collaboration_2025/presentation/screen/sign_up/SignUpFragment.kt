package com.example.tbc_collaboration_2025.presentation.screen.sign_up

import android.graphics.Color
import android.text.Annotation
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.compose.ui.util.fastJoinToString
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_collaboration_2025.common.Colors
import com.example.tbc_collaboration_2025.common.Strings
import com.example.tbc_collaboration_2025.domain.error.AppError.*
import com.example.tbc_collaboration_2025.domain.error.ValidationError.*
import com.example.tbc_collaboration_2025.databinding.FragmentCreateAccountBinding as Binding
import com.example.tbc_collaboration_2025.presentation.common.BaseFragment
import com.example.tbc_collaboration_2025.presentation.extension.popMessage
import com.example.tbc_collaboration_2025.presentation.screen.sign_up.SignUpContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: SignUpViewModel by viewModels()


    override fun bind() = setupClickableSignInTextView()

    override fun listeners() {
        setListenerOnFirstNameEditText()
        setListenerOnLastNameEditText()
        setListenerOnEmailEditText()
        setListenerOnPasswordEditText()
        setListenerOnConfirmPasswordEditText()
        setListenerOnTermsAndPrivacyCheckBox()
        setListenerOnCreateAccountButton()
    }

    override suspend fun collectObservers(): Unit = with(receiver = viewModel) {
        coroutineScope {
//            launch { state.collect { handleStates(state = it) } }
            launch { sideEffect.collectLatest { handleSideEffects(sideEffect = it) } }
        }
    }


    // TODO: show and hide keyboards can be used here
    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnFirstNameEditText() = binding.firstNameEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnFirstNameChanged(value = it.toString()))
    }

    private fun setListenerOnLastNameEditText() = binding.lastNameEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnLastNameChanged(value = it.toString()))
    }

    private fun setListenerOnEmailEditText() = binding.emailEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnEmailChanged(value = it.toString()))
    }

    private fun setListenerOnPasswordEditText() = binding.passwordEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnPasswordChanged(value = it.toString()))
    }

    private fun setListenerOnConfirmPasswordEditText() =
        binding.confirmPasswordEditText.doAfterTextChanged {
            viewModel.onEvent(event = Event.OnConfirmPasswordChanged(value = it.toString()))
        }

    private fun setListenerOnTermsAndPrivacyCheckBox() =
        binding.agreeToTermsAndPrivacyCheckBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEvent(event = Event.OnAgreementChanged(isChecked = isChecked))
        }

    private fun setListenerOnCreateAccountButton() =
        binding.createAccountButton.setOnClickListener {
            viewModel.onEvent(event = Event.OnSignUpClicked)
        }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
//    private fun handleStates(state: State) {
//        binding.createAccountButton.isEnabled = state.isLoading.not()
//    }

    private fun handleSideEffects(sideEffect: SideEffect) = with(receiver = binding.root) {
        when (sideEffect) {
            is SideEffect.ShowError -> {
                val error = when (sideEffect.errorCode) {
                    NetworkError -> ContextCompat.getString(context, Strings.error_network)
                    ApiError -> ContextCompat.getString(context, Strings.error_api)
                    StateError -> ContextCompat.getString(context, Strings.error_state)
                    UnknownError -> ContextCompat.getString(context, Strings.error_unknown)
                    is ValidationError -> {
                        sideEffect.errorCode.errors.fastJoinToString {
                            when (it) {
                                FieldEmpty -> ContextCompat.getString(context, Strings.error_empty_fields)
                                InvalidEmail -> ContextCompat.getString(context, Strings.error_invalid_email)
                                PasswordTooWeak -> ContextCompat.getString(context, Strings.error_weak_password)
                            }
                        }
                    }
                    is Message -> sideEffect.errorCode.value
                }
                popMessage(text = error, color = Colors.amaranth)
            }

            SideEffect.NavigateToEventHub -> navigateToEventHubScreen()
            SideEffect.NavigateToSignIn -> navigateToSignInScreen()
        }
    }
    /** ========================================================================================= */


    /** ====================================== NAVIGATIONS ====================================== */
    private fun navigateToEventHubScreen() {
        val direction = SignUpFragmentDirections.actionCreateAccountFragmentToEventHubFragment()
        findNavController().navigate(directions = direction)
    }

    private fun navigateToSignInScreen() {
        val direction = SignUpFragmentDirections.actionCreateAccountFragmentToSignInFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun setupClickableSignInTextView(): Unit = with(receiver = binding.signInTextView) {
        val fullText = context.getText(Strings.already_have_an_account_sign_in) as Spanned
        val spannable = SpannableStringBuilder(fullText)
        val annotations = fullText.getSpans(0, fullText.length, Annotation::class.java)
        val target = annotations.find { it.value == ANNOTATION_SIGN_IN }

        target?.let {
            val what = object : ClickableSpan() {
                override fun onClick(widget: View) =
                    viewModel.onEvent(event = Event.OnSignInClicked)

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = Color.BLACK
                }
            }
            val start = fullText.getSpanStart(it)
            val end = fullText.getSpanEnd(it)
            val flags = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            spannable.setSpan(what, start, end, flags)
        }

        apply {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }
    /** ========================================================================================= */


    private companion object {
        const val ANNOTATION_SIGN_IN = "sign_in"
    }
}
