package edu.iitu.smartattendance.infrastructure.utils

sealed interface Either<out L, out R> {
    data class Left<L>(val value: L) : Either<L, Nothing>
    data class Right<R>(val value: R) : Either<Nothing, R>
}

fun<T> T.toRight() = Either.Right(this)
fun<T> T.toLeft() = Either.Left(this)