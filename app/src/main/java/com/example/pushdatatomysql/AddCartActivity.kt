package com.example.pushdatatomysql

import android.R.color.holo_green_dark
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class AddCartActivity : AppCompatActivity() {

    var myDb: DatabaseHelper? = null


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cart)
        myDb = DatabaseHelper(this)


        val CameraAddcart = findViewById<TextView>(R.id.cameraaddcart)
        val BreshAddcart = findViewById<TextView>(R.id.breshaddcart)
        val ToyaAddcart = findViewById<TextView>(R.id.toyaddcart)


        val Button = findViewById<Button>(R.id.cart)

        Button.setOnClickListener {

            val intent = Intent(this@AddCartActivity,MainActivity::class.java)

            startActivity(intent)
        }

        CameraAddcart.setOnClickListener {

            var itemname:String = "camera"
            var itemcount:Int =1
            var itemamount:Int = 20
            callInsertMethod(itemname,itemcount,itemamount)
            CameraAddcart.setText("Added")

        }

        BreshAddcart.setOnClickListener {

            callInsertMethod("bresh",1,10)
            BreshAddcart.setText("Added")

        }

        ToyaAddcart.setOnClickListener {

            callInsertMethod("toy",1,50)
            ToyaAddcart.setText("Added")

        }
    }


    fun callInsertMethod(itemname: String, itemcount: Int, itemamount: Int)
    {
        val isInserted = myDb!!.insertData(itemname,itemcount,itemamount)

        if (isInserted == true)
//            Toast.makeText(this@AddCartActivity, "$itemname data inserter", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this@AddCartActivity, "$itemname data not inserter", Toast.LENGTH_SHORT).show()


    }
}