package com.example.testapp.mytestapp.utils

import android.os.Handler
import android.widget.ImageView
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageLoader {

    private var executorService: ExecutorService  = Executors.newFixedThreadPool(5)

     //handler to display images in UI thread
	 internal var handler = Handler()

    fun loadImage(url: String?, imageView: ImageView) {
        if (url != null && url.isNotEmpty()) {
            executorService.submit(ImageLoaderRunner(url, imageView))
        }
    }
	
	//to check internet connection
	/*
	
	val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
				val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
				val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
	
	*/
	

    internal inner class ImageLoaderRunner(private var url: String, private var imageView: ImageView) : Runnable {

        override fun run() {
            try {
							
                val bmp = getBitmap(url)
                val bd = BitmapDisplayer(bmp, imageView)
                handler.post(bd)
            } catch (th: Throwable) {
                th.printStackTrace()
            }
        }
    }

	//downloading image
    private fun getBitmap(url: String): Bitmap? {

        try {
            val imageUrl = URL(url)
            val conn = imageUrl.openConnection() as HttpURLConnection
            conn.connectTimeout = 30000
            conn.readTimeout = 30000
            conn.instanceFollowRedirects = true

            try {
                return BitmapFactory.decodeStream(conn.inputStream)
            }
            finally {
                conn.disconnect()
            }
        } catch (ex: Throwable) {
            ex.printStackTrace()
            return null
        }

    }

    internal inner class BitmapDisplayer(private var bitmap: Bitmap?, private var imageView: ImageView) : Runnable {
        override fun run() {
            if (bitmap != null)
                imageView.setImageBitmap(bitmap)
        }
    }
}