package common.play1.extensions;

import common.basic.logs.Logger;
import common.basic.utils.DateUtil;
import play.jobs.Job;

import java.util.Date;

public abstract class JobBase<T> extends Job<T> {
    public void doJob() {
        Date dateStart = new Date();
        try {
            doDoJob();
        } catch (Exception e) {
            Logger.e(e.toString());
        } finally {
            Date dateEnd = new Date();
            long diff = dateEnd.getTime() - dateStart.getTime();
            Logger.i(String.format("%s\t%s ~ %s\t%dms", getClass().getSimpleName(), DateUtil.yyyyMMddHHmmss(dateStart), DateUtil.yyyyMMddHHmmss(dateEnd), diff));

        }
    }

    protected abstract void doDoJob() throws Exception;
}
