package edu.iitu.smartattendance.presentation.common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseStore<S : StateMachine.State, E : StateMachine.Event, C : StateMachine.Command>(
    private val config: Config<S, C>,
    private val reducer: StateMachine.Reducer<S, E, C>,
    private val executor: StateMachine.CommandExecutor<C, E>? = null
) : ViewModel() {
    data class Config<S, C>(
        val state: S,
        val commands: Set<C> = emptySet()
    )

    private val _state = MutableStateFlow(config.state)
    val state = _state.asStateFlow()
    private val _events = MutableSharedFlow<E>(replay = 1)
    private val _commands = MutableSharedFlow<C>(replay = 1)
    private val commands = _commands.asSharedFlow()

    fun dispatchEvent(event: E) =
        _events.tryEmit(event)

    private fun dispatchCommands(commands: Set<C>) =
        commands.forEach { _commands.tryEmit(it) }

    private fun setState(state: S) = _state.update { state }

    protected fun update() {
        setupExecutor()
        viewModelScope.launch {
            _events.collect { event ->
                val (newState, commands) = reducer.reduce(state.value, event)
                setState(newState)
                dispatchCommands(commands)
            }
        }
        dispatchCommands(config.commands)
    }

    private fun setupExecutor() {
        executor?.setup(viewModelScope, ::dispatchEvent)
        execute()
    }

    private fun execute() {
        if (executor == null) return

        viewModelScope.launch {
            commands.collect { command ->
                val event = executor.execute(command)
                event?.let {
                    dispatchEvent(event)
                }
            }
        }
    }

    override fun onCleared() {
        executor?.clear()
        super.onCleared()
    }
}