
	package ar.edu.itba.ss.core;

	import java.util.function.BiFunction;

		/**
		* <p>Permite crear tuplas bidimensionales, útil para operaciones
		* simultáneas con streams. <b>No abusar de su uso.</b></p>
		*/

	public class Pair<T> {

		protected final T left;
		protected final T right;

		public Pair(final T left, final T right) {
			this.left = left;
			this.right = right;
		}

		public T getLeft() {
			return left;
		}

		public T getRight() {
			return right;
		}

		public <R> Pair<R> combine(
				final Pair<T> p, final BiFunction<T, T, R> combiner) {
			return new Pair<R>(
					combiner.apply(left, p.left),
					combiner.apply(right, p.right));
		}
	}
