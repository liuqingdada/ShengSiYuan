# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/yctu/tools/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-repackageclasses 'wenwen'
-allowaccessmodification

-keepattributes SourceFile,LineNumberTable

-dontwarn okhttp3.logging.**
# ignore warning for R.raw.model
-dontwarn com.mobvoi.speech.SpeechService

# Bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}
-keep class androidx.core.**{*;}

# Leakcanary
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }

### 高德定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
#2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}
-dontwarn com.amap.apis.**
-dontwarn com.amap.api.**

### 个推
-dontwarn com.igexin.**
-keep class com.igexin.** { *; }
-keep class org.json.** { *; }

### QQ
-keep class com.tencent.** { *; }

### weixin
-keep class com.tencent.mm.opensdk.** {*;}
-keep class com.tencent.wxop.** {*;}
-keep class com.tencent.mm.sdk.** {*;}

# weibo
-keep class com.sina.** { *; }

-keep class com.mobvoi.assistant.message.GTPushChannel$MessageContent { *; }

#该类的混淆会使得protobuf数据类进行java自带的反序列化时失败
-keep class com.google.protobuf.GeneratedMessageLite$SerializedForm { *; }

-keep class com.mobvoi.assistant.proto.CardStreamRecProto { *; }
-keep class com.mobvoi.assistant.proto.CardStreamRecProto$* { *; }
-keep class com.mobvoi.assistant.proto.ContentRecProto { *; }
-keep class com.mobvoi.assistant.proto.ContentRecProto$* { *; }
-keep class com.mobvoi.assistant.proto.LaboratoryProto { *; }
-keep class com.mobvoi.assistant.proto.LaboratoryProto$* { *; }

-keep class com.mobvoi.assistant.proto.TrainingProto {*;}
-keep class com.mobvoi.assistant.proto.TrainingProto$* {*;}

-keep class com.mobvoi.assistant.proto.InvitationProto {*;}
-keep class com.mobvoi.assistant.proto.InvitationProto$* {*;}

-keep class com.mobvoi.assistant.proto.AchievementProto {*;}
-keep class com.mobvoi.assistant.proto.AchievementProto$* {*;}

-keep class com.mobvoi.assistant.proto.CodeScanProto {*;}
-keep class com.mobvoi.assistant.proto.CodeScanProto$* {*;}

#Eventbus
-keepclassmembers class ** {
     @com.google.common.eventbus.Subscribe public *;
 }

-keep class androidx.appcompat.widget.Toolbar {*;}