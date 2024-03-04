package com.jetbrains.kmm.androidApp.data

import com.jetbrains.androidApp.R
import com.jetbrains.kmm.androidApp.data.dto.BookDto

internal object BookMockDataHolder {

    fun getBookings(): List<BookDto> {
        return listOf(
            BookDto(
                id = "1",
                title = "A cinco passos de voce",
                image = R.drawable.cinco_passos_de_voce_livro,
                description = "Stella Grant gosta de estar no controle. Ela parece ser uma adolescente típica, mas em sua rotina há listas de tarefas e inúmeros remédios que ela deve tomar para controlar a fibrose cística, uma doença crônica que impede que seus pulmões funcionem como deveriam.",
                authorName = "Larissa Silva",
            ),
            BookDto(
                id = "2",
                title = "Ela fica com A Garota",
                image = R.drawable.ela_fica_com_a_garota,
                description = "Stella Grant gosta de estar no controle. Ela parece ser uma adolescente típica, mas em sua rotina há listas de tarefas e inúmeros remédios que ela deve tomar para controlar a fibrose cística, uma doença crônica que impede que seus pulmões funcionem como deveriam.",
                authorName = "Michelle Silva",
            ),
            BookDto(
                id = "3",
                title = "Princesa das cinzas",
                image = R.drawable.princesa_das_cinzas,
                description = "Stella Grant gosta de estar no controle. Ela parece ser uma adolescente típica, mas em sua rotina há listas de tarefas e inúmeros remédios que ela deve tomar para controlar a fibrose cística, uma doença crônica que impede que seus pulmões funcionem como deveriam.",
                authorName = "Cleber Silva",
            ),
            BookDto(
                id = "4",
                title = "Princesa das cinzas",
                image = R.drawable.romeu_e_julieta,
                description = "Stella Grant gosta de estar no controle. Ela parece ser uma adolescente típica, mas em sua rotina há listas de tarefas e inúmeros remédios que ela deve tomar para controlar a fibrose cística, uma doença crônica que impede que seus pulmões funcionem como deveriam.",
                authorName = "Beijamim Silva",
            )
        )
    }
}