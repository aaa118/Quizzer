package com.demo.quiz.db

import androidx.room.TypeConverter
import com.demo.quiz.model.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

class TypeConverters {
    @TypeConverter
    fun fromStringToListOfStrings(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<Results?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringsArrayListToString(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}