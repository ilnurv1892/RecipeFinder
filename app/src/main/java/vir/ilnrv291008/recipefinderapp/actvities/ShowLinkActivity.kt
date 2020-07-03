package vir.ilnrv291008.recipefinderapp.actvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_show_link.*
import vir.ilnrv291008.recipefinderapp.R

class ShowLinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_link)

        var extra = intent.extras

        if (extra != null) {
            var link = extra.get("link")

            webViewId.loadUrl(link.toString())
        }

    }
}