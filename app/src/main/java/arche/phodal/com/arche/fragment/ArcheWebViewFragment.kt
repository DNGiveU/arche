package arche.phodal.com.arche.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import arche.phodal.com.arche.R
import com.wang.avi.AVLoadingIndicatorView

class ArcheWebViewFragment : Fragment() {
    private var mWebView: WebView? = null
    private var avi: AVLoadingIndicatorView? = null

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_webview, container, false)

        avi = view?.findViewById(R.id.avi)
        mWebView = view?.findViewById(R.id.webview)

        mWebView!!.loadUrl("file:///android_asset/www/index.html")

        val webSettings = mWebView!!.settings
        webSettings.javaScriptEnabled = true
        mWebView!!.webViewClient = WebViewClient()

        setLoadingProgress()
        return view
    }

    private fun setLoadingProgress() {
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                avi!!.show()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                avi!!.hide()
                super.onPageFinished(view, url)
            }
        }
    }
}