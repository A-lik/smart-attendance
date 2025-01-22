package edu.iitu.smartattendance.presentation.common.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import java.lang.ref.WeakReference

abstract class BaseExecutor<C : StateMachine.Command, E : StateMachine.Event> :
    StateMachine.CommandExecutor<C, E> {

    private var scopeRef: WeakReference<CoroutineScope>? = null
    override var eventDispatcher: ((E) -> Unit)? = null

    override var scope: CoroutineScope?
        get() = scopeRef?.get()
        set(value) {
            scopeRef = WeakReference(value)
        }

    override fun setup(
        scope: CoroutineScope,
        eventDispatcher: (E) -> Unit,
    ) {
        this.scope = scope
        this.eventDispatcher = eventDispatcher
    }

    override fun clear() {
        scope?.cancel()
        scope = null
        eventDispatcher = null
    }
}