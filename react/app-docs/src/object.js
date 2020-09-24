// 值类型(基本类型)：字符串（String）、数字(Number)、布尔(Boolean)、对空（Null）、未定义（Undefined）、Symbol。
// 引用数据类型：对象(Object)、数组(Array)、函数(Function)。
let objectTest = {
    testPerson: function () {
        testPerson()
    },
    testTypeOf: function () {
        testTypeOf()
    },
    isArray: function (array) {
        return isArray(array)
    },
    isDete: function (date) {
        return isDate(date)
    },
    convert: function (x) {
        convert(x)
    },
    printDate: function () {
        printDate()
    },
    compareTest: function () {
        compareTest()
    },
    ajaxTest: function () {
        ajaxTest()
    },
}
export default objectTest

const person = {
    firstName: "John",
    lastName: "Doe",
    id: 5566,

    fullName: function () {
        return this.firstName + " " + this.lastName;
    }
};

function testPerson() {
    console.log(person.firstName)
    // 加括号输出函数执行结果
    console.log(person.fullName())
    // 不加括号输出函数表达式：function() { return this.firstName + " " + this.lastName; }
    console.log(person.fullName)
}

function testTypeOf() {
    console.log(typeof "John")
    console.log(typeof 3.14)
    console.log(typeof false)
    console.log(typeof [1, 2, 3, 4])
    console.log(typeof {name: "John", age: 34})
    console.log(typeof null)
    console.log(typeof undefined)
    let person;
    console.log(typeof person)
    console.log(null === undefined)
    console.log(null == undefined)
    console.log(typeof function () {
    })
    // not a number, JavaScript中,NaN是惟一一个和自己也不想等的值.
    // 所以,也就不能使用等号运算符来判断一个值是否是NaN,不过有全局函数isNaN()来干这件事.
    // 它会隐式的将它的参数转换成数字,所以即便参数是个不能转换成数字的字符串,它也会返回true(转换成了NaN)
    console.log(isNaN("xyz"))
    console.log(NaN)
    console.log(Infinity)
    // 用0作除数会产生另外一个特殊值Infinity:
    console.log(3 / 0)
    // 你不能想当然的猜测正无穷大或者负无穷大的计算结果:
    console.log(Infinity + Infinity)
    console.log(Infinity - Infinity)
    console.log(Infinity * Infinity)
    console.log(Infinity / Infinity)
    console.log(typeof NaN)
    console.log(typeof Infinity)
    console.log("John".constructor)
    console.log(false.constructor)
    console.log([1, 2, 3, 4].constructor)
    console.log({name: "John", age: 34}.constructor)
    console.log(new Date().constructor)
    console.log(function () {
    }.constructor)
}

function isArray(array) {
    return array.constructor.toString().indexOf("Array") > -1;
}

function isDate(date) {
    return date.constructor.toString().indexOf("Date") > -1;
}

function convert(x) {
    let out
    out = String(x)         // 将变量 x 转换为字符串并返回
    console.log(out)
    out = String(123)       // 将数字 123 转换为字符串并返回
    console.log(out)
    out = String(100 + 23)  // 将数字表达式转换为字符串并返回
    console.log(out)

    // Number 方法 toString() 也是有同样的效果
    out = x.toString()
    console.log(out)
    out = (123).toString()
    console.log(out)
    out = (100 + 23).toString()
    console.log(out)

    // 将字符串转换为数字
    out = Number("3.14")
    console.log(out)
    out = Number("")
    console.log(out)
    out = Number("  ")
    console.log(out)
    out = Number("99 88")
    console.log(out)
    out = Number("-99 88")
    console.log(out)

    // Operator + 可用于将变量转换为数字：
    let y = "5"
    let z = +y
    console.log(z)
    console.log(typeof z)

    console.log(Number(false))
    console.log(Number(true))
    console.log(Number(new Date()))

    // 自动转换类型
    // 当 JavaScript 尝试操作一个 "错误" 的数据类型时，会自动转换为 "正确" 的数据类型
    console.log(5 + null)
    console.log("5" + null)
    console.log("z" - null)
    console.log("5" + 1)
    console.log("5" - 1)
    console.log("z" - 1)
}

function printDate() {
    const date = new Date()
    console.log(date.getDate())         // 从 Date 对象返回一个月中的某一天 (1 ~ 31)
    console.log(date.getDay())          // 从 Date 对象返回一周中的某一天 (0 ~ 6)
    console.log(date.getFullYear())     // 从 Date 对象以四位数字返回年份
    console.log(date.getHours())        // 返回 Date 对象的小时 (0 ~ 23)
    console.log(date.getMilliseconds()) // 返回 Date 对象的毫秒(0 ~ 999)
    console.log(date.getMinutes())      // 返回 Date 对象的分钟 (0 ~ 59)
    console.log(date.getMonth())        // 从 Date 对象返回月份 (0 ~ 11)
    console.log(date.getSeconds())      // 返回 Date 对象的秒数 (0 ~ 59)
    console.log(date.getTime())         // 返回 1970 年 1 月 1 日至今的毫秒数
    console.log("-----------")
    console.log(date.getUTCHours())
}

function compareTest() {
    let x = 0
    let y = 0
    let z = 0
    if (x = 1) {
        console.log(Boolean(x = -1))
    }
    // 在常规的比较中，数据类型是被忽略的
    x = 10
    y = "10"
    console.log(x == y)
    // 在严格的比较运算中，=== 为恒等计算符，同时检查表达式的值与类型
    // noinspection JSIncompatibleTypesComparison
    console.log(x === y)
    //switch 语句会使用恒等计算符(===)进行比较
    switch (x) {
        case "10":
            alert("World")
            break
        case 10:
            console.log("switch 语句会使用恒等计算符(===)进行比较")
            break
        default:
            throw new Error("IllegalStateException")
    }

    // JavaScript 中的所有数据都是以 64 位浮点型数据(float) 来存储。
    // 所有的编程语言，包括 JavaScript，对浮点型数据的精确度都很难确定：
    x = 0.1
    y = 0.2
    z = x + y
    console.log(z === 0.3)
    console.log(z)
    z = (x * 10 + y * 10) / 10
    console.log(z === 0.3)
    console.log(z)

    if (x === 19) ;
    {
        console.log("code block")
    }

    // 数组最后一个值的后面添加逗号虽然语法没有问题，但是在不同的浏览器可能得到不同的结果。
    let colors = [5, 6, 7,]; //这样数组的长度可能为3 也可能为4
    console.log("数组长度: " + colors.length)
}

function ajaxTest() {
    setTimeout(() => {
        console.log("Set timeout")
    }, 3000)
    console.log("after 1")

    let xhr = new XMLHttpRequest()
    xhr.onload = () => {
        let resp = xhr.response
        console.log(resp)
    }
    xhr.onerror = () => {
        console.log("Requst http error")
    }
    xhr.open("GET", "https://www.runoob.com/try/ajax/ajax_info.txt", true)
    xhr.send()
}

function promiseTest() {

}

function newTimeoutPromise(msg, delay) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {

        })
    })
}
