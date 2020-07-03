package vir.ilnrv291008.recipefinderapp.data

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_row.view.*
import vir.ilnrv291008.recipefinderapp.R
import vir.ilnrv291008.recipefinderapp.actvities.ShowLinkActivity
import vir.ilnrv291008.recipefinderapp.model.Recipe

class RecipeListAdapter(
    private val list: ArrayList<Recipe>,
    val context: Context

) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.recipeTitle
        var ingredients: TextView = itemView.recipeIngredients
        var link: Button = itemView.linkButton
        var thumb: ImageView = itemView.thumbnail


        fun bindView(recipe: Recipe) {
            title.text = recipe.title
            ingredients.text = "Ingredients: ${recipe.ingredients}"

            link.setOnClickListener {
                var intent = Intent(context, ShowLinkActivity::class.java)
                intent.putExtra("link",recipe.link.toString())
                context.startActivity(intent)
            }

            if (!TextUtils.isEmpty(recipe.thumbnail)) {
                Picasso.get().load(recipe.thumbnail)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.ic_menu_report_image).into(thumb)

            } else {
                Picasso.get().load(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher).into(thumb)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }
}