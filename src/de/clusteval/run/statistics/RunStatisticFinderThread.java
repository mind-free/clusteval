/*******************************************************************************
 * Copyright (c) 2013 Christian Wiwie.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Christian Wiwie - initial API and implementation
 ******************************************************************************/
/**
 * 
 */
package de.clusteval.run.statistics;

import java.io.File;

import de.clusteval.framework.repository.RegisterException;
import de.clusteval.framework.repository.Repository;
import de.clusteval.framework.threading.SupervisorThread;
import de.clusteval.utils.Finder;
import de.clusteval.utils.FinderThread;

/**
 * @author Christian Wiwie
 * 
 */
public class RunStatisticFinderThread extends FinderThread {

	/**
	 * @param supervisorThread
	 * @param framework
	 * @param checkOnce
	 * 
	 */
	public RunStatisticFinderThread(final SupervisorThread supervisorThread,
			final Repository framework, final boolean checkOnce) {
		super(supervisorThread, framework, 30000, checkOnce);
	}

	/**
	 * @param supervisorThread
	 * @param framework
	 * @param sleepTime
	 * @param checkOnce
	 * 
	 */
	public RunStatisticFinderThread(final SupervisorThread supervisorThread,
			final Repository framework, final long sleepTime,
			final boolean checkOnce) {
		super(supervisorThread, framework, sleepTime, checkOnce);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.FinderThread#runStart()
	 */
	@Override
	protected void beforeFind() {
		this.log.debug("Checking for new RunStatistics...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.FinderThread#getFinder()
	 */
	@Override
	protected Finder getFinder() throws RegisterException {
		return new RunStatisticFinder(repository, System.currentTimeMillis(),
				new File(repository.getRunStatisticBasePath()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.FinderThread#runEnd()
	 */
	@Override
	protected void afterFind() {
		repository.setRunStatisticsInitialized();
	}
}
