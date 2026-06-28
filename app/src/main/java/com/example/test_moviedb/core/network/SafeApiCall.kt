package com.example.test_moviedb.core.network

import com.example.test_moviedb.core.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun <T> safeApiCall(apiCall: suspend () -> T): Flow<Resource<T>> = flow {
    emit(Resource.Loading)
    try {
        emit(Resource.Success(apiCall()))
    } catch (e: HttpException) {
        val errorMessage = when (e.code()) {
            401 -> "Unauthorized: Please check your API key."
            403 -> "Forbidden: You don't have permission to access this resource."
            404 -> "Not Found: The resource could not be found."
            429 -> "Too Many Requests: You have exceeded the rate limit."
            in 500..599 -> "Server Error: Something went wrong on the server."
            else -> "Unexpected Error: ${e.message()}"
        }
        emit(Resource.Error(message = errorMessage, cause = e))
    } catch (e: SocketTimeoutException) {
        emit(Resource.Error(message = "Connection Timeout: Please try again later.", cause = e))
    } catch (e: IOException) {
        emit(Resource.Error(message = "Network Error: Please check your internet connection.", cause = e))
    } catch (e: Exception) {
        emit(Resource.Error(message = "An unexpected error occurred: ${e.localizedMessage}", cause = e))
    }
}