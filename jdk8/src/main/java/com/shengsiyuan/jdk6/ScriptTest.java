package com.shengsiyuan.jdk6;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by cooper
 * 2020/11/11.
 * Email: 1239604859@qq.com
 * <p>
 * 对脚本语言的支持（如: ruby, groovy, javascript）
 */
class ScriptTest {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine javaScript = manager.getEngineByName("JavaScript");

        String code = "print('Hello World')";
        javaScript.eval(code);
    }
}
