package com.gfdellatin.testing.model

import com.gfdellatin.core.domain.model.Comic

class ComicFactory {

    fun create(comic: FakeComic) = when (comic) {
        FakeComic.FakeComic1 -> Comic(
            2211506,
            "http://fakecomicurl.jpeg"
        )
    }

    sealed class FakeComic {
        object FakeComic1 : FakeComic()
    }
}