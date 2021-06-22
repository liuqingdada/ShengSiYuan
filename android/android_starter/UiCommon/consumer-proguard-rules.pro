# Fragmentation
-keepclasseswithmembernames class androidx.customview.widget.ViewDragHelper{ *; }
-keep class * extends androidx.fragment.app.FragmentManager{ *; }

# immersionbar
-keep class com.gyf.immersionbar.* {*;}
-dontwarn com.gyf.immersionbar.**

# AgentWeb
# Java 注入类不要混淆, 例如 sample 里面的 AndroidInterface 类, 需要 Keep
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**