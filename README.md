# chota-url
### A tinyurl/bit.ly like service
## Tech stack:
 * Scala
 * akka-http
 * MongoDB
 * Redis

### MongoDb is used to store url data like creation time, hits count.
### Redis is used for fast url lookup and redirection.

## Screenshots
### Request for creating short url
![ss](https://github.com/amitbansal7/chota-url/blob/master/screenshots/1.png?raw=true)

### Url lookup and redirect.
![ss](https://github.com/amitbansal7/chota-url/blob/master/screenshots/2.png?raw=true)

### Append "+" and the end of short url to get url stats and info.
![ss](https://github.com/amitbansal7/chota-url/blob/master/screenshots/3.png?raw=true)