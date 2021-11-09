package com.example.pushdatatomysql


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.database.Cursor
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener


class MainActivity : AppCompatActivity() {
    var myDb: DatabaseHelper? = null

    var itemName:String = "camera"
    var itemCount:Int = 1
    var totalAmount:Int = 20
    var incrementCount:Int = 1
    var decrementCount:Int = 1


    @SuppressLint("Range", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDb = DatabaseHelper(this)

        var ItemName = findViewById<TextView>(R.id.productname)
        var ItemCountText = findViewById<TextView>(R.id.item_count_text_display)
        var IncrementCount = findViewById<TextView>(R.id.increase_count)
        var DecrementCount = findViewById<TextView>(R.id.decrease_count)
        var TotalAmount = findViewById<TextView>(R.id.total_amount)

        var ItemName2 = findViewById<TextView>(R.id.productname2)
        var ItemCount2 = findViewById<TextView>(R.id.item_count2)
        var IncrementCount2 = findViewById<TextView>(R.id.increase_count2)
        var DecrementCount2 = findViewById<TextView>(R.id.decrease_count2)

        var ItemName3 = findViewById<TextView>(R.id.productname3)
        var ItemCount3 = findViewById<TextView>(R.id.item_count3)
        var IncrementCount3 = findViewById<TextView>(R.id.increase_count3)
        var DecrementCount3 = findViewById<TextView>(R.id.decrease_count3)

        var p1 = findViewById<LinearLayout>(R.id.p1)
        var p2 = findViewById<LinearLayout>(R.id.p2)
        var p3 = findViewById<LinearLayout>(R.id.p3)

        val savedAmount = findViewById<TextView>(R.id.saved_amount)
        val finalAmount = findViewById<TextView>(R.id.final_amount)
        val DiscountPrice = findViewById<EditText>(R.id.disjoint_edittext)

        var discount = "null"
        var Discount = 0.1

        val res: Cursor? = myDb!!.getAllData()
        var TOtalAmount:Int =0
        var TOtalAmountC:Int = 0
        var TOtalAmountB:Int = 0

        if (res != null) {
            while (res.moveToNext()) {
                if ( res.getString(1) == "camera")
                {
                    p1.visibility = View.VISIBLE
                    ItemName.setText(res.getString(1)+" Rs:20")
                    ItemCountText.setText(res.getString(2))
                    var itemcount = res.getString(2)
                    var totalprice = res.getString(3)
                    TOtalAmount = TOtalAmount+ itemcount.toInt()*20
                }
                if ( res.getString(1) == "bresh")
                {
                    p2.visibility = View.VISIBLE
                    ItemName2.setText(res.getString(1)+" Rs:10")
                    ItemCount2.setText(res.getString(2))
                    var itemcount = res.getString(2)
                    var totalprice = res.getString(3)
                    TOtalAmount = TOtalAmount+ itemcount.toInt()*10
                }
                if ( res.getString(1) == "toy")
                {
                    p3.visibility = View.VISIBLE
                    ItemName3.setText(res.getString(1)+" Rs:50")
                    ItemCount3.setText(res.getString(2))
                    var itemcount = res.getString(2)
                    var totalprice = res.getString(3)
                    TOtalAmount = TOtalAmount+ itemcount.toInt()*50

                }

            }

            TotalAmount.setText("Total Amount Rs: "+TOtalAmount.toString())

            if (TOtalAmount>=500){
                savedAmount.visibility = View.VISIBLE
                finalAmount.visibility = View.VISIBLE

                var savedamount = TOtalAmount*Discount
                savedAmount.setTextColor(Color.parseColor("#00FF00"))
                savedAmount.setText("Saved amount Rs: "+savedamount.toString())

                var finalamount = TOtalAmount-savedamount
                finalAmount.setText("Final Total Amount Rs: "+finalamount.toString())

            }

            else{
                savedAmount.visibility = View.GONE
                finalAmount.visibility = View.GONE
            }
        }

        //camera increment
        IncrementCount.setOnClickListener {

            var itemcount:String? = null
            val res: Cursor? = myDb!!.getAllData()

            if (res != null) {
                while (res.moveToNext()) {
                    if(res.getString(1)=="camera")
                    {
                        itemcount = res.getString(2)
                        ItemCountText.setText((itemcount.toInt()+1).toString())
                        TOtalAmountC=(itemcount.toInt()+1)*20
                    }
                }
            }

            var id: Int =1
            var ITENCount :Int =(itemcount!!.toInt()+1)
            var ITEMPrice :Int = TOtalAmountC

            UpdateData(id,"camera",ITENCount,ITEMPrice)
            CallrefreshActivityMethod()

        }

        //camera decrement
        DecrementCount.setOnClickListener {

            var itemcountd:String? = null
            val res: Cursor? = myDb!!.getAllData()

            if (res != null) {
                while (res.moveToNext()) {
                    if(res.getString(1)=="camera")
                    {
                        itemcountd = res.getString(2)
                        ItemCountText.setText((itemcountd.toInt()-1).toString())
                        TOtalAmountB=(itemcountd.toInt()-1)*20
                    }

                }
            }

            var id: Int =1
            var ITENCount :Int =(itemcountd!!.toInt()-1)
            var ITEMPrice :Int = TOtalAmountB

            UpdateData(id,"camera",ITENCount,ITEMPrice)
            CallrefreshActivityMethod()

        }

        //bresh increment
        IncrementCount2.setOnClickListener {

            var itemcountb:String? = null
            val res: Cursor? = myDb!!.getAllData()

            if (res != null) {
                while (res.moveToNext()) {
                    if(res.getString(1)=="bresh")
                    {
                        itemcountb = res.getString(2)
                        ItemCount2.setText((itemcountb.toInt()+1).toString())
                        TOtalAmount=(itemcountb.toInt()+1)*10
                    }
                }
            }

            var id: Int =2
            var ITENCount :Int =(itemcountb!!.toInt()+1)
            var ITEMPrice :Int = TOtalAmount

            UpdateData(id,"bresh",ITENCount,ITEMPrice)
            CallrefreshActivityMethod()

        }
        //bresh decrement
        DecrementCount2.setOnClickListener {

            var itemcountdd:String? = null
            val res: Cursor? = myDb!!.getAllData()

            if (res != null) {
                while (res.moveToNext()) {
                    if(res.getString(1)=="bresh")
                    {
                        itemcountdd = res.getString(2)
                        ItemCount2.setText((itemcountdd.toInt()-1).toString())
                        TOtalAmount=(itemcountdd.toInt()-1)*10

                    }
                }
            }

            var id: Int =2
            var ITENCount :Int =(itemcountdd!!.toInt()-1)
            var ITEMPrice :Int = TOtalAmount

            UpdateData(id,"bresh",ITENCount,ITEMPrice)
            CallrefreshActivityMethod()
        }

        //toy increment
        IncrementCount3.setOnClickListener {

            var itemcountbb:String? = null
            val res: Cursor? = myDb!!.getAllData()

            if (res != null) {
                while (res.moveToNext()) {
                    if(res.getString(1)=="toy")
                    {
                        itemcountbb = res.getString(2)
                        ItemCount3.setText((itemcountbb.toInt()+1).toString())
                        TOtalAmount=(itemcountbb.toInt()+1)*50
                    }
                }
            }
            var id: Int =3
            var ITENCount :Int =(itemcountbb!!.toInt()+1)
            var ITEMPrice :Int = TOtalAmount

            UpdateData(id,"toy",ITENCount,ITEMPrice)
            CallrefreshActivityMethod()

        }
        //toy decrement
        DecrementCount3.setOnClickListener {

            var itemcountddd:String? = null
            val res: Cursor? = myDb!!.getAllData()

            if (res != null) {
                while (res.moveToNext()) {
                    if(res.getString(1)=="toy")
                    {
                        itemcountddd = res.getString(2)
                        ItemCount3.setText((itemcountddd.toInt()-1).toString())
                        TOtalAmount=(itemcountddd.toInt()-1)*10
                    }
                }
            }
            var id: Int =3
            var ITENCount :Int =(itemcountddd!!.toInt()-1)
            var ITEMPrice :Int = TOtalAmount

            UpdateData(id,"toy",ITENCount,ITEMPrice)
            CallrefreshActivityMethod()
        }
    }

    private fun CallrefreshActivityMethod() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    fun UpdateData(id: Int, itemname: String, itemcount: Int, totalamount: Int) {

        val isUpdate = myDb!!.updateData(id,itemname,itemcount,totalamount)
        if (isUpdate == true) {
//            Toast.makeText(this@MainActivity, itemname.toString()+" "+itemcount+" "+totalamount, Toast.LENGTH_SHORT).show()
//            Toast.makeText(this@MainActivity, "Data Update", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this@MainActivity, "Data not Updated", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun isInserted(itemname: String, itemcount: Int, totalamount: Int) {

        val isInserted = myDb!!.insertData(itemname,itemcount,totalamount)
//        Toast.makeText(this@MainActivity, itemname.toString()+" "+itemcount+" "+totalamount, Toast.LENGTH_SHORT).show()
        if (isInserted == true) {
//            Toast.makeText(this@MainActivity, "Data INSERTED", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Data not INSERTED", Toast.LENGTH_SHORT)
                .show()
        }
    }

}
