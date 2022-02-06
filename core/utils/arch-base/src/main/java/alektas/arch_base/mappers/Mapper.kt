package alektas.arch_base.mappers

interface Mapper<in Input, out Output> {
    fun map(input: Input): Output
}






