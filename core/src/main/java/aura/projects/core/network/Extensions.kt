package aura.projects.core.network

import android.util.Log
import aura.projects.core.network.common.CommonResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
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
    ActionResult.Error(CallException(errorMessage = e.message, errorCode = errorMessage))
}

fun <R> analyzeCommonResponse(
    response: Response<CommonResponse<R>>
):ActionResult<R>{
    return when{
        response.isSuccessful && response.code() == 200 && response.body()?.status == 200 -> {
            ActionResult.Success(response.body()?.data)
        }
        response.code() in 400..500 ->{
            ActionResult.Error(CallException(response.code(), response.message()))
        }
        else -> {
            ActionResult.Error(CallException(response.code(), response.message()))
        }
    }
}

fun <R> analyzeResponse(
    response: Response<R>
): ActionResult<R> {
    return when {
        response.isSuccessful && response.code() == 200-> {
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