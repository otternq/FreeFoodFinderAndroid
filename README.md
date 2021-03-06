Free Food Finder
========

An android app that lets people tell the world about free food

Missing File
--------

###Private Strings###
This project depends on a number of API keys, the file that contains these keys
is expected to be contained at `res/values/privateStrings.xml`

```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="mongolabAPIKey">YOUR_MONGOLAB_API_KEY_HERE</string>
    <string name="googleAPIKey">YOUR_GOOGLE_API_KEY_HERE</string>
    <string name="googleAnalyticsKey">YOUR_GOOGLE_ANALYTICS_KEY</string>
</resources>
```

Dependencies
--------

###Internal###

####GSON####
A JSON library for Java

- [URL](http://code.google.com/p/google-gson/)
- [User Guide](https://sites.google.com/site/gson/gson-user-guide)
- [Downloads](http://code.google.com/p/google-gson/downloads/list)

####Google Play services APIs####
See http://developer.android.com/google/play-services/index.html for more details

###External###

####MongoLab####
Currently this application is dependent on MongoLabs for its data storage.
The application requires an API key to MongoLab and expects the following: <br />

<b>Database</b>:
- freefoodfinder

<b>Collections</b>:
- entries

Platform
--------
Operating System: Android <br />
Minimum SDK Version: 13 <br />
Target SDK Version: 16 <br />

References
--------
- [Android Documentation - Action Bar](http://developer.android.com/guide/topics/ui/actionbar.html)
- [Android Documentation - XML Menu](http://developer.android.com/guide/topics/ui/menus.html#xml)
- [Android Documentation - onOptionsItemSelected](http://developer.android.com/reference/android/app/Activity.html#onOptionsItemSelected(android.view.MenuItem(http://developer.android.com/reference/android/app/Activity.html#onOptionsItemSelected(android.view.MenuItem)
- [Android Documentation - String Resources](http://developer.android.com/guide/topics/resources/string-resource.html)
- [Android Documentation - Toasts](http://developer.android.com/guide/topics/ui/notifiers/toasts.html)
- [AndroidSnippets - HTTP Post Request with HttpClient](http://www.androidsnippets.com/executing-a-http-post-request-with-httpclient)
- [RESTful Java Client With Apache HttpClient](http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-apache-httpclient/)
- [Android GPS, Location Manager Tutorial](http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/) - Source of the GPSTracker Class
- [MongoDB for mobile app backends](http://www.slideshare.net/marakana/learn-5611322) - Specifically slides 30/31
- [MongoDB Documentation - Geospatial Indexes](http://docs.mongodb.org/manual/applications/geospatial-indexes/)
- [W3.org HTTP Protocal Status Codes](http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html)
