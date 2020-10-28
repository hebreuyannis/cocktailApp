package com.hebreuyannis.cocktailapp

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData


class SnackbarMessageManager {

    private val messages: MutableList<SnackbarMessage> = mutableListOf()
    private val currentMessage: MutableLiveData<SnackbarMessage> =
        MutableLiveData<SnackbarMessage>()

    companion object {
        // Keep a fixed number of old items
        @VisibleForTesting
        const val MAX_ITEMS = 10
    }

    fun addMessage(msg: SnackbarMessage) {

        val sameRequestId = messages.filter { it.requestId == msg.requestId }

        if (sameRequestId.isNotEmpty()) {
            messages.removeAll(sameRequestId)
        }

        // Remove old messages
        if (messages.size > MAX_ITEMS) {
            messages.retainAll(messages.drop(messages.size - MAX_ITEMS))
        }
    }

    fun loadNextMessage() {
        messages.removeAll { it.requestId == currentMessage.value?.requestId }
        currentMessage.postValue(messages.firstOrNull())
    }

    fun observeNextMessage(): MutableLiveData<SnackbarMessage> {
        return currentMessage
    }
}