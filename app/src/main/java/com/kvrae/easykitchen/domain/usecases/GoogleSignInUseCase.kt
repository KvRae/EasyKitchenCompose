package com.kvrae.easykitchen.domain.usecases

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.kvrae.easykitchen.data.remote.dto.User
import com.kvrae.easykitchen.data.repository.AuthRepository

class GetGoogleSignInClientUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(activity: Activity): GoogleSignInClient {
        return authRepository.getGoogleSignInClient(activity)
    }
}

class GetSignInIntentUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(googleSignInClient: GoogleSignInClient): Intent {
        return authRepository.getSignInIntent(googleSignInClient)
    }
}

class HandleSignInResultUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(completedTask: Task<GoogleSignInAccount>): Result<String> {
        return authRepository.handleSignInResult(completedTask)
    }
}

class SendIdTokenToBackendUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(idToken: String): Result<User> {
        return authRepository.sendIdTokenToBackend(idToken)
    }
}