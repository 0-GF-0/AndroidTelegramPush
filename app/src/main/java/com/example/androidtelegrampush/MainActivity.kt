package com.example.androidtelegrampush

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidtelegrampush.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.push.setOnClickListener{
            push("content")
        }
    }

    private fun push(content: String) {
        thread{
            try {
                val client = OkHttpClient()
                var link = "https://dianbao.vercel.app/send/3AD79BCE07349/" + content
                Log.d("link", "${link}")
                val request = Request.Builder()
                    .url("${link}")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            binding.showResponse.text = response
        }
    }
}