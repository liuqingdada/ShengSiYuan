import objectTest from "./object"

const TAG = "JS-MAIN:"

let main = {
    main: function () {
        startServer()

        startLocalTest()
    },
}
export default main

function startServer() {
}

function startLocalTest() {
    console.debug(TAG, "app started: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
    objectTest.testPerson()
    console.debug("\n\n")
    objectTest.testTypeOf()
    console.debug("\n\n")
    let isArray = objectTest.isArray([1, 2, 3, 4])
    console.debug(TAG, "[1, 2, 3, 4] is array: " + isArray)
    isArray = objectTest.isArray("1234")
    console.debug(TAG, "1234 is array: " + isArray)
    let isDate = objectTest.isDete(new Date())
    console.debug(TAG, "new Date() is Date: " + isDate)
    isDate = objectTest.isDete("string date")
    console.debug(TAG, "string date is Date: " + isDate)
    console.debug("\n\n")
    objectTest.convert(new Date())
    console.debug("\n\n")
    objectTest.printDate()
    console.debug("\n\n")
    objectTest.compareTest()
    console.debug("\n\n")
    objectTest.ajaxTest()
    console.debug("\n\n")
    objectTest.promiseTest()
    console.debug("\n\n")
    objectTest.mapTest()
}
