package linKav20.github.com.core.serialization

import com.google.gson.GsonBuilder

val gson = GsonBuilder().setPrettyPrinting().create()

fun toGson(obj: Object) : String {
    return gson.toJson(obj)
}