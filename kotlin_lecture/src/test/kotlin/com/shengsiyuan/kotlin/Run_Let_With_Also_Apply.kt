package com.shengsiyuan.kotlin

/**
 * run
 * 可以將 run 想像成一個獨立出來的 Scope, run 會把最後一行的東西回傳或是帶到下一個 chain
 */
fun run1() {
    val whatsMyName = "Francis"
    run {
        val whatsMyName = "Ajax"
        println("Call my name! $whatsMyName")
    }
    println("What's my name? $whatsMyName")
}

open class Telephone : Parcelable() {
    var whoCallMe: String = ""

    var fromWhere: String = ""

    fun callMe(myName: String) {
        println("$whoCallMe ! Call me $myName !!")
    }
}

/**
 * run 還能將最後一行的東西回傳，或傳給下一個 chain ，也就是說能這麼寫：
 */
fun run2() {
    run {
        val telephone = Telephone()
        telephone.whoCallMe = "English"
        telephone    // <--  telephone 被帶到下一個 Chain
    }.callMe("Softest part of heart")    // <-- 這裡可以執行 `Telephone` Class 的方法

    println("=========================")

    val wowCall = run {
        val telephone = Telephone()
        telephone.fromWhere = "Sagittarius"
        telephone.whoCallMe = "Still Unknown"
        telephone  // <-- telephone 回傳，wowCall 型態成為 Telephone
    }
    println("WOW, This signal is from ${wowCall.fromWhere}")
}

class GoodSmartPhone : Telephone() {
    var hello: Boolean = false

    fun setCleanSystemInterface(b: Boolean) {
    }

    fun setGreatBatteryLife(b: Boolean) {
    }

    fun setGreatBuildQuality(b: Boolean) {
    }

    fun setNouch(b: Boolean) {
        hello = b
    }
}

/**
 * with 一般常常作為初始化時使用， with(T) 之中的傳入值可以以 this (稱作 identifier) 在 scope 中取用，不用打出 this也沒關係。
 * 雖然， with 也會將最後一行回傳，但目前看起來大部分還是只用它來做初始化。透過 with()很明確知道是為了括弧中的變數進行設定。
 *
 * 但很多使用狀況變數可能是可為空的變數，如此一來 with的 scope 中就必須要宣告 「?」或「!!」來取用該物件的方法 (Method)。
 */
fun with1(good: GoodSmartPhone?) {
    val greatSmartphone = GoodSmartPhone()
    with(greatSmartphone) {
        this.setCleanSystemInterface(true)

        // `this` is not necessary
        setGreatBatteryLife(true)
        setGreatBuildQuality(true)
        setNouch(true)
        "123"
    }

    val str = with(good) {
        println("setCleanSystemInterface: ")
        this?.setCleanSystemInterface(true)
        println("setNouch: ")
        this?.setNouch(true)
        "123"
    }
    println(good?.hello)
    println(str)
}

/**
 * T.run
 * 什⋯ 什麼嘛，多了 T 是怎麼一回事！
 * 這些 function 的使用方式，需要接在一個變數後面才行。像是 someVariable.run { /* do something */ }，
 * 包含 T.run 下面四個 let also apply 都屬於這種 extension function。
 * 因為 run有兩種用法，這裡為了避免混淆而將 T 寫出來。
 *
 * T.run 也能像 with 一樣來做初始化，而且 extension function
 * 有個好處是可以在使用時就進行 「?」 或 「!!」 的宣告。另外，T 能夠以 this 的形式在 scope 內取用。
 * 像是上面的範例，如果用 T.run來 做初始化，就會是：
 *
 * 當然如果傳進來的變數是空值， T.run{} 內的程式碼就根本不會執行了
 * 除此之外，T.run 和 run 的特性完全一樣。可以將最後一行的東西回傳，或是傳給下一個 chain
 */
open class TPhone : Telephone() {

    fun tRun(goodSmartPhone: GoodSmartPhone?) {
        goodSmartPhone?.run {
            this.setCleanSystemInterface(true)
            // `this` is not necessary
            setGreatBatteryLife(true)
            setGreatBuildQuality(true)
            setNouch(true)

            /*
             * 但實作上 T.run 有可能需要取用外層變數或方法，但 this 已經被變數 T 佔用。例如，取得 Activity 則要這樣處理
             */
            this@TPhone.fromWhere
        }
    }

