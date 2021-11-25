package aura.projects.core.network

class CallException(private val errorCode: Int? = null, private val errorMessage: String? = null) : Throwable(errorMessage.toString()){

}