import React from "react"
import ReactDOM from "react-dom"
import js from "./js/main"
import ra, {
    Clock, Toggle, ListView, NameForm, EssayForm, FlavorForm, Reservation, Calculator
} from "./react/main"


// 在 JSX 语法中，你可以在大括号内放置任何有效的 JavaScript 表达式
// JSX 也是一个表达式
const element = ra.showTime(new Date().toLocaleTimeString())

class App extends React.Component {
    render() {
        return (
            <div>
                <Clock/>
                <Toggle/>
                <ListView/>
                <NameForm/>
                <EssayForm/>
                <FlavorForm/>
                <Reservation/>
                <Calculator/>
            </div>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById("root"),
    () => {
        js.main()
    }
)
