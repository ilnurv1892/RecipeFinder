package vir.ilnrv291008.recipefinderapp.actvities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import vir.ilnrv291008.recipefinderapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        searchBtn.setOnClickListener {
            var intent = Intent(this, RecipeList::class.java)
            var ingredients = ingredientsEdt.text.toString()
            var searchTerm = searchTermEdt.text.toString()
            intent.putExtra("ingredients",ingredients)
            intent.putExtra("searchTerm",searchTerm)

            startActivity(intent)

        }

    }
}