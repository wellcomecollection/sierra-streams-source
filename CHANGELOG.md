# CHANGELOG

## v0.4

This release adds a new `timeoutMs` parameter to `StreamSource`, allowing you
to choose how long to wait before timing out the connection to Sierra.

This parameter is optional, and defaults to the previous timeout (10000 ms).

## v0.3 - 2018-05-30

This release contains no code changes, and is just a test of our new automated
release process.

## v0.2 - 2018-01-11

This release sends the "Connection: close" header on every request to Sierra,
causing it to reset the HTTP session every time.

This was based on an issue we saw with the Wellcome instance of Sierra where
successive response in the same HTTP session would take exponentially longer
to return.

## v0.1 - 2017-11-22

Initial release!
