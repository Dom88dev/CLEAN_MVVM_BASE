# Begin: Proguard rules for kakao

-keep class com.kakao.** { *; }
-keepattributes Signature
-keepclassmembers class * {
  public static <fields>;
  public *;
}
-dontwarn android.support.v4.**,org.slf4j.**,com.google.android.gms.**

-keep class com.kakao.sdk.**.model.* { <fields>; }
-keep class * extends com.google.gson.TypeAdapter

# End: Proguard rules for kakao