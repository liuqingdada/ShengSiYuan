import React from "react"

const TAG = "REACT-MAIN:"

let main = {
    main: function () {
    },

    showTime: function (timeString) {
        return showTime(timeString)
    }
}
export default main

const user = {
    firstName: "Yang",
    lastName: "Liuqing",
}

function formatName() {
    return user.lastName + " " + user.firstName
}

/**
 * 这是一个React元素, 而且是一个 DOM 标签
 * @returns {JSX.Element}
 */
function showTime(timeString) {
    console.debug(TAG, Boolean(user))
    return (
        <div>
            <h1>Hello, {formatName()}!</h1>
            <h2>It is {timeString}.</h2>
        </div>
    )
}

///////////////////////////////

// 函数组件
function Welcome(props) {
    return <h1>Hello, {props.name}</h1>
}

// class 组件
class Welcome2 extends React.Component {
    render() {
        return <h1>Hello, {this.props.name}</h1>
    }
}

/**
 * 这也是一个React元素, 而且是一个用户自定义的组件
 * 当 React 元素为用户自定义组件时，它会将 JSX 所接收的属性（attributes）
 * 以及子组件（children）转换为单个对象传递给组件，这个对象被称之为 “props”
 *
 * 注意： 组件名称必须以大写字母开头
 * React 会将以小写字母开头的组件视为原生 DOM 标签
 *
 * 组件无论是使用函数声明还是通过 class 声明，都决不能修改自身的 props
 * 所有 React 组件都必须像纯函数一样保护它们的 props 不被更改
 *
 * name: props 的一个属性
 * @type {JSX.Element}
 */
const elementWelcome = <Welcome name="Sara"/>  // Welcome 的一个实例, Hello, Sara

// 组合组件
class App extends React.Component {
    render() {
        return (
            <div>
                <Welcome name="Sara"/>
                <Welcome name="Cahal"/>
                <Welcome2 name="Cooper"/>
            </div>
        )
    }
}

export class Clock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            date: new Date(),
        }
    }

    render() {
        return (
            <div>
                <h1>Hello, {formatName()}!</h1>
                <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
            </div>
        )
    }

    /**
     * 尽管 this.props 和 this.state 是 React 本身设置的，且都拥有特殊的含义，
     * 但是其实你可以向 class 中随意添加不参与数据流（比如计时器 ID）的额外字段。
     */
    componentDidMount() {
        console.log(TAG, "onResume")
        this.timerId = setInterval(() => {
            this.tick()
        }, 1000)
    }

    componentWillUnmount() {
        console.log(TAG, "onPause")
        clearInterval(this.timerId)
    }

    tick() {
        const date = new Date()
        const offset = 1000 - date.getMilliseconds()
        //console.log(TAG, offset)
        /**
         * 因为 this.props 和 this.state 可能会异步更新，所以你不要依赖他们的值来更新下一个状态。
         * 要解决这个问题，可以让 setState() 接收一个函数而不是一个对象。
         * 这个函数用上一个 state 作为第一个参数，将此次更新被应用时的 props 做为第二个参数：
         *  // Correct
         *  this.setState((state, props) => ({
         *      counter: state.counter + props.increment
         *  }));
         */
        this.setState({
            date: new Date(),
        })
    }
}

export class Toggle extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isToggleOn: true
        }
        /**
         * 你必须谨慎对待 JSX 回调函数中的 this，在 JavaScript 中，class 的方法默认不会绑定 this。
         * 如果你忘记绑定 this.handleClick 并把它传入了 onClick，当你调用这个函数的时候 this 的值为 undefined。
         *
         * 这并不是 React 特有的行为；这其实与 JavaScript 函数工作原理有关。
         * 通常情况下，如果你没有在方法后面添加 ()，例如 onClick={this.handleClick}，你应该为这个方法绑定 this。
         *
         * @type {any}
         */
        this.handleClick = this.handleClick.bind(this)
    }

    handleClick() {
        this.setState(state => ({
            isToggleOn: !state.isToggleOn
        }))
    }

    /**
     * class fields 语法
     */
    handleClick2 = () => {
        this.handleClick()
    }

    /**
     * 在极少数情况下，你可能希望能隐藏组件，即使它已经被其他组件渲染。
     * 若要完成此操作，你可以让 render 方法直接返回 null，而不进行任何渲染。
     * @returns {JSX.Element}
     */
    render() {
        return (
            /**
             * 此语法问题在于每次渲染 LoggingButton 时都会创建不同的回调函数。
             * 在大多数情况下，这没什么问题，但如果该回调函数作为 prop 传入子组件时，
             * 这些组件可能会进行额外的重新渲染。
             * 我们通常建议在构造器中绑定或使用 class fields 语法来避免这类性能问题。
             */
            /*<button onClick={() => this.handleClick()}>
                {this.state.isToggleOn ? "ON" : "OFF"}
            </button>*/

            // 构造器中绑定
            <button onClick={this.handleClick}>
                {this.state.isToggleOn ? "ON" : "OFF"}
            </button>
        )
    }
}

