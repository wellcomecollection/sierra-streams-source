# CHANGELOG

## v0.2 - 2018-01-11

This release sends the "Connection: close" header on every request to Sierra,
causing it to reset the HTTP session every time.

This was based on an issue we saw with the Wellcome instance of Sierra where
successive response in the same HTTP session would take exponentially longer
to return.

## v0.1 - 2017-11-22

Initial release!
