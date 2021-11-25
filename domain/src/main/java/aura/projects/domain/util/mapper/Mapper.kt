package aura.projects.domain.util.mapper

abstract class Mapper<T, R> {
    abstract operator fun invoke(response: T): R
}