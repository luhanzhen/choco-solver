/**
 *  Copyright (c) 1999-2011, Ecole des Mines de Nantes
 *  All rights reserved.
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the Ecole des Mines de Nantes nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package solver.search.strategy.strategy.set;

import solver.variables.SetVar;

/**
 * Heuristic for branching on a given SetVar
 * @author Jean-Guillaume Fages
 * @since 6/10/13
 */
public interface SetValSelector {

	/**
	 * Value selection heuristic
	 * @param v a non-instantiated SetVar
	 * @return an integer i of v's envelope, which is not included in v's kernel
	 * so that a decision (forcing/removing i) can be applied on v
	 */
	public int selectValue(SetVar v);

	/**
	 * Eventually perform some computation before the search process starts
	 */
	public void init();

	/**
	 * Selects the first integer in the envelope and not in the kernel
	 */
	public class FirstVal implements SetValSelector{
		@Override
		public int selectValue(SetVar s) {
			for (int i=s.getEnvelopeFirst(); i!=SetVar.END; i=s.getEnvelopeNext()) {
				if (!s.kernelContains(i)) {
					return i;
				}
			}
			throw new UnsupportedOperationException(s+" is already instantiated. Cannot compute a decision on it");
		}
		@Override
		public void init(){}
	}
}
