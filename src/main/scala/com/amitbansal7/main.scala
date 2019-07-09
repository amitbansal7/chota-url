import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import scala.util.{ Failure, Success }
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.amitbansal.utils.CORSHandler
import scala.concurrent.duration._
import scala.io.StdIn
import akka.http.scaladsl.server.Directives._

object main extends CORSHandler{

  val host = "0.0.0.0"
  val port = 8080


  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("chota-url")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    implicit val timeout = Timeout(5 seconds)

    val route: Route = corsHandler {
      toStrictEntity(30 seconds) {
        (path("") & get) {
          complete(StatusCodes.OK, "Server is up and running..")
        }
      }
    }

    val bindingFuture = Http().bindAndHandle(route, host, port)

    bindingFuture.onComplete {
      case Success(_) => println(s"Server is running at ${host}:${port}\nHit return to terminate..")
      case Failure(e) => println(s"could not start application: {}", e.getMessage)
    }

    StdIn.readLine()
    bindingFuture.flatMap(_.unbind())
    system.terminate()
    println("Server is closed.")
  }
}