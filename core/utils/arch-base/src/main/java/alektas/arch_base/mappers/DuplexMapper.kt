package alektas.arch_base.mappers

interface DuplexMapper<Input, Output> {
    fun mapInput(input: Input): Output
    fun mapOutput(output: Output): Input
}