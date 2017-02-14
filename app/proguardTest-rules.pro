# ------------------- TEST DEPENDENCIES -------------------
-dontwarn org.hamcrest.**
-dontwarn android.test.**
-dontwarn android.support.test.**

-keep class org.hamcrest.** {
   *;
}

-keep class org.junit.** { *; }
-dontwarn org.junit.**

-keep class junit.** { *; }
-dontwarn junit.**

-keep class sun.misc.** { *; }
-dontwarn sun.misc.**

-keep class com.tojc.**
-keepclassmembers class com.tojc.** { *; }
-keep enum com.tojc.**
-keepclassmembers enum com.tojc.** { *; }
-keep interface com.tojc.**
-keepclassmembers interface com.tojc.** { *; }

-keep class javax.**

-dontwarn com.tojc.ormlite.android.compiler.**
-dontwarn com.squareup.javawriter.**