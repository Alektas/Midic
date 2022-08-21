package alektas.arch_base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected fun onLoading(inProgress: Boolean) {}

    protected fun onError(exception: Exception) {}

    protected fun launchSafely(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onLoading: (Boolean) -> Unit = ::onLoading,
        onError: (Exception) -> Unit = ::onError,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(context, start) {
            try {
                onLoading(true)
                block()
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                e.printStackTrace()
                onError(e)
            } finally {
                onLoading(false)
            }
        }
    }

}
