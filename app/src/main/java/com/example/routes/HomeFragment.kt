package com.example.routes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.routes.data.models.Post
import com.example.routes.data.remote.PostAPI
import com.example.routes.data.remote.Rede
import com.example.routes.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Rede.getRetrofitInstace("https://jsonplaceholder.typicode.com/")
        val endpointPost = retrofit.create(PostAPI::class.java)

        binding.button.setOnClickListener{
            if(binding.editTextTextPersonName.text.isNotEmpty()){
                val chamada = endpointPost.getPostInfo(binding.editTextTextPersonName.text.toString().toInt())

                chamada.enqueue(object : Callback<Post>{

                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        response.body()?.let { id ->
                            binding.textView.text = id.toString()
                        }
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.e("IMPACTA",t.printStackTrace().toString())
                    }

                })
            }
        }
    }

}