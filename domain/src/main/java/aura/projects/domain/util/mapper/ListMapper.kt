package aura.projects.domain.util.mapper

class ListMapper<T, R>(private val mapper: Mapper<T, R>): Mapper<List<T>?, List<R>>() {

    override fun invoke(response: List<T>?): List<R> {
        val list = mutableListOf<R>()
        response?.forEach {
            mapper(it)
        }
        return list.toList()
    }

}