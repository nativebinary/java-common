package common.play1.utils;

import play.jobs.Job;

/**
 * Created with IntelliJ IDEA.
 * User: aha00a
 * Date: 12. 5. 7.
 * Time: PM 12:45
 * To change this template use File | Settings | File Templates.
 */
public class JobUtil{
    public static void runAsync(Job job) {
        job.now();
    }

    public static void runSync(Job job) {
        job.run();
    }

    public static <T> T runSyncWithResult(Job<T> job) throws Exception {
        return job.doJobWithResult();
    }
}
