# Begin: Common Proguard rules

# Don't note duplicate definition (Legacy Apche Http Client)
-dontnote android.net.http.*
-dontnote org.apache.http.**

# Add when compile with JDK 1.7
-keepattributes EnclosingMethod

# End: Common Proguard rules
-keepclassmembers class * extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite {
  <fields>;
}
-dontwarn com.google.errorprone.annotations.Immutable