package alektas.arch_base.models

sealed interface Result<out S : Any, out E : Any> {

    object Empty : Result<Nothing, Nothing>

    data class Success<S : Any>(val data: S) : Result<S, Nothing>

    data class Error<E : Any>(val data: E) : Result<Nothing, E>

}
