package com.hebreuyannis.data_remote.exception

class LoadingException : Exception()

class NetworkException(val code: Int) : Exception()
