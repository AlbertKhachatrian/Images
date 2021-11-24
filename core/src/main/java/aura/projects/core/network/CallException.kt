package aura.projects.core.network

data class CallException<ErrorBody>(
    val errorCode: Int? = null,
    val errorMessage: String? = null
)