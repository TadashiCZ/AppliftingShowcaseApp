package cz.tadeasvalenta.appliftingshowcaseapp.util

import org.junit.Assert.assertEquals
import org.junit.Test

class DatabaseConvertersTest {

    private val testedConverters = DatabaseConverters()

    @Test
    fun testFromList() {
        val exampleList = listOf("id1", "id2", "id3")
        assertEquals(testedConverters.fromList(exampleList), "id1,id2,id3")
    }

    @Test
    fun testToList() {
        val exampleStringList = "id1,id2,id3"
        assertEquals(testedConverters.toList(exampleStringList), listOf("id1", "id2", "id3"))
    }
}
