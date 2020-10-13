package chayapam.s.moviefinder

import com.google.gson.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type


class Deserilizer<T> : JsonDeserializer<MovieList> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MovieList
    {
        val moviesList = json!!.asJsonObject["movies"]



        return Gson().fromJson(moviesList, MovieList::class.java)

    }


    fun getKeyNamesFromJsonObject(jsonObject: JsonObject): List<String>? {
        val namesList: MutableList<String> = ArrayList()
        try {
            val obj = JSONObject(jsonObject.toString())
            val stringIterator = obj.keys()
            while (stringIterator.hasNext()) {
                namesList.add(stringIterator.next())
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return namesList
    }
}
