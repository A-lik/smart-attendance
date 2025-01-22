package edu.iitu.smartattendance.presentation.common.mvi

import kotlinx.coroutines.CoroutineScope

sealed interface StateMachine {
    interface State : StateMachine
    interface Event : StateMachine
    interface Command : StateMachine

    interface CommandExecutor<C : Command, E : Event> : StateMachine {
        var scope: CoroutineScope?
        var eventDispatcher: ((E) -> Unit)?

        abstract suspend fun execute(command: C): E?
        fun setup(scope: CoroutineScope, eventDispatcher: (E) -> Unit)
        fun clear()
    }

    interface Reducer<S, E, C> {
        fun reduce(state: S, event: E): Transition<S, C>
    }
}

typealias Transition<S, C> = Pair<S, Set<C>>

fun <S : StateMachine.State, C : StateMachine.Command> S.noTransition() =
    Transition(this, emptySet<C>())

//internal fun <C : StateMachine.Command, S : StateMachine.State> C.toTransition(state: S): Transition<S, C> =
//    state to setOf(this)
//
//internal fun <C : StateMachine.Command, S : StateMachine.State> S.toTransition(commands: Set<C>): Transition<S, C> =
//    this to commands

fun <C : StateMachine.Command, S : StateMachine.State> S.toTransition(command: C? = null): Transition<S, C> {
    val commands = command?.let { setOf(it) } ?: emptySet()
    return this to commands
}