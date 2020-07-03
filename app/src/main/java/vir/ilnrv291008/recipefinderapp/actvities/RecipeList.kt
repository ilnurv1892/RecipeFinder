package vir.ilnrv291008.recipefinderapp.actvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_recipe_list.*
import org.json.JSONException
import org.json.JSONObject
import vir.ilnrv291008.recipefinderapp.R
import vir.ilnrv291008.recipefinderapp.data.RecipeListAdapter
import vir.ilnrv291008.recipefinderapp.model.LEFT_LINK
import vir.ilnrv291008.recipefinderapp.model.QUERY
import vir.ilnrv291008.recipefinderapp.model.Recipe

class RecipeList : AppCompatActivity() {

    var volleyReqest: RequestQueue? = null
    var recipeList: ArrayList<Recipe>? = null
    var urlString = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"
    var recipeListAdapter: RecipeListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        recipeList = ArrayList<Recipe>()
        volleyReqest = Volley.newRequestQueue(this)

        var url: String? = null
        var extras = intent.extras
        val ingredients  = extras!!.getString("ingredients")

        val searchTerm = extras.get("searchTerm")

        if (extras != null && ingredients!! != "" && searchTerm!! != "") {

            var tempUrl = LEFT_LINK + ingredients + QUERY + searchTerm

            urlString = tempUrl
        } else {
            urlString = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"
        }

        getRecipe(urlString)
    }


    fun getRecipe(url: String) {
        val recipeReques = JsonObjectRequest(
            Request.Method.GET,
            url,
            Response.Listener { response: JSONObject ->

                try {
                    val resultArray = response.getJSONArray("results")
                    for (i in 0 until resultArray.length()) {
                        var recipeObj = resultArray.getJSONObject(i)


                        var title = recipeObj.getString("title")
                        var link = recipeObj.getString("href")
                        var thumbnail = recipeObj.getString("thumbnail")
                        var ingredients = recipeObj.getString("ingredients")

                        var recipe = Recipe()

                        recipe.title = title
                        recipe.link = link
                        recipe.thumbnail = thumbnail
                        recipe.ingredients = ingredients


                        Log.d("resulst ", title)


                        recipeList!!.add(recipe)

                    }

                    recipeListAdapter!!.notifyDataSetChanged()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }


            },
            Response.ErrorListener { error: VolleyError? ->

                try {
                    Log.d("Error: ", error.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            })




        volleyReqest!!.add(recipeReques)

        recipeListAdapter = RecipeListAdapter(recipeList!!, this)
        layoutManager = LinearLayoutManager(this)

        recyclerViewId.layoutManager = layoutManager
        recyclerViewId.adapter = recipeListAdapter
    }
}