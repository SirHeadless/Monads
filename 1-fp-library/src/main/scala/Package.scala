
object Package {
  private type ReqularArrow[A, B] = A => B
  private type KleisliArrow[A, B, C[_]] = A => C[B]

  implicit final class InfixNotationForPointFree[A, B](private val ab: A => B) extends AnyVal {
    @inline def -->[C](bc: B => C): A => C = PointFree.compose(ab)(bc)
  }

  implicit final class InfixNotationForPointFreeKleisli[A, B](private val ab: A => IO[B]) extends AnyVal {
    @inline def >=>[C](bc: B => IO[C]): A => IO[C] = PointFree.composeKleisli(ab)(bc)
  }

  implicit final class InfixNotationForFlatMap[A, F[_]: Monad](private val fa: F[A]) {
    def map[B](ab: A => B): F[B] = Monad[F].map(fa)(ab)
    def flatMap[B](afb: A => F[B]): F[B] = Monad[F].flatMap(fa)(afb)
  }

}
