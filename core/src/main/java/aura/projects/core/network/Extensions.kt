package aura.projects.core.network

import android.util.Log
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <R> makeApiCall(
    call: suspend () -> ActionResult<R>,
    errorMessage: Int = 4567
) = try {
    call()
} catch (timeOut: SocketTimeoutException){
    ActionResult.Error(CallException(1000,"Check internet connection"))
} catch (timeOut: UnknownHostException){
    ActionResult.Error(CallException(1000,"Check internet connection"))
}catch (e: Exception) {
    Log.i("RESPONSES", "ERROR:${e.message.toString()}")
    ActionResult.Error(CallException<Nothing>(errorMessage = e.message, errorCode = errorMessage))
}

fun <R> analyzeResponse(
    response: Response<R>
): ActionResult<R> {
    return when {
        response.isSuccessful -> {
            ActionResult.Success(response.body())
        }
        response.code() in 400..500 -> {
            try {
                val errorBodyJsonString = response.errorBody()?.string()

                ActionResult.Error(CallException(response.code(), errorBodyJsonString))
            }catch (e: Exception){
                ActionResult.Error(CallException(response.code(), e.message))
            }
        }
        else -> {
            ActionResult.Error(CallException(response.code(),response.message()))
        }
    }
}