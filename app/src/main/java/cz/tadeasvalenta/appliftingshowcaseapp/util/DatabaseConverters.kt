package cz.tadeasvalenta.appliftingshowcaseapp.util

import androidx.room.TypeConverter

class DatabaseConverters {

    @TypeConverter
    fun fromList(list: List<String>): String {
        var string = ""
        for (item in list) {
            string += item
            if (list.last() != item) {
                string += ","
            }
        }
        return string
    }

    @TypeConverter
    fun toList(listInString: String): List<String> {
        return listInString.split(",")
    }
}
