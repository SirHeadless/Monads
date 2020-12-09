

/*
 * Projekt: Detact
 *
 * Copyright(c) Symate GmbH. Unerlaubtes Kopieren und Nutzen untersagt.
 */


trait Applicative[C[_]] {
  def pure[A](a: A): C[A]
}
