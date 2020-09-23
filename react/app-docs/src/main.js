import objectTest from "./object"

let main = {
    main: function () {
        console.log("app started: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        objectTest.testPerson()
        console.log("\n\n")
        objectTest.testTypeOf()
        console.log("\n\n")
        let isArray = objectTest.isArray([1, 2, 3, 4])
        console.log("[1, 2, 3, 4] is array: " + isArray)
        isArray = objectTest.isArray("1234")
        console.log("1234 is array: " + isArray)
        let isDate = objectTest.isDete(new Date())
        console.log("new Date() is Date: " + isDate)
        isDate = objectTest.isDete("string date")
        console.log("string date is Date: " + isDate)
        console.log("\n\n")
        objectTest.convert(new Date())
    }
}
export default main
