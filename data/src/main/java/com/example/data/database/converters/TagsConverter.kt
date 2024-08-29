package com.example.data.database.converters

import androidx.room.TypeConverter

class TagsConverter {

    @TypeConverter
    fun fromTags(tags: List<String>): String{
        return tags.toString()
    }

    @TypeConverter
    fun toTags(tagsData: String): List<String>{
        val result = mutableListOf<String>()
        val split = tagsData
            .replace("[", "")
            .replace("]", "")
            .replace("\"","")
            .split(", ")

        for(n in split){
            try {
                result.add(n)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        return result
    }

}