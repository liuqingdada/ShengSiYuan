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
        super();
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
        console.log(TAG, offset)
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
        super();
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
