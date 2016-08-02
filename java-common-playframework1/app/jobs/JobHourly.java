package jobs;

import common.play1.extensions.JobBase;
import play.jobs.Every;

@Every("1h")
public class JobHourly extends JobBase {

    @Override
    protected void doDoJob() throws Exception {

    }

}