/**
 * 一个元素的 key 最好是这个元素在列表中拥有的一个独一无二的字符串。通常，我们使用数据中的 id 来作为元素的 key
 *
 * 当元素没有确定 id 的时候，万不得已你可以使用元素索引 index 作为 key：
 * 如果列表项目的顺序可能会变化，我们不建议使用索引来用作 key 值，因为这样做会导致性能变差，还可能引起组件状态的问题
 *
 * 如果你选择不指定显式的 key 值，那么 React 将默认使用索引用作为列表项目的 key 值。
 */
export class ListView extends React.Component {
    render() {
        const data = [1, 2, 3, 4, 5]
        const items = data.map((value, index) =>
            <li key={index}>
                {"index: " + index + ", value: " + value}
            </li>
        )
        return <ul>{items}</ul>
    }
}

export class NameForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: ""
        }
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(event) {
        this.setState({
            value: event.target.value
        })
    }

    handleSubmit(event) {
        alert("提交的名字: " + this.state.value)
        /**
         * 在 React 中另一个不同点是你不能通过返回 false 的方式阻止默认行为。你必须显式的使用 preventDefault
         */
        event.preventDefault()
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    名字:
                    <input type="text" value={this.state.value} onChange={this.handleChange}/>
                </label>
                <input type="submit" value="提交"/>
            </form>
        )
    }
}

export class EssayForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: "请撰写一篇关于你喜欢的 DOM 元素的文章."
        }
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(event) {
        this.setState({
            value: event.target.value
        })
    }

    handleSubmit(event) {
        alert("提交的文章: " + this.state.value)
        event.preventDefault()
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    文章:
                    <textarea value={this.state.value} onChange={this.handleChange}/>
                </label>
                <input type="submit" value="提交"/>
            </form>
        )
    }
}

export class FlavorForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: "coconut"
        }
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(event) {
        this.setState({
            value: event.target.value
        })
        console.log(event)
        console.log(this.state)
    }

    handleSubmit(event) {
        alert("你喜欢的风味是: " + this.state.value)
        event.preventDefault()
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    选择你喜欢的风味:
                    <select value={this.state.value} onChange={this.handleChange}>
                        <option value="grapefruit">葡萄柚</option>
                        <option value="lime">酸橙</option>
                        <option value="coconut">椰子</option>
                        <option value="mango">芒果</option>
                    </select>
                </label>
                <input type="submit" value="提交"/>
            </form>
        )
    }
}

export class Reservation extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isGoing: true,
            numberOfGuests: 2,
        }
        this.handleInputChange = this.handleInputChange.bind(this)
    }

    /**
     * 当需要处理多个 input 元素时，我们可以给每个元素添加 name 属性，并让处理函数根据 event.target.name 的值选择要执行的操作
     * @param event
     */
    handleInputChange(event) {
        const target = event.target
        const value = target.type === "checkbox" ? target.checked : target.value
        const name = target.name
        console.log(TAG, value, name)
        this.setState({
            [name]: value
        })
    }

    render() {
        return (
            <form>
                <label>
                    参与:
                    <input
                        name="isGoing"
                        type="checkbox"
                        checked={this.state.isGoing}
                        onChange={this.handleInputChange}/>
                </label>
                <br/>
                <label>
                    来宾人数:
                    <input
                        name="numberOfGuests"
                        type="number"
                        value={this.state.numberOfGuests}
                        onChange={this.handleInputChange}/>
                </label>
            </form>
        )
    }
}

export class BoilingVerdict extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            celsius: 0
        }
    }

    render() {
        let info
        if (this.props.celsius >= 100) {
            info = "The water would boil."
        } else {
            info = "The water would not boil."
        }
        return <p>{info}</p>
    }
}















