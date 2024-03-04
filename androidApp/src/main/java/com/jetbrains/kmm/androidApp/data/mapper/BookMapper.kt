package com.jetbrains.kmm.androidApp.data.mapper

import com.jetbrains.kmm.androidApp.data.dto.BookDto
import com.jetbrains.kmm.androidApp.domain.model.BookModel

internal fun BookDto.toBookModel(): BookModel {
    return BookModel(
        id = this.id,
        image = this.image,
        title = this.title,
        description = this.description,
        authorName = this.authorName,
    )
}