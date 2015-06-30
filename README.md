# LediMoteAndroid

Android client for [LediMote](https://github.com/drejkim/LediMote).

Take a look at this [video](https://youtu.be/i61g4aYkrI0) to see the web and iOS clients in action. The Android client behaves similarly.

![LediMote](https://raw.githubusercontent.com/drejkim/LediMoteAndroid/master/screenshots/LediMote.png)

## Requirements

* [LediMote](https://github.com/drejkim/LediMote)
* Android 4.1 and higher
* Android Studio

## Running the demo

### Updating the WebSocket address

Modify `mSocket` in `MainActivity.java`. The section of the code looks like this:

```java
private Socket mSocket;
    {
        try {
            // MODIFY THIS WITH THE APPROPRIATE URL
            // Note: For some reason, Edison's name does not resolve, and I had to use its IP address
            mSocket = IO.socket("http://123.456.7.890:8080");
        } catch(URISyntaxException e) { }
    }
```

Use the IP address of your Edison in `mSocket`. For some reason, Edison's network name does not resolve (at least for me).

### Running the LediMote server

* On Edison, navigate to `LediMote/server`.
* Run the server by typing `node server.js`.

The Node.js server should now be running. The console will look something like this:

```bash
HTTP server listening on port 8080
```

### Using the Android client

See the [Android instructions](https://developer.android.com/tools/building/building-studio.html) to build and run the app.
