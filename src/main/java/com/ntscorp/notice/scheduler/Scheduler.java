package com.ntscorp.notice.scheduler;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	@Scheduled(cron = "0/5 * * * * ?")
	public void test2() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//logger.info("스케쥴러 : " + sf.format(new Date()));
	}
}