    /*
     * 有沒有其他的做法，可以讓 identifier 不是 this呢？
     * 這就需要來介紹下一位： let
     */
}

/**
 * let
 * 又或者可以寫成 T.let，也是一個 extension function。
 * T 在 scope 內則是用 it 來存取而不是 this。也可以依照需求改成其他的名字，增加可讀性。
 * 與 run 相同，會將最後一行帶到下一個 chain 或是回傳。
 */

class TreasureBox {
    private val passwd = "123456"
    private val treasure = "You've got a Windows install USB"

    fun open(key: String?): String {
        return key?.let {
            // `it` is the key String.
            // `this` is TreasureBox.

            var treasure = "error"
            if (it == passwd) {
                treasure = this.treasure
            }
            treasure
        } ?: "error"
    }
}

/**
 * 希望能自訂 identifier 時，或是希望 this 可以存取到上層內容時，建議使用 let
 *
 * identifier -> 身份标识，例如 this super，他们后面跟上 @ 符号； 或者自定义类似这种身份标识
 */
fun let1() {
    val treasureBox = TreasureBox()
    var open = treasureBox.open(null)
    println(open)
    open = treasureBox.open("admin")
    println(open)
    open = treasureBox.open("123456")
    println(open)
}

/**
 * also
 * 也可以寫作 T.also
 * 剩下的 also和 apply 決大部分也是使用於初始化物件。前文提到：這幾種 Standard Library Function 其實可以互相替換，選擇合適的場景使用即可。
 * 而它們與上面的 run 與 let的不同之處在於： run與 let 會將最後一行傳給下個 Chain 或是回傳，物件類型依最後一行而定； also和 apply 則是將「自己 (this)」回傳或傳入下個 chain。
 * 有點像是 builder pattern ，做完一次設定後又將自己回傳回去。另外， also在 scope 內可以透過 it 來存取 T本身。
 */

fun also1() {
    val goodSmartPhone = GoodSmartPhone().also {
        it.setCleanSystemInterface(true)
    }.also {
        it.setGreatBatteryLife(true)
    }.also {
        it.setGreatBuildQuality(true)
    }.also {
        it.setNouch(true)
    }
    println(goodSmartPhone)
}

/**
 * apply
 * 也可以寫作 T.apply 。
 * apply與 also 有 87 分像，不同的地方是 apply 在 scope 內 T 的存取方式是 this ，其他都與 also 一樣。
 *
 * 這裡的範例以 Fragment 生成時，需要時做的 newInstance()方法。利用 apply 和 also 在 Kotlin 之中如何改寫：
 */

class ListFragment {
    var arguments: Bundle = Bundle()
}

class Bundle {
    fun putParcelableArrayList(key: String, value: ArrayList<out Parcelable>) {
    }
}

open class Parcelable

class XActivity {
    companion object {
        private const val COFFEE_SHOP_LIST_KEY = "coffee-list-key"

        fun newInstance(coffeeShops: List<Telephone>): ListFragment {
            return ListFragment().apply {
                // `this` is `ListFragment` in apply scope
                arguments = Bundle().also {
                    // `it` is `Bundle` in also scope
                    // `this` is `ListFragment`
                    it.putParcelableArrayList(COFFEE_SHOP_LIST_KEY, coffeeShops as ArrayList<out Parcelable>)
                }
            }
        }

        fun newInstance2(coffeeShops: List<Telephone>): ListFragment {
            return ListFragment().also {
                it.arguments = Bundle().also {
                    it.putParcelableArrayList(COFFEE_SHOP_LIST_KEY, coffeeShops as ArrayList<out Parcelable>)
                }
            }
        }
    }
}

/**
 * Created by andy
 * 2019/1/22.
 * Email: 1239604859@qq.com
 */

fun main() {
    run1()
    run2()

    println("*****************")

    with1(null)

    println("*****************")

    let1()

    also1()
}