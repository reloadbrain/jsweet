/* 
 * JSweet transpiler - http://www.jsweet.org
 * Copyright (C) 2015 CINCHEO SAS <renaud.pawlak@cincheo.fr>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jsweet.transpiler.util;

import org.jsweet.transpiler.JSweetProblem;
import org.jsweet.transpiler.JSweetTranspiler;
import org.jsweet.transpiler.SourceFile;
import org.jsweet.transpiler.SourcePosition;
import org.jsweet.transpiler.TranspilationHandler;

/**
 * An error count decorator for a transpilation handler.
 * 
 * @author Renaud Pawlak
 */
public class ErrorCountTranspilationHandler implements TranspilationHandler {

	private TranspilationHandler delegate;
	private int errorCount = 0;
	private int warningCount = 0;
	private int problemCount = 0;

	/**
	 * Decorates the given transpilation handler.
	 */
	public ErrorCountTranspilationHandler(TranspilationHandler delegate) {
		this.delegate = delegate;
	}

	/**
	 * Count the problems and delegates to the decorated transpilation handler.
	 */
	public void report(JSweetProblem problem, SourcePosition sourcePosition, String message) {
		switch (problem.getSeverity()) {
		case ERROR:
			problemCount++;
			errorCount++;
			break;
		case WARNING:
			problemCount++;
			warningCount++;
			break;
		default:
			problemCount++;
		}
		delegate.report(problem, sourcePosition, message);
	}

	/**
	 * Returns the error count.
	 */
	public int getErrorCount() {
		return errorCount;
	}

	@Override
	public void onCompleted(JSweetTranspiler<?> transpiler, boolean fullPass, SourceFile[] files) {
		delegate.onCompleted(transpiler, fullPass, files);
	}

	/**
	 * Returns the warning count.
	 */
	public int getWarningCount() {
		return warningCount;
	}

	/**
	 * Returns the problem count (error + warning count).
	 */
	public int getProblemCount() {
		return problemCount;
	}

}
