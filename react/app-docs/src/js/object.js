const TAG = "JS-OBJECT:"
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
    promiseTest: function () {
        promiseTest()
    },
    mapTest: function () {
        mapTest()
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
    console.debug(TAG, person.firstName)
    // 加括号输出函数执行结果
    console.debug(TAG, person.fullName())
    // 不加括号输出函数表达式：function() { return this.firstName + " " + this.lastName; }
    console.debug(TAG, person.fullName)
}

function testTypeOf() {
    console.debug(TAG, typeof "John")
    console.debug(TAG, typeof 3.14)
    console.debug(TAG, typeof false)
    console.debug(TAG, typeof [1, 2, 3, 4])
    console.debug(TAG, typeof {name: "John", age: 34})
    console.debug(TAG, typeof null)
    console.debug(TAG, typeof undefined)
    let person;
    console.debug(TAG, typeof person)
    console.debug(TAG, null === undefined)
    console.debug(TAG, null == undefined)
    console.debug(TAG, typeof function () {
    })
    // not a number, JavaScript中,NaN是惟一一个和自己也不想等的值.
    // 所以,也就不能使用等号运算符来判断一个值是否是NaN,不过有全局函数isNaN()来干这件事.
    // 它会隐式的将它的参数转换成数字,所以即便参数是个不能转换成数字的字符串,它也会返回true(转换成了NaN)
    console.debug(TAG, isNaN("xyz"))
    console.debug(TAG, NaN)
    console.debug(TAG, Infinity)
    // 用0作除数会产生另外一个特殊值Infinity:
    console.debug(TAG, 3 / 0)
    // 你不能想当然的猜测正无穷大或者负无穷大的计算结果:
    console.debug(TAG, Infinity + Infinity)
    console.debug(TAG, Infinity - Infinity)
    console.debug(TAG, Infinity * Infinity)
    console.debug(TAG, Infinity / Infinity)
    console.debug(TAG, typeof NaN)
    console.debug(TAG, typeof Infinity)
    console.debug(TAG, "John".constructor)
    console.debug(TAG, false.constructor)
    console.debug(TAG, [1, 2, 3, 4].constructor)
    console.debug(TAG, {name: "John", age: 34}.constructor)
    console.debug(TAG, new Date().constructor)
    console.debug(TAG, function () {
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
    console.debug(TAG, out)
    out = String(123)       // 将数字 123 转换为字符串并返回
    console.debug(TAG, out)
    out = String(100 + 23)  // 将数字表达式转换为字符串并返回
    console.debug(TAG, out)

    // Number 方法 toString() 也是有同样的效果
    out = x.toString()
    console.debug(TAG, out)
    out = (123).toString()
    console.debug(TAG, out)
    out = (100 + 23).toString()
    console.debug(TAG, out)

    // 将字符串转换为数字
    out = Number("3.14")
    console.debug(TAG, out)
    out = Number("")
    console.debug(TAG, out)
    out = Number("  ")
    console.debug(TAG, out)
    out = Number("99 88")
    console.debug(TAG, out)
    out = Number("-99 88")
    console.debug(TAG, out)

    // Operator + 可用于将变量转换为数字：
    let y = "5"
    let z = +y
    console.debug(TAG, z)
    console.debug(TAG, typeof z)

    console.debug(TAG, Number(false))
    console.debug(TAG, Number(true))
    console.debug(TAG, Number(new Date()))

    // 自动转换类型
    // 当 JavaScript 尝试操作一个 "错误" 的数据类型时，会自动转换为 "正确" 的数据类型
    console.debug(TAG, 5 + null)
    console.debug(TAG, "5" + null)
    console.debug(TAG, "z" - null)
    console.debug(TAG, "5" + 1)
    console.debug(TAG, "5" - 1)
    console.debug(TAG, "z" - 1)
}

function printDate() {
    const date = new Date()
    console.debug(TAG, date.getDate())         // 从 Date 对象返回一个月中的某一天 (1 ~ 31)
    console.debug(TAG, date.getDay())          // 从 Date 对象返回一周中的某一天 (0 ~ 6)
    console.debug(TAG, date.getFullYear())     // 从 Date 对象以四位数字返回年份
    console.debug(TAG, date.getHours())        // 返回 Date 对象的小时 (0 ~ 23)
    console.debug(TAG, date.getMilliseconds()) // 返回 Date 对象的毫秒(0 ~ 999)
    console.debug(TAG, date.getMinutes())      // 返回 Date 对象的分钟 (0 ~ 59)
    console.debug(TAG, date.getMonth())        // 从 Date 对象返回月份 (0 ~ 11)
    console.debug(TAG, date.getSeconds())      // 返回 Date 对象的秒数 (0 ~ 59)
    console.debug(TAG, date.getTime())         // 返回 1970 年 1 月 1 日至今的毫秒数
    console.debug(TAG, "-----------")
    console.debug(TAG, date.getUTCHours())
}

function compareTest() {
    let x = 0
    let y = 0
    let z = 0
    if (x = 1) {
        console.debug(TAG, Boolean(x = -1))
    }
    // 在常规的比较中，数据类型是被忽略的
    x = 10
    y = "10"
    console.debug(TAG, x == y)
    // 在严格的比较运算中，=== 为恒等计算符，同时检查表达式的值与类型
    // noinspection JSIncompatibleTypesComparison
    console.debug(TAG, x === y)
    //switch 语句会使用恒等计算符(===)进行比较
    switch (x) {
        case "10":
            alert("World")
            break
        case 10:
            console.debug(TAG, "switch 语句会使用恒等计算符(===)进行比较")
            break
        default:
            throw new Error("IllegalStateException")
    }

    // JavaScript 中的所有数据都是以 64 位浮点型数据(float) 来存储。
    // 所有的编程语言，包括 JavaScript，对浮点型数据的精确度都很难确定：
    x = 0.1
    y = 0.2
    z = x + y
    console.debug(TAG, z === 0.3)
    console.debug(TAG, z)
    z = (x * 10 + y * 10) / 10
    console.debug(TAG, z === 0.3)
    console.debug(TAG, z)

    if (x === 19) ;
    {
        console.debug(TAG, "code block")
    }

    // 数组最后一个值的后面添加逗号虽然语法没有问题，但是在不同的浏览器可能得到不同的结果。
    let colors = [5, 6, 7,]; //这样数组的长度可能为3 也可能为4
    console.debug(TAG, "数组长度: " + colors.length)
}

function ajaxTest() {
    let xhr = new XMLHttpRequest()
    xhr.onload = () => {
        let resp = xhr.response
        console.debug(TAG, resp)
    }
    xhr.onerror = () => {
        console.debug(TAG, "Requst http error")
    }
    xhr.open("GET", "http://www.liulongbin.top:3005/api/getlunbo", true)
    xhr.send()
}

function promiseTest() {
    print(1, 1000).then(() => {
        return print(2, 2000)
    }).then(() => {
        print(3, 3000).then(() => {
            return awaitTest()
        })
    })
}

async function awaitTest() {
    await print(1, 1000)
    await print(2, 2000)
    await print(3, 3000)
}

function print(msg, delay) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.debug(TAG, msg)
            resolve()
        }, delay)
    })
}

function mapTest() {
    const nums = [1, 2, 3, 4, 5]
    const doubled = nums.map(it => it * 2)
    console.debug(doubled)
}
