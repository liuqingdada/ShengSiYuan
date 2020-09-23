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
    console.log(NaN)
    console.log(Infinity)
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
}

function printDate() {
    const date = new Date()
    console.log(date.getDate())
    console.log(date.getDay())
}
