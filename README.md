# deep-link-referred
reference : https://www.linkedin.com/pulse/android-deferred-deep-link-hamid-sedghi-n


hosting
https://gist.github.com/Manit123001/ea171e6b2740d3c6767d2e5b67e92bdbp


code for test broadcast receiver
```adb shell am broadcast -a com.android.vending.INSTALL_REFERRER -n com.example.navigation.sample_deferred_deeplink/.InstallReferrerReceiver --es "referrer" "?product=JET\&utm_source=test"```
