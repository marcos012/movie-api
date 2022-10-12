package com.marcos012.movies.handlers

class InvalidOperationException : RuntimeException {
    constructor() : super()
    constructor(mensagem: String) : super(mensagem)
}

