package com.example.imagecloudfirebase

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.imagecloudfirebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var storageRef : StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var imageuri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initvars()
        registerclickevents()
    }

    private fun registerclickevents() {
        binding.btnupload.setOnClickListener {
            uploadimage()

        }
        binding.btnretrive.setOnClickListener {
            startActivity(Intent(this,ImageActivity::class.java))
        }
        binding.imageView.setOnClickListener{
            resultLauncher.launch("image/*")

        }
    }

    private fun uploadimage() {
        binding.progressBar2.visibility = View.VISIBLE
        storageRef = storageRef.child(System.currentTimeMillis().toString())
        imageuri?.let {
            storageRef.putFile(it).addOnCompleteListener { task->
              if (task.isSuccessful){

                  storageRef.downloadUrl.addOnSuccessListener { uri ->
                     val map = HashMap<String , Any>()
                      map["pic"] = uri.toString()

                      firebaseFirestore.collection("images").add(map).addOnCompleteListener {firestoreTask ->
                          if (firestoreTask.isSuccessful){
                              Toast.makeText(this,"uploaded succesfully", Toast.LENGTH_SHORT).show()


                          }else{
                              binding.progressBar2.visibility = View.GONE

                              Toast.makeText(this,firestoreTask.exception?.message, Toast.LENGTH_SHORT).show()
                          }

                          binding.imageView.setImageResource(R.drawable.ic_launcher_background)
                      }
                  }

              }else{
                  Toast.makeText(this , task.exception?.message , Toast.LENGTH_SHORT).show()
              }
            }
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()){

        imageuri = it
        binding.imageView.setImageURI(it)

    }


    private fun initvars(){

        storageRef = FirebaseStorage.getInstance().reference.child("images")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

}