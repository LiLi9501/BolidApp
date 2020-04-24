package com.timer;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

import com.javaee.servlets.Bolid;

@Singleton

@Startup
public class UpdateTimer {

	private final Integer MIN_DELAYED = 15;
	private final String timerName = "UpdateBolidTimer";

	@Resource
	private TimerService timerService;

	@Inject
	Bolid bolid;

	@PostConstruct
	private void init() {
		for (Object obj : timerService.getTimers()) {
			Timer t = (Timer) obj;
			if (t.getInfo().equals(timerName)) {
				t.cancel();
			}
		}
		timerService.createTimer(5000, (1000 * MIN_DELAYED), timerName);
	}

	// @Schedule(hour = "*", minute = "*/10", second = "*", info = "Every 10 min
	// timer", persistent=false)
	@Timeout
	public void execute(Timer timer) {
		System.out.println("Timer Service : " + timer.getInfo());
		System.out.println("Execution Time : " + new Date());
		System.out.println("Data update from Bolid");
		System.out.println("Next update: " + timer.getNextTimeout());
		System.out.println("____________________________________________");
		bolid.updateCategoryMap(0, Integer.MAX_VALUE);
	}
}