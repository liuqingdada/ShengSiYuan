import React from "react"
import ReactDOM from "react-dom"
import main from "./main"

ReactDOM.render(
    <h1>Hello World!</h1>,
    document.getElementById("root"),
    () => {
        main.main()
    }
)